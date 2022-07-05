package parser;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import primitives.Point;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SenceDescriptor {
    private Map<String,String> sceneAttributes;
    private  Map<String,String> ambientLightAttributes;
    private List< Map<String,String>> spheres;
    private List< Map<String,String>> triangles;
    // ***************** Constructors ********************** //

    /**
     *
     * @param sceneAttributes
     * @param ambientLightAttributes
     * @param spheres
     * @param triangles
     */
    public SenceDescriptor(Map<String, String> sceneAttributes, Map<String, String> ambientLightAttributes, List<Map<String, String>> spheres, List<Map<String, String>> triangles) {
        this.sceneAttributes = sceneAttributes;
        this.ambientLightAttributes = ambientLightAttributes;
        this.spheres = spheres;
        this.triangles = triangles;
    }
    // ***************** getter ********************** //
    public Map<String, String> getSceneAttributes() {
        return sceneAttributes;
    }

    public Map<String, String> getAmbientLightAttributes() {
        return ambientLightAttributes;
    }

    public List<Map<String, String>> getSpheres() {
        return spheres;
    }

    public List<Map<String, String>> getTriangles() {
        return triangles;
    }

    public SenceDescriptor InitializeFromXMLstring(String xmlText) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            //get document
            Document document = builder.parse(new File(xmlText));//.xml
            //Normalize the xml structure
            //document.getDocumentElement().normalize();
            var scene = document.getDocumentElement();
            //get all data
            //background-color
            sceneAttributes = new HashMap<>();
            sceneAttributes.put("0", scene.getAttribute("background-color"));
            //ambient-light color
            ambientLightAttributes = new HashMap<>();
            ambientLightAttributes.put("0", scene.getAttribute("ambient-light color"));
            //geometries
            var geometries = document.getElementsByTagName("geometries");
            //sphere
            Map<String, String> sphere = new HashMap<>();
            int countSphere = 0;
            String dataSphere;
            // triangle
            Map<String, String> triangle = new HashMap<>();
            int countTriangle = 0;
            String dataTriangle;
            for (int i = 0; i < geometries.getLength(); i++) {
                var node = geometries.item(i);
                if (node.hasAttributes()) {
                    String attribute = node.getNodeName();
                    var element1 = (Element) node;
                    switch (attribute) {
                        //sphere
                        case "sphere":
                            dataSphere = element1.getAttribute("center") + "," + element1.getAttribute("radius");
                            sphere.put(String.valueOf(countSphere), dataSphere);
                            countSphere++;
                            spheres.add(sphere);
                            //triangle
                        case "triangle":
                            dataTriangle = element1.getAttribute("p0") + "," + element1.getAttribute("p1") + "," + element1.getAttribute("p2");
                            triangle.put(String.valueOf(countTriangle), dataTriangle);
                            countTriangle++;
                            triangles.add(triangle);
                        default:
                            break;
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return new SenceDescriptor(sceneAttributes,ambientLightAttributes,spheres,triangles);
    }
}
