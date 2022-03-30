package Scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * Scene class : Compound class for all the objects of the 3D world to render
 */
public class Scene {

    private final String _name;                 // scene name
    private final Color _background;            // background color
    private final AmbientLight _ambientLight;   // ambient light
    private final Geometries _geometries;       // composite for all geometric object

    /**
     * Construcor using Builder Pattern
     * @param builder the builder for the scene
     */
    private Scene(SceneBuilder builder){
        _name = builder._name;
        _background = builder._background;
        _ambientLight = builder._ambientLight;
        _geometries = builder._geometries;
    }

    //getters without Dan permission

    public String getName() {
        return _name;
    }

    public Color getBackground() {
        return _background;
    }

    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    /**
     * inner class for Scene Builder
     */
    public static class SceneBuilder {

        private final String _name;
        private Color _background = Color.BLACK;
        private AmbientLight _ambientLight = new AmbientLight();
        private Geometries _geometries = new Geometries();

        /**
         * Construcor for builder
         * @param name mandatory name
         */
        public SceneBuilder(String name){
            _name = name;
        }
        //chaining methods

        public SceneBuilder setBackground(Color background) {
            _background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            _ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            _geometries = geometries;
            return this;
        }
        public Scene build(){
            return new Scene(this);
        }
    }
}
