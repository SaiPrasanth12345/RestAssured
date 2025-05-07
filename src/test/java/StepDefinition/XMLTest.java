package StepDefinition;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import io.cucumber.java.en.Given;

public class XMLTest {
	
	public Document doc;
	
	@Given("Generate an XML File")
	public void generate_an_xml_file() throws Exception {
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder=   dbfactory.newDocumentBuilder();
		doc = dbuilder.newDocument();
		
		// Generating XML
		Element rootElement = doc.createElement("cars");
		doc.appendChild(rootElement);
		
		Element superCars = doc.createElement("supercars");
		rootElement.appendChild(superCars);
		// Adding Attribute
		Attr companyattr= doc.createAttribute("company");
		companyattr.setValue("Ferrari");
		superCars.setAttributeNode(companyattr);
		
		for (int i=0; i<10; i++) {
			Element carname = doc.createElement("carname");
			superCars.appendChild(carname);
			// Adding TextNode
			carname.appendChild(doc.createTextNode("Ferrari "+ String.valueOf(100+i)));
			// Adding Attribute
			Attr typeAttr = doc.createAttribute("type");
			typeAttr.setValue("Sports");
			carname.setAttributeNode(typeAttr);
		}
		
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		// print Result in console
		StreamResult consoleRes = new StreamResult(System.out);
		transformer.transform(source, consoleRes);
		
		//writing XML into a file
		File f = new File("src//test//resources//Ferrari.xml");
		StreamResult res = new StreamResult(f);
		transformer.transform(source, res);
		
	}

}
