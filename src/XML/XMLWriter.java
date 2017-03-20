package XML;

import Beans.Score;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by ganleb13 on 17.03.2017.
 * In this class we will write and save our Score-XML File
 */
public class XMLWriter {

    private static final String FILENAME = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "res" + File.separator + "Scores.xml";



    public static void XMLSave(LinkedList<Score> scList) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("tetrisscores");
        doc.appendChild(rootElement);

        //Insert all Scores into the XML
        for (Score sc:scList) {
            System.out.println("HALLLOOOOO");
            Element score = doc.createElement("score");
            Element user = doc.createElement("username");
            user.setTextContent(sc.getUser());
            Element points = doc.createElement("points");
            points.setTextContent(""+sc.getScore());
            score.appendChild(user);
            score.appendChild(points);
        }

        Transformer trans = TransformerFactory.newInstance().newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(System.out);

        trans.transform(source, result);

        System.out.println("Stats saved");

    }
    //In this static Method we will load the XML Scores and save it to a LinkedList<Score>
    public static LinkedList<Score> XMLLoad() throws ParserConfigurationException, IOException, SAXException {
        File fxmlFile = new File(FILENAME);
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
        Document doc = dbuilder.parse(fxmlFile);
        LinkedList<Score> liList = new LinkedList<>();

        doc.getDocumentElement().normalize();       //To normalize the XML Document

        String node = doc.getDocumentElement().getNodeName();       //To get the first element in the XML file
        if (node.equals("tetrisscores"))
        {
            NodeList nlScores = doc.getElementsByTagName("score");
            for (int i = 0; i<nlScores.getLength(); i++)
            {
                Node n = nlScores.item(i);
                //System.out.println(n.getNodeName());

                if (n.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element el = (Element)n;
                    Score sc = new Score(el.getElementsByTagName("username").item(0).getTextContent(),
                            Integer.parseInt(el.getElementsByTagName("points").item(0).getTextContent()));
                    //System.out.println(sc.toString());
                    liList.add(sc);
                }
            }
        }



        return liList;
    }

    public static void main(String[] args) {
        try {
            XMLLoad();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    public static void AddScore()
    {

    }
}
