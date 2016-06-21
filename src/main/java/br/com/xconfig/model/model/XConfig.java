package br.com.xconfig.model.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lacau on 15/06/16.
 */
public class XConfig {

    public static final String MULTI_VALUE_SEPARATOR = "|";

    private static final String MULTI_VALUE_SEPARATOR_ESC = "\\|";

    private String key;

    private String value;

    public XConfig(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public List<String> getValues() {
        if(value != null && value.contains(MULTI_VALUE_SEPARATOR)) {
            final List<String> values = new ArrayList<>();
            final String[] splittedValues = value.split(MULTI_VALUE_SEPARATOR_ESC);
            for(String v : splittedValues) {
                values.add(v.trim());
            }

            return values;
        }

        return null;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("{").append("key=\"").append(key).append("\"").append(",value=\"").append(value).append("\"").append("}").toString();
    }
}