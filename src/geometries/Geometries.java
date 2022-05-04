package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    //list of cross points
    private List<Intersectable> _intersectableList = new LinkedList<>();

    /**
     * constructor, add point cross to the list
     *
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * add all point cross to the list
     *
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(_intersectableList, geometries);
    }

    /**
     * find cross point and add to the list
     * @param ray
     * @return List of cross point
     */

/**
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
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
    }**/


    /**
     * find cross point and add to the list
     * @param ray
     * @return List of cross point
     */

    /**
     * @Override protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
     * return (List<GeoPoint>) this;
     * }
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
