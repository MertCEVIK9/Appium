package LifeboxTest;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TakeData {
    private static HashMap<String, String> data;
    private static HashMap<String, String> credential;

    static {
        JSONParser parser = new JSONParser();
        data = new HashMap<>();
        try {
            Object obj = parser.parse(new FileReader("/Users/mertcevik/IdeaProjects/AppiumTest/src/test/resources/MainPage.json"));
            JSONObject jsonObject = (JSONObject) obj;
            for (Object key : jsonObject.keySet()) {
                String name = (String) key;
                String value = (String) jsonObject.get(name);
                data.put(name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String CREDENTIALS_FOLDER_PATH = "/Users/mertcevik/IdeaProjects/AppiumTest/src/test/resources/Credentials/";

    private static HashMap<String, HashMap<String, String>> credentials = new HashMap<>();

    static {
        File credentialsFolder = new File(CREDENTIALS_FOLDER_PATH);
        File[] credentialFiles = credentialsFolder.listFiles();
        if (credentialFiles != null) {
            for (File file : credentialFiles) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    String fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
                    credentials.put(fileName, readJson(file.getAbsolutePath()));
                }
            }
        }
    }

    private static HashMap<String, String> readJson(String filePath) {
        JSONParser parser = new JSONParser();
        HashMap<String, String> credential = new HashMap<>();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            for (Object key : jsonObject.keySet()) {
                String name = (String) key;
                String value = (String) jsonObject.get(name);
                credential.put(name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credential;
    }

    public static String getUser(String user, String field) {
        if (credentials.containsKey(user)) {
            HashMap<String, String> credential = credentials.get(user);
            if (credential.containsKey(field)) {
                return credential.get(field);
            }
        }
        return null;
    }
    public static String getXPath(String name) {
            return data.get(name);
    }




    public static DesiredCapabilities getDesiredCapabilities(String phoneName) {
        JSONParser parser = new JSONParser();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        try (FileReader reader = new FileReader("/Users/mertcevik/IdeaProjects/AppiumTest/src/test/resources/Devices/"+phoneName+".json")) {
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            for (Object key : jsonObject.keySet()) {
                String name = (String) key;
                Object value = jsonObject.get(name);
                desiredCapabilities.setCapability(name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desiredCapabilities;
    }
}

