package br.com.xconfig.exception;

/**
 * Created by lacau on 15/06/16.
 */
public class ConfigNotFoundException extends RuntimeException {

    public ConfigNotFoundException(String message) {
        super(message);
    }
}