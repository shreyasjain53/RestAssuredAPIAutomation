package commonFunctions;

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
}
