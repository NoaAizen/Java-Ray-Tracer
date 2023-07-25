package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * The Geometries class represents a collection of intersectable geometries in 3D Cartesian coordinate system.
 * It is a subclass of the Intersectable class.
 * Geometries objects are used to store multiple intersectable geometries together and find their intersection points with a given ray.
 * Geometries are immutable, and new geometries can be added to the collection using the "add" method.
 * The findGeoIntersectionsHelper method calculates the intersection points of the ray with all the geometries in the collection.
 *
 * Author: Noga Jacobs and Noa
 */
public class Geometries extends Intersectable {
    /**
     * List of intersectable geometries
     */
    private List<Intersectable> _intersectableList = new LinkedList<>();

    // ***************** constructor ********************** //
    /**
     * Constructs a Geometries object and adds the given intersectable geometries to the collection.
     *
     * @param geometries The intersectable geometries to add to the collection
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds the given intersectable geometries to the collection.
     *
     * @param geometries The intersectable geometries to add to the collection
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(_intersectableList, geometries);
    }

    /**
     * Finds the intersection points of the given ray with all the geometries in the collection.
     * The function unites all the intersection points closest to the ray's origin and before the maximum distance.
     *
     * @param ray         The ray for calculating the intersection points
     * @param maxDistance The maximum distance for valid intersection points
     * @return A list of GeoPoint objects representing the intersection points with the geometries in the collection
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        List<GeoPoint> totalIntersections = null;
        for (var geometry : _intersectableList) {
            var geoIntersections = geometry.findGeoIntersections(ray);
            if (geoIntersections != null) {
                if (totalIntersections == null) {
                    totalIntersections = new LinkedList<>();
                }
                totalIntersections.addAll(geoIntersections);
            }
        }
        return totalIntersections;
    }
}
