package DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ganleb13 on 14.03.2017.
 * In this class we load the Database-Properties from the database-property file
 */
public class PropertyLoader {

    public static final String PROPERTYFILE = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "res" + File.separator + "DBProperties.property";

    public static Properties loadProperties() throws FileNotFoundException, IOException
    {
        Properties prop = new Properties();
        prop.load(new FileInputStream(PROPERTYFILE));
        return prop;
    }

    /**
    public static void main(String[] args) {
        try {
            Properties prop = loadProperties();

            System.out.println(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
     */
}
