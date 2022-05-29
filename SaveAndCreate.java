package KPP.lab5.Sec;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveAndCreate {
    public DTPArr dtpArr;
    public List<DTPArr> list = new ArrayList<>();
    public DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    public DocumentBuilder builder;
    public XPathFactory factory = XPathFactory.newInstance();
    public XPath xPath = factory.newXPath();
    public Document doc;

    public SaveAndCreate() throws ParserConfigurationException {
        this.builder = builderFactory.newDocumentBuilder();
    }

    public void setDocument(String fileName) throws IOException, SAXException {
        FileInputStream file = new FileInputStream(fileName);
        doc = builder.parse(file);
    }

    public void print() {
        String region = null;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getRegName().equals(region)) {
                System.out.println("\nRegion: " + list.get(i).getRegName());
            }
            System.out.println(list.get(i).toString());
            region = list.get(i).getRegName();
        }
    }

    public void parseDoc() {
        String region = null;
        String districtString;
        String cord_lString;
        String cord_wString;

        NodeList district = null;
        NodeList cord_l = null;
        NodeList cord_w = null;

        try {
            region = xPath.evaluate(".//dtpCardList/regName", doc);
            district = (NodeList) xPath.evaluate(".//tab/district", doc, XPathConstants.NODESET);
            cord_l = (NodeList) xPath.evaluate(".//tab/infoDtp/COORD_L", doc, XPathConstants.NODESET);
            cord_w = (NodeList) xPath.evaluate(".//tab/infoDtp/COORD_W", doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < district.getLength(); i++) {
            districtString = district.item(i).getTextContent();
            cord_lString = cord_l.item(i).getTextContent();
            cord_wString = cord_w.item(i).getTextContent();
            dtpArr = new DTPArr(region, districtString, cord_lString, cord_wString);
            list.add(dtpArr);
        }
    }



    public void createXML() {

        doc = builder.newDocument();
        Element root = doc.createElement("dtpCardList");
        Element rootAccident;
        Element elem;

        for (int i = 0; i < list.size(); i++) {
            rootAccident = createElem("accident");

            elem = createElem("regName");
            elem.setTextContent(list.get(i).getRegName());
            rootAccident.appendChild(elem);

            elem = createElem("district");
            elem.setTextContent(list.get(i).getDistrict());
            rootAccident.appendChild(elem);

            elem = createElem("COORD_L");
            elem.setTextContent(list.get(i).getCOORD_L());
            rootAccident.appendChild(elem);

            elem = createElem("COORD_W");
            elem.setTextContent(list.get(i).getCOORD_W());
            rootAccident.appendChild(elem);
            root.appendChild(rootAccident);
        }
        doc.appendChild(root);
    }

    public Element createElem(String name){
        Element elem  = doc.createElement(name);
        return elem;
    }

    public void saveXML(){
        Transformer trf;
        DOMSource src;
        FileOutputStream file;

        try {
            trf = TransformerFactory.newInstance().newTransformer();
            trf.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
            trf.setOutputProperty(OutputKeys.INDENT, "yes");
            trf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            src = new DOMSource(doc);
            file = new FileOutputStream("C:\\Users\\sosor\\IdeaProjects\\JavaProjects\\src\\KPP\\lab5\\ForXmlTestFile\\test1.xml");

            StreamResult result = new StreamResult(file);
            trf.transform(src, result);

        } catch (FileNotFoundException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
