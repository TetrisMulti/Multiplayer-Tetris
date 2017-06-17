package BL;

import javax.swing.text.html.HTMLDocument;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ganleb13 on 12.05.2017.
 * This class handles the Setting Saving and loading feature
 */
public class SettingsLoader {

    private final String Path;
    private HashMap<String, Integer> hmKeys;

    /**
     * Constructor to initialize the Property path and the Hashmap for the settings
     */
    public SettingsLoader() {
        Path = System.getProperty("user.dir") +File.separator+ "src" + File.separator + "res" + File.separator + "Settings.properties";
        hmKeys = new HashMap<String, Integer>();
    }

    /**
     * Method to read the Settings from the property file
     * key(function) and value(keyvalue) will be saved into the hmKeys
     * @throws IOException
     */
    public void ReadSettings() throws IOException {
        FileReader fr = new FileReader(new File(Path));
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while ((line = br.readLine()) != null)
        {
            String[] splittedLine = line.split("=");
            switch (splittedLine[0])
            {
                case "down": hmKeys.put("down", Integer.parseInt(splittedLine[1]));break;
                case "left": hmKeys.put("left", Integer.parseInt(splittedLine[1]));break;
                case "right": hmKeys.put("right", Integer.parseInt(splittedLine[1]));break;
                case "rotateLeft": hmKeys.put("rotateLeft", Integer.parseInt(splittedLine[1]));break;
                case "rotateRight": hmKeys.put("rotateRight", Integer.parseInt(splittedLine[1]));break;
            }
        }
    }

    /**
     * Method to write the setting onto the property file
     *
     * @param hmNewKeys --> Hashmap of Keysettings to write onto the property file
     * @throws IOException
     */
    public void WriteSettings(HashMap<String, Integer> hmNewKeys) throws IOException {
        FileWriter fw = new FileWriter(new File(Path));
        BufferedWriter bw = new BufferedWriter(fw);

        for (Iterator it = hmNewKeys.keySet().iterator();it.hasNext();) {
            String key = (String) it.next();
            int value = (int) hmNewKeys.get(key);
            bw.write(key+"="+value+"\n");
        }
        bw.flush();
        bw.close();
    }

    /**
     * Method that returns the Keys fresh from the Property file
     * @return --> Hashmap of Keysettings
     */
    public HashMap<String, Integer> getHmKeys() {
        try {
            ReadSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hmKeys;
    }
}
