package Scene;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import parser.SenceDescriptor;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Scene class : Compound class for all the objects of the 3D world to render
 */
public class Scene {

    private final String name;                 // scene name
    private final Color background;            // background color
    private final AmbientLight ambientLight;   // ambient light
    private final Geometries geometries;       // composite for all geometric object
    private List<LightSource> lights = new LinkedList<>();
    /**
     * Construcor using Builder Pattern
     * @param builder the builder for the scene
     */
    public Scene(SceneBuilder builder){
        name = builder._name;
        background = builder._background;
        ambientLight = builder._ambientLight;
        geometries = builder._geometries;
        lights = builder._lights;
    }

    //getters without Dan permission
    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public List<LightSource>  getLights() {
        return lights;
    }

    /**
     * inner class for Scene Builder
     */
    public static class SceneBuilder {
        private String _filePath; //
        private Scene _scene; //
        private SenceDescriptor senceDesc; //
        private final String _name;        // Scene Builder name
        private Color _background = Color.BLACK;////Scene Builder background color
        private AmbientLight _ambientLight = new AmbientLight();// //  Scene Builder ambient light
        private Geometries _geometries = new Geometries();////  Scene Builder composite for all geometric object
        private List<LightSource> _lights = new LinkedList<>();   // Scene Builder name

        // ***************** Constructors ********************** //
        /**
         * Construcor for builder
         * @param name mandatory name
         */
        public SceneBuilder(String name){
            _name = name;
        }

        // ***************** Setters  ********************** //

        public SceneBuilder setFilePath(String filePath) {
            _filePath = filePath;
            return this;
        }

        public SceneBuilder setBackground(Color background) {
            _background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            _ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setScene(Scene scene) {
            _scene = scene;
            return this;
        }

        /**
         * func setter type builder
         * @param geometries
         * @return SceneBuilder
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            _geometries = geometries;
            return this;
        }

        /**
         * func setter type builder
         * @param lights
         * @return SceneBuilder
         */
        public SceneBuilder setLights(List<LightSource> lights){
            _lights = lights;
            return this;
        }

        // ***************** class Sence Type Build  ********************** //

        /**
         * build Scene
         * @return Scene
         */
        public Scene build(){
            return new Scene(this);
        }

        public void loadSceneFromFile(File myFile){
            senceDesc = senceDesc.InitializeFromXMLstring(myFile.getName());//+.xml
            //geometries
            //sphere
            List<Map<String, String>> spheres = senceDesc.getSpheres();
            for (int i= 0; i<spheres.size(); i++)
            {
                Map<String, String> sphereMap = spheres.get(i);
                String[] pointS = sphereMap.get(0).split(",")[0].split(" ");
                Point p = new Point(Double.valueOf(pointS[0]), Double.valueOf(pointS[1]),Double.valueOf(pointS[2]));
                Sphere sphere = new Sphere(p,Double.valueOf(sphereMap.get(0).split(",")[1]));
                _geometries.add(sphere);
            }
            //Triangles
            List<Map<String, String>> triangles = senceDesc.getTriangles();
            for (int i= 0; i<triangles.size(); i++)
            {
                Map<String, String> triangleMap = triangles.get(i);
                String[] pointsThree = triangleMap.get(0).split(",");
                String[] pointS1 =pointsThree[0].split(" ");
                Point p1 = new Point(Double.valueOf(pointS1[0]), Double.valueOf(pointS1[1]),Double.valueOf(pointS1[2]));
                String[] pointS2 =pointsThree[0].split(" ");
                Point p2 = new Point(Double.valueOf(pointS2[0]), Double.valueOf(pointS2[1]),Double.valueOf(pointS2[2]));
                String[] pointS3 =pointsThree[0].split(" ");
                Point p3 = new Point(Double.valueOf(pointS3[0]), Double.valueOf(pointS3[1]),Double.valueOf(pointS3[2]));
                Triangle triangle = new Triangle(p1,p2,p3);
                _geometries.add(triangle);
            }
            String[] ambientLightS = senceDesc.getAmbientLightAttributes().get(0).split(" ");
            _ambientLight= new AmbientLight(new Color(Double.valueOf(ambientLightS[0]), Double.valueOf(ambientLightS[1]),Double.valueOf(ambientLightS[2])),new Double3(1,1,1));

            String[] background  = senceDesc.getSceneAttributes().get(0).split(" ");
            _background = new Color(Double.valueOf(background[0]), Double.valueOf(background[1]),Double.valueOf(background[2]));
        }
    }
}
