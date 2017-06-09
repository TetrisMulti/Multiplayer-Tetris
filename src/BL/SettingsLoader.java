package BL;

import javax.swing.text.html.HTMLDocument;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ganleb13 on 12.05.2017.
 */
public class SettingsLoader {

    private final String Path;
    private String WholeSettings;
    private HashMap<String, Integer> hmKeys;             //In dieser Map sind die Keywerte zu den Bewegungen gespeichert #HashtagMap

    public SettingsLoader() {
        Path = System.getProperty("user.dir") +File.separator+ "src" + File.separator + "res" + File.separator + "Settings.properties";
        hmKeys = new HashMap<String, Integer>();
    }

    public void ReadSettings() throws IOException {
        FileReader fr = new FileReader(new File(Path));
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while ((line = br.readLine()) != null)
        {
            String[] splittedLine = line.split("=");
            switch (splittedLine[0])
            {
                case "down": hmKeys.put("down", Integer.parseInt(splittedLine[1]));
                case "left": hmKeys.put("left", Integer.parseInt(splittedLine[1]));
                case "right": hmKeys.put("right", Integer.parseInt(splittedLine[1]));
                case "rotateLeft": hmKeys.put("rotateLeft", Integer.parseInt(splittedLine[1]));
                case "rotateRight": hmKeys.put("rotateRight", Integer.parseInt(splittedLine[1]));
            }
        }
    }

    public void WriteSettings() throws IOException {
        FileWriter fw = new FileWriter(new File(Path));
        BufferedWriter bw = new BufferedWriter(fw);

        for (Iterator it = hmKeys.keySet().iterator();it.hasNext();) {
            String key = (String) it.next();
            int value = (int) hmKeys.get(key);
            bw.write(key+"="+value);
        }
        bw.flush();
        bw.close();
    }

    public void outKeys()
    {
        for (Iterator it = hmKeys.keySet().iterator();it.hasNext();)
        {
            String key = (String) it.next();
            System.out.println(key+"="+hmKeys.get(key));
            System.out.println(KeyEvent.getKeyText(hmKeys.get(key)));
        }
    }

    public static void main(String[] args) {
        try {
            SettingsLoader se = new SettingsLoader();
            se.ReadSettings();
            se.outKeys();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
