package renderer;

import static org.junit.jupiter.api.Assertions.*;

import geometries.Intersectable;
import geometries.Plane;
import org.junit.jupiter.api.Test;

import primitives.*;

import java.util.List;

class IntegrationTests {

    private int SumPointCross(Camera camera, Intersectable g, int nx, int ny){
        int counter = 0;
        for(int i = 0; i < ny; i++)
        {
            for (int j=0; j<nx; j++)
            {
                Ray ray = camera.constructRay(nx, ny, j, i);
                var crossPoint = g.findIntersections(ray);
                if(crossPoint != null)
                    counter += crossPoint.size();
                else
                    counter += 0;
            }
        }
        return counter;
   }
}