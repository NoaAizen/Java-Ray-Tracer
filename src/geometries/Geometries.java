package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> _intersectableList=new LinkedList<>();



    public Geometries(Intersectable... geometries){
        add(geometries);
    }
    public void add(Intersectable... geometries){
        Collections.addAll(_intersectableList, geometries);
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
