package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    //list of cross points
    private List<Intersectable> _intersectableList = new LinkedList<>();

    /**
     * constructor, add point cross to the list
     * @param geometries
     */
    public Geometries(Intersectable... geometries){
        add(geometries);
    }

    /**
     * add all point cross to the list
     * @param geometries
     */
    public void add(Intersectable... geometries){
        Collections.addAll(_intersectableList, geometries);
    }

    /**
     * find cross point and add to the list
     * @param ray
     * @return List of cross point
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> result = null;
        for (Intersectable item : _intersectableList) {
            List<Point> itempoints = item.findIntersections(ray);
            if (itempoints != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itempoints);
            }
        }
        return result;
    }

}
