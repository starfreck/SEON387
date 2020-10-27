package XSLTExample;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XsltTrasfromation {
 
  private static Document document;
 
  public static void main(String[] args) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 
    File xml = new File("src/XSLTExample/cdcatalog.xml");
    File xsl = new File("src/XSLTExample/cdcatalog.xsl");
 
    DocumentBuilder builder = factory.newDocumentBuilder();
    document = builder.parse(xml);
 
    // Use a Transformer for output
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    StreamSource style = new StreamSource(xsl);
    Transformer transformer = transformerFactory.newTransformer(style);
 
    DOMSource source = new DOMSource(document);
    StreamResult result = new StreamResult(System.out);
    transformer.transform(source, result);
  }
}