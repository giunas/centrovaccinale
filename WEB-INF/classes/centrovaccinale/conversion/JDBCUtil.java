package centrovaccinale.conversion;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;



public class JDBCUtil {
	public static Document toDocument(ResultSet rs) throws ParserConfigurationException, SQLException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		Element results = doc.createElement("Vaccinazioni");
		doc.appendChild(results);

		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();

		while (rs.next()) {
			Element row = doc.createElement("Vaccinazione");
			results.appendChild(row);

			for (int i = 1; i <= colCount; i++) {
				String columnName = rsmd.getColumnName(i);
				Object value = rs.getObject(i);
								
			    Element node = doc.createElement(columnName);
			    node.appendChild(doc.createTextNode(value.toString()));
			    row.appendChild(node);
			}
		}		
		
		return doc;
	}
	
	public static String serialize(Document doc) {
		StringWriter sw = null;
	    StreamResult sr = null;
		try {
		    DOMSource domSource = new DOMSource(doc);
		    Transformer transformer = TransformerFactory.newInstance().newTransformer();
		    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    sw = new StringWriter();
		    sr = new StreamResult(sw);
		    transformer.transform(domSource, sr);
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
		
		return sw.toString();
	}
	
	public static void validate(Document doc) {
		try {
	        Document document = doc;
	
	        // create a SchemaFactory capable of understanding WXS schemas
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	
	        // load a WXS schema, represented by a Schema instance
	        Source schemaFile = new StreamSource(new File("report.xsd"));
	        Schema schema = factory.newSchema(schemaFile);
	
	        // create a Validator instance, which can be used to validate an instance document
	        Validator validator = schema.newValidator();
	
	        // validate the DOM tree

            validator.validate(new DOMSource(document));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (SAXException e) {
            System.out.println(e.getMessage());
        }
	}
}
