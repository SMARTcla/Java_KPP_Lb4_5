package KPP.lab5.Third;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Test {
    public static final String filePathXML = "C:\\Users\\sosor\\IdeaProjects\\JavaProjects\\src\\KPP\\lab5\\ForXmlTestFile\\test1.xml";
    public static final String filePathXSD = "C:\\Users\\sosor\\IdeaProjects\\JavaProjects\\src\\KPP\\lab5\\ForXmlTestFile\\test1.xsd";

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(filePathXSD));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setSchema(schema);
        dbf.setValidating(false);
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);

        File xmlFile = new File(filePathXML);

        DocumentBuilder builder = dbf.newDocumentBuilder();
        builder.parse(xmlFile);

        System.out.println("End of validation");

    }
}
