package KPP.lab5;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Sax {
    private static final String fileName = "C:\\Users\\sosor\\IdeaProjects\\JavaProjects\\src\\KPP\\lab5\\test.xml";
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        DefaultHandler handler = new DefaultHandler(){
            @Override
            public void startDocument() {
                System.out.println("SAX Parser Without Validation");
                System.out.println("----StartDoc----");
            }

            @Override
            public void endDocument() {
                System.out.println("----EndDoc----");
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                System.out.println("\tStart Element: (" + qName + ") proccessing");
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                System.out.println("\tEnd Element: (" + qName +") proccessing");
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                String info = new String(ch, start, length);
                info = info.replace("\n","").trim();
                if (!info.isEmpty()){
                    System.out.println("\t\tChar(info): " + info.trim());
                }
            }
        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File(fileName), handler);
    }
}
