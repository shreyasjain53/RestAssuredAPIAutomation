package commonFunctions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {

	/**
	 * Loads properties from a data.properties file located within the specified
	 * package.
	 *
	 * @param packageName The package name eg: YourClassName.class.getPackageName()
	 * @return Properties object containing the loaded properties.
	 */
	public Properties loadProperties(String packageName) {
		Properties prop = new Properties();
		try {
			String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "java" + File.separator + packageName.replace('.', File.separatorChar)
					+ File.separator + "data.properties";
			System.out.println("data.properites file path :- " + filePath);
			FileInputStream fis = new FileInputStream(filePath);
			prop.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.gc();
		}
		return prop;
	}

	/**
 * Loads environment variables from an XML file based on the specified product name.
 * 
 * <p>This method reads an XML file named "EnvironmentVariables.xml" located in the 
 * root directory of the project. It searches for a product element whose name matches 
 * the provided productName, and then retrieves the URL and API key associated with that product. 
 * The URL and API key are printed to the console once a match is found.</p>
 * 
 * <p>If no matching product is found, no environment variables are loaded.</p>
 * 
 * @param productName The name of the product whose environment variables need to be loaded.
 *                    The product name is case-insensitive.
 * 
 * <p>Note: The function assumes that the XML structure and file are correctly formatted.</p>
 */
	public void loadEnvironmentVariables(String productName) {
		String filePath = System.getProperty("user.dir") + File.separator + "EnvironmentVariables.xml";
		File xmlFile = new File(filePath);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList productList = doc.getElementsByTagName("product");

				for (int i = 0; i < productList.getLength(); i++) {
					Node productNode = productList.item(i);

					if (productNode.getNodeType() == Node.ELEMENT_NODE) {
						Element productElement = (Element) productNode;
						String name = productElement.getElementsByTagName("name").item(0).getTextContent();
						if (name.equalsIgnoreCase(productName)){
						String url = productElement.getElementsByTagName("url").item(0).getTextContent();
						String apiKey = productElement.getElementsByTagName("apiKey").item(0).getTextContent();
						System.out.println("Name: " + name);
						System.out.println("URL: " + url);
						System.out.println("API Key: " + apiKey);
						break;
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.gc();
		}
	}
}
