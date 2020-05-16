package eu.mrndesign.matned.jdbc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MysqlConnectionParameters {

    private final static String MySQL_PROPERTIES_FILENAME = "/jdbc.properties";
    private String username;
    private String password;
    private String databaseName;
    private String databaseHost;
    private String databasePort;

    public MysqlConnectionParameters() {
        init();
    }

    private void init() {
        try {
            Properties propertiesHolder = loadProperties();

            username = propertiesHolder.getProperty("database.jdbc.username");
            password = propertiesHolder.getProperty("database.jdbc.password");
            databaseName = propertiesHolder.getProperty("database.jdbc.name");
            databaseHost = propertiesHolder.getProperty("database.jdbc.host");
            databasePort = propertiesHolder.getProperty("database.jdbc.port");

        } catch (IOException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
    }

    private Properties loadProperties() throws IOException {
        Properties properties = new Properties();

        InputStream jdbcPropertiesStream = this.getClass().getResourceAsStream(MySQL_PROPERTIES_FILENAME);
        if(jdbcPropertiesStream == null){
            System.err.println("No resource file. Missing file name: '"+MySQL_PROPERTIES_FILENAME+"'");
            System.exit(99);
        }
        properties.load(jdbcPropertiesStream);
        return properties;
    }

    public static String getMySQL_PROPERTIES_FILENAME() {
        return MySQL_PROPERTIES_FILENAME;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getDatabasePort() {
        return databasePort;
    }
}
