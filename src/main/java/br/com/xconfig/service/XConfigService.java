package br.com.xconfig.service;

import br.com.xconfig.exception.ConfigNotFoundException;
import br.com.xconfig.model.model.XConfig;
import br.com.xconfig.repository.PropertiesRepository;

/**
 * Created by lacau on 15/06/16.
 */
public final class XConfigService {

    private static final class Holder {
        static final XConfigService INSTANCE = new XConfigService();
    }

    private XConfigService() {
    }

    public static XConfigService getInstance() {
        return Holder.INSTANCE;
    }

    public XConfig get(String key) {
        validateKeyArgument(key);

        String value = PropertiesRepository.getInstance().get(key);
        if(value == null) {
            throw new ConfigNotFoundException(String.format("Config not found. key=%s", key));
        }

        return new XConfig(key, value);
    }

    public XConfig put(String key, String value) {
        validateKeyArgument(key);
        if(value == null) {
            throw new IllegalArgumentException("Parameter value can not be null");
        }

        PropertiesRepository.getInstance().put(key, value);

        return new XConfig(key, value);
    }

    public void remove(String key) {
        validateKeyArgument(key);

        PropertiesRepository.getInstance().remove(key);
    }

    public void reload() {
        PropertiesRepository.getInstance().reload();
    }

    private void validateKeyArgument(String key) {
        if(key == null) {
            throw new IllegalArgumentException("Parameter key can not be null");
        }
    }
}