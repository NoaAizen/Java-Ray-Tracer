package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * @author noga and noa
 */
public class Geometries extends Intersectable {
    /**
     * list of cross points
     */
    private List<Intersectable> _intersectableList = new LinkedList<>();
    // ***************** constructor ********************** //
    /**
     * constructor, add point cross to the list
     *
     * @param geometries - Intersectable...
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * add all point cross to the list
     *
     * @param geometries - Intersectable...
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(_intersectableList, geometries);
    }

    /**
     * This function unites all the intersection points closest to the beam and before the geometric shape
     * @param ray-   -The fund ray calculating the points is cut
     * @param maxDistance- max Distance
     * @return (List GeoPoint) this;
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
