package Scene;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import parser.SceneDescriptor;
import primitives.Color;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import primitives.Double3;
import primitives.Point;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
     *
     * @param builder the builder for the scene
     */
    private Scene(SceneBuilder builder) {
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

    public List<LightSource> getLights() {
        return lights;
    }

    /**
     * inner class for Scene Builder
     */
    public static class SceneBuilder {

        private final String _name;        // Scene Builder name
        private Color _background = Color.BLACK;////Scene Builder background color
        private AmbientLight _ambientLight = new AmbientLight();// //  Scene Builder ambient light
        private Geometries _geometries = new Geometries();////  Scene Builder composite for all geometric object
        private List<LightSource> _lights = new LinkedList<>();   // Scene Builder name
        private SceneDescriptor _senceDesc = new SceneDescriptor();
        // ***************** Constructors ********************** //

        /**
         * Construcor for builder
         *
         * @param name mandatory name
         */
        public SceneBuilder(String name) {
            _name = name;
        }

        // ***************** Setters  ********************** //

        /**
         * func setter type builder
         *
         * @param background
         * @return SceneBuilder
         */
        public SceneBuilder setBackground(Color background) {
            _background = background;
            return this;
        }

        /**
         * func setter type builder
         *
         * @param ambientLight
         * @return SceneBuilder
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            _ambientLight = ambientLight;
            return this;
        }

        /**
         * func setter type builder
         *
         * @param geometries
         * @return SceneBuilder
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            _geometries = geometries;
            return this;
        }

        /**
         * func setter type builder
         *
         * @param lights
         * @return SceneBuilder
         */
        public SceneBuilder setLights(List<LightSource> lights) {
            _lights = lights;
            return this;
        }

        // ***************** class Sence Type Build  ********************** //

        /**
         * build Scene
         * @return Scene
         */
        public Scene build() {
            return new Scene(this);
        }

        /**
         * open xml file
         * send the file to func InitializeFromXMLstring (in packet parser , in class SenceDescriptor) that return Object (SenceDescriptor).
         * use parameter in _senceDesc to put in SceneBuilder fields
         * @param myFile - string
         * @return SceneBuilder
         */
        public SceneBuilder loadSceneFromFile(String myFile) {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            //get document
            Document document = null;//.xml
            try {
                document = builder.parse(new File(myFile));
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Normalize the xml structure
            document.getDocumentElement().normalize();
            _senceDesc = _senceDesc.InitializeFromXMLstring(document);//+.xml
            //geometries
            //sphere
            List<Map<String, String>> spheres = _senceDesc.getSpheres();
            for (int i = 0; i < spheres.size(); i++) {
                for (Map.Entry m : spheres.get(i).entrySet()) {
                    String temp = (String) m.getValue();
                    var temp1 = temp.split(",");
                    var pointS = temp1[0].split(" ");
                    Point p = new Point(Double.valueOf(pointS[0]), Double.valueOf(pointS[1]), Double.valueOf(pointS[2]));
                    Sphere sphere = new Sphere(p, Double.valueOf(((String) m.getValue()).split(",")[1]));
                    _geometries.add(sphere);
                }
            }
            //Triangles
            List<Map<String, String>> triangles = _senceDesc.getTriangles();
            for (int i = 0; i < triangles.size(); i++) {
                for (Map.Entry m : triangles.get(i).entrySet()) {
                    //String temp = (String) m.getValue();
                    String[] pointsThree = ((String) m.getValue()).split(",");
                    String[] pointS1 = pointsThree[0].split(" ");
                    Point p1 = new Point(Double.valueOf(pointS1[0]), Double.valueOf(pointS1[1]), Double.valueOf(pointS1[2]));
                    String[] pointS2 = pointsThree[1].split(" ");
                    Point p2 = new Point(Double.valueOf(pointS2[0]), Double.valueOf(pointS2[1]), Double.valueOf(pointS2[2]));
                    String[] pointS3 = pointsThree[2].split(" ");
                    Point p3 = new Point(Double.valueOf(pointS3[0]), Double.valueOf(pointS3[1]), Double.valueOf(pointS3[2]));
                    Triangle triangle = new Triangle(p1, p2, p3);
                    _geometries.add(triangle);
                }
            }

            //ambientLight
            for (Map.Entry m : _senceDesc.getAmbientLightAttributes().entrySet()) {
                var colorStr = ((String) m.getValue()).split(" ");
                _ambientLight = new AmbientLight(new Color(Double.valueOf(colorStr[0]), Double.valueOf(colorStr[1]), Double.valueOf(colorStr[2])), new Double3(1, 1, 1));
            }

            for (Map.Entry m : _senceDesc.getSceneAttributes().entrySet()) {
                var background = ((String) m.getValue()).split(" ");
                _background = new Color(Double.valueOf(background[0]), Double.valueOf(background[1]), Double.valueOf(background[2]));
            }
            return this;
        }

    }
}
