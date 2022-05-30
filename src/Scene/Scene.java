package Scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

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
    private Scene(SceneBuilder builder){
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
        /**
         * func setter type builder
         * @param background
         * @return SceneBuilder
         */
        public SceneBuilder setBackground(Color background) {
            _background = background;
            return this;
        }

        /**
         * func setter type builder
         * @param ambientLight
         * @return SceneBuilder
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            _ambientLight = ambientLight;
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

        /**
         * build Scene
         * @return Scene
         */
        public Scene build(){
            return new Scene(this);
        }
    }
}
