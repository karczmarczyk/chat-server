package pl.karczmarczyk.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 *
 * @author mateusz
 */
public class Config {

    private static Logger log = Logger.getLogger(Config.class);

    private static String SEP = java.io.File.separator;
    private static String CONFIG_FILE = "chat.properties";

    private static Map<String, String> defaultSetting = new HashMap<String, String>();
    private static Properties fileSettings = new Properties();
    boolean fromFile = false;

    static {
	defaultSetting.put("chat.server.host", "localhost"); //ws
	defaultSetting.put("chat.server.port", "8025");
	defaultSetting.put("chat.server.contextPath", "/websockets");
	
	defaultSetting.put("chat.db.user", "postgres");
	defaultSetting.put("chat.db.pass", "postgres");
	defaultSetting.put("chat.db.url", "jdbc:postgresql://localhost:5432/chat");
    }

    public static void readConfig() throws IOException {
	String baseDir = System.getProperty("catalina.base");
	String configFile = baseDir + SEP + "conf" + SEP + CONFIG_FILE;
	File file = new File(configFile);
	if (file.exists()) {
	    log.info("Loading config from 'chat.properties'");
	    FileInputStream inStream = new FileInputStream(file);
	    fileSettings.load(inStream);
	    inStream.close();
	}
	dumpConfig();
    }

    //===========================================================================================
    /**
     * Pobierz wynik jako Boolean
     *
     * @param paramName
     * @return
     */
    public static boolean getAsBool(String paramName) {
	boolean retVal = false;
	String value = System.getProperty(paramName);
	if (null == value) {
	    value = (String) fileSettings.get(paramName);
	}
	if (null == value) {
	    value = defaultSetting.get(paramName);
	}
	if ("true".equals(value)) {
	    retVal = true;
	}
	return retVal;
    }

    /**
     * Pobierz jako STRING
     * @param paramName nazwa parametru
     * @return
     */
    public static String getAsString(String paramName) {
	String value = null;
	value = System.getProperty(paramName);
	if (null == value) {
	    value = (String) fileSettings.get(paramName);
	}
	if (null == value) {
	    value = defaultSetting.get(paramName);
	}
	return value;
    }
	
    /**
     * Pobierz wynik jako Boolean
     * @param paramName
     * @return
     */
    public static int getAsInt(String paramName) {
	int retVal = 0;
	String value = System.getProperty(paramName);
	if (null == value) {
	    value = (String) fileSettings.get(paramName);
	}
	if (null == value) {
	    value = defaultSetting.get(paramName);
	}
	if (null == value) {
	    value = "0";
	}
	if (null != value) {
	    retVal = Integer.parseInt(value);
	}
	return retVal;
    }

    public static void dumpConfig() {
	Set<String> keySet = defaultSetting.keySet();
	String msg = "current config:\n[PROPERTIES]=================================+\n";
	for (String key : keySet) {
	    String val = (String) getAsString(key);
	    if ("chat.db.pass".equals(key)) {
		val = "<secret>";
	    }
	    msg = msg + key + "=" + val + "\n";
	}
	msg = msg + "=============================================";
	log.info(msg);
	System.out.print(msg);
    }

    //===========================================================================================
}
