package br.com.xconfig.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * Created by lacau on 15/06/16.
 */
public class PropertiesRepository {

    private static final String FILE_NAME = "/xconfig.properties";

    private Properties props;

    private static final class Holder {
        static final PropertiesRepository INSTANCE = new PropertiesRepository();
    }

    private PropertiesRepository() {
        loadProperties();
    }

    public static PropertiesRepository getInstance() {
        return PropertiesRepository.Holder.INSTANCE;
    }

    private void loadProperties() {
        props = new Properties();

        final File propsFile = getPropertiesFile();
        try(InputStream inputStream = new FileInputStream(propsFile)) {
            props.load(inputStream);
        } catch(IOException e) {
            throw new IllegalStateException(String.format("Error on read/write. file=%s", FILE_NAME), e);
        }
    }

    public String get(String key) {
        Object o = props.get(key);
        return o != null ? (String) o : null;
    }

    public void put(String key, String value) {
        props.setProperty(key, value);
        saveProps();
    }

    public void remove(String key) {
        props.remove(key);
        saveProps();
    }

    public void reload() {
        loadProperties();
    }

    private void saveProps() {
        final File propsFile = getPropertiesFile();

        try(OutputStream out = new FileOutputStream(propsFile)) {
            props.store(out, null);
        } catch(IOException e) {
            throw new IllegalStateException(String.format("Error on read/write. file=%s", FILE_NAME), e);
        }
    }

    private File getPropertiesFile() {
        URL url = this.getClass().getResource(FILE_NAME);
        if(url == null) {
            throw new MissingResourceException(String.format("Could not found file in classpath. file=%s", FILE_NAME), null, null);
        }

        try {
            return new File(url.toURI());
        } catch(URISyntaxException e) {
            throw new IllegalStateException(String.format("Error on parse url to uri. url=%s", url), e);
        }
    }
}