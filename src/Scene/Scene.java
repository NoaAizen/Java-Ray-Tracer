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
 * Scene class represents the 3D world to be rendered, containing geometries, lights, and other properties.
 *
 * Authors: Noga Jacobs and Noa
 */
public class Scene {

    private final String name;                 // scene name
    private final Color background;            // background color
    private final AmbientLight ambientLight;   // ambient light
    private final Geometries geometries;       // composite for all geometric object
    private List<LightSource> lights = new LinkedList<>(); // list of light sources

    /**
     * Private constructor used by the SceneBuilder to construct the Scene object.
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

    /**
     * Get the name of the scene.
     *
     * @return the scene name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the background color of the scene.
     *
     * @return the background color
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Get the ambient light of the scene.
     *
     * @return the ambient light
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    /**
     * Get the composite of all geometric objects in the scene.
     *
     * @return the geometries composite
     */
    public Geometries getGeometries() {
        return geometries;
    }

    /**
     * Get the list of light sources in the scene.
     *
     * @return the list of light sources
     */
    public List<LightSource> getLights() {
        return lights;
    }

    /**
     * Nested static class for Scene Builder.
     */
    public static class SceneBuilder {

        private final String _name;  // SceneBuilder name
        private Color _background = Color.BLACK; // SceneBuilder background color
        private AmbientLight _ambientLight = new AmbientLight(); // SceneBuilder ambient light
        private Geometries _geometries = new Geometries(); // SceneBuilder composite for all geometric objects
        private List<LightSource> _lights = new LinkedList<>();   // SceneBuilder list of light sources
        private SceneDescriptor _senceDesc = new SceneDescriptor();
        // ***************** Constructors ********************** //

        /**
         * Constructor for the SceneBuilder.
         *
         * @param name the mandatory name of the scene
         */
        public SceneBuilder(String name) {
            _name = name;
        }

        // ***************** Setters  ********************** //

        /**
         * Set the background color of the scene.
         *
         * @param background the background color to set
         * @return the SceneBuilder with the updated background color
         */
        public SceneBuilder setBackground(Color background) {
            _background = background;
            return this;
        }

        /**
         * Set the ambient light of the scene.
         *
         * @param ambientLight the ambient light to set
         * @return the SceneBuilder with the updated ambient light
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            _ambientLight = ambientLight;
            return this;
        }

        /**
         * Set the composite for all geometric objects in the scene.
         *
         * @param geometries the composite for geometric objects to set
         * @return the SceneBuilder with the updated geometries composite
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            _geometries = geometries;
            return this;
        }

        /**
         * Set the list of light sources in the scene.
         *
         * @param lights the list of light sources to set
         * @return the SceneBuilder with the updated list of light sources
         */
        public SceneBuilder setLights(List<LightSource> lights) {
            _lights = lights;
            return this;
        }

        // ***************** class Scene Type Build  ********************** //

        /**
         * Build the Scene object using the values set in the SceneBuilder.
         *
         * @return the built Scene object
         */
        public Scene build() {
            return new Scene(this);
        }

        /**
         * Load the scene from an XML file using SceneDescriptor and initialize the SceneBuilder fields.
         *
         * @param myFile the path to the XML file
         * @return the SceneBuilder with the updated fields
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
