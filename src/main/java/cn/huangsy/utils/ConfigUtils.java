package cn.huangsy.utils;

import io.vavr.Lazy;
import org.eclipse.microprofile.config.ConfigProvider;

public class ConfigUtils {

    private static final Lazy<Boolean> IS_DEV_MODE = Lazy.of(() ->
            ConfigProvider.getConfig().getOptionalValue("application.dev.mode", Boolean.class).orElse(false));

    public static boolean isDevMode() {
        return IS_DEV_MODE.get();
    }
}
