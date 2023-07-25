package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * The Polygon class represents a two-dimensional polygon in 3D Cartesian coordinate system.
 * A polygon is defined by a list of vertices in order, forming an edge path.
 * The class inherits from the Geometry class.
 *
 * The Polygon must be convex, meaning it forms no angles greater than 180 degrees between consecutive edges.
 * The vertices of the polygon must lie in the same plane.
 *
 * Authors: Noga Jacobs and Noa
 */
public class Polygon extends Geometry {

	/**
	 * List of polygon's vertices in order by edge path
	 */
	protected List<Point> vertices;

	/**
	 * The plane in which the polygon lays (associated with the polygon).
	 */
	protected Plane plane;
	/**
	 * The number of vertices in the polygon
	 */
	private int size;

	/**
	 * Constructs a polygon based on a list of vertices. The vertices must be in order
	 * by edge path, and the polygon must be convex.
	 *
	 * @param vertices The list of vertices, ordered by edge path.
	 * @throws IllegalArgumentException In any case of an illegal combination of vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consecutive vertices are the same point</li>
	 *                                  <li>The vertices are not in the same plane</li>
	 *                                  <li>The order of vertices is not according to edge path</li>
	 *                                  <li>Three consecutive vertices lay in the same line (180° angle between two consecutive edges)</li>
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (var i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
		size = vertices.length;
	}
	// ***************** Overrides ********************** //

	/**
	 * Calculates the normal vector to the polygon at any given point.
	 * Since the polygon lies in a plane, the normal vector is constant and
	 * is calculated based on the plane associated with the polygon.
	 *
	 * @param point The point for which to calculate the normal.
	 * @return The normal vector.
	 */
	@Override
	public Vector getNormal(Point point) {
		return plane.getNormal();
	}

	/**
	 * Finds the intersection point of a ray with the polygon.
	 *
	 * @param ray         The ray for calculating the intersection point.
	 * @param maxDistance The maximum distance for valid intersections.
	 * @return A list containing a single GeoPoint object representing the intersection point of the ray with the polygon.
	 * If there is no intersection, the list is null.
	 */
	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		List<GeoPoint> list = plane.findGeoIntersectionsHelper(ray, maxDistance);
		if(list == null){
			return  null;
		}
		return List.of(new GeoPoint(this,list.get(0).point));
	}


}
