package parser;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneDescriptor {
    /**
     * Fields background
     */
    private Map<String,String> sceneAttributes;
    /**
     * Fields ambientLight
     */
    private Map<String,String> ambientLightAttributes;
    /**
     * Fields of sphere
     */
    private List<Map<String,String>> spheres = new ArrayList<>();
    /**
     * Fields of triangle
     */
    private List<Map<String,String>> triangles = new ArrayList<>();
    // ***************** Constructors ********************** //

    /**
     * Constructors
     * @param sceneAttributes
     * @param ambientLightAttributes
     * @param spheres
     * @param triangles
     */
    public SceneDescriptor(Map<String, String> sceneAttributes, Map<String, String> ambientLightAttributes, List<Map<String, String>> spheres, List<Map<String, String>> triangles) {
        this.sceneAttributes = sceneAttributes;
        this.ambientLightAttributes = ambientLightAttributes;
        this.spheres = spheres;
        this.triangles = triangles;

    }

    /**
     * Constructors empty
     */
    public SceneDescriptor() {

    }

    // ***************** getter ********************** //

    /**
     * func getter
     * @return Map<String, String>
     */
    public Map<String, String> getSceneAttributes() {
        return sceneAttributes;
    }
    /**
     * func getter
     * @return Map<String, String>
     */
    public Map<String, String> getAmbientLightAttributes() {
        return ambientLightAttributes;
    }
    /**
     * func getter
     * @return  List<Map<String, String>>
     */
    public List<Map<String, String>> getSpheres() {
        return spheres;
    }
    /**
     * func getter
     * @return  List<Map<String, String>>
     */
    public List<Map<String, String>> getTriangles() {
        return triangles;
    }

    /**
     * get file, take the data on the file and put them in object (SceneDescriptor) Fields
     * @param document
     * @return SceneDescriptor
     */
    public SceneDescriptor InitializeFromXMLstring(Document document) {
            //Normalize the xml structure
            //document.getDocumentElement().normalize();
        var scene = document.getDocumentElement();
            //get all data
        //background-color
        sceneAttributes = new HashMap<>();
        sceneAttributes.put("0", scene.getAttribute("background-color"));
            //ambient-light color

        ambientLightAttributes = new HashMap<>();
            //ambientLightAttributes.put("0", scene.getElementsByTagName("ambient-light color"));
        var list = scene.getElementsByTagName("ambient-light");
        var ambientLight = list.item(0);
        var element = (Element) ambientLight;
        ambientLightAttributes.put("0",((Element) ambientLight).getAttribute("color"));
        //sphere
        Map<String, String> sphere = new HashMap<String,String>();
        int countSphere = 0;
        String dataSphere;
        // triangle
        Map<String, String> triangle = new HashMap<String,String>();
        int countTriangle = 0;
        String dataTriangle;
        //geometries
        NodeList geometriesList = document.getElementsByTagName("geometries");
        for(int i = 0; i <geometriesList.getLength(); i++) {
            Node geometries = geometriesList.item(i);
            if(geometries.getNodeType() == Node.ELEMENT_NODE) {
                NodeList geometryDetails =  geometries.getChildNodes();
                for(int j = 0; j < geometryDetails.getLength(); j++){
                    Node geometry = geometryDetails.item(j);
                    if(geometry.getNodeType() == Node.ELEMENT_NODE) {
                        Element geometryElement = (Element) geometry;
                        switch (geometryElement.getTagName()) {
                            //sphere
                            case "sphere":
                                dataSphere = geometryElement.getAttribute("center") + "," + geometryElement.getAttribute("radius");
                                sphere.put(String.valueOf(countSphere), dataSphere);
                                sphere.entrySet();
                                countSphere++;
                                break;
                            //triangle
                            case "triangle":
                                dataTriangle = geometryElement.getAttribute("p0") + "," + geometryElement.getAttribute("p1") + "," + geometryElement.getAttribute("p2");
                                triangle.put(String.valueOf(countTriangle), dataTriangle);
                                countTriangle++;
                                triangle.entrySet();
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        spheres.add(sphere);
        triangles.add(triangle);
        return new SceneDescriptor(sceneAttributes,ambientLightAttributes,spheres,triangles);
    }
}
