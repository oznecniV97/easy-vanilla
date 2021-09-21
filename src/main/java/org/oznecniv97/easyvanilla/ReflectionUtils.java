package org.oznecniv97.easyvanilla;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class ReflectionUtils {

    private ReflectionUtils() {}

    private static final Logger log = LogManager.getLogger();

    private static RuntimeException getRuntimeException(Exception e) {
        return new RuntimeException("Schiattata la reflection", e);
    }

    public static String getStringField(Object obj, String fieldName) {
        try {
            return getField(obj, fieldName).get(obj).toString();
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw getRuntimeException(e);
        }
    }

    public static int getIntField(Object obj, String fieldName) {
        try {
            return getField(obj, fieldName).getInt(obj);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw getRuntimeException(e);
        }
    }

    public static boolean getBooleanField(Object obj, String fieldName) {
        try {
            return getField(obj, fieldName).getBoolean(obj);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw getRuntimeException(e);
        }
    }

    public static Field getField(Object obj, String fieldName) throws NoSuchFieldException {
        final var field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

}
