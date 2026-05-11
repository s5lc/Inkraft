package cx.rain.mc.inkraft;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.Properties;

@Slf4j
public class Inkraft {
    public static final String MODID = "inkraft";
    public static final String VERSION;
    public static final ZonedDateTime BUILD_TIME;

    static {
        var properties = new Properties();
        String version;
        ZonedDateTime buildTime;
        try {
            properties.load(Inkraft.class.getResourceAsStream("/build_info.properties"));
            version = properties.getProperty("mod_version") + "+mc" + properties.getProperty("minecraft_version");
            buildTime = ZonedDateTime.parse(properties.getProperty("build_time"));
        } catch (Exception ignored) {
            version = "Unknown";
            buildTime = null;
        }
        BUILD_TIME = buildTime;
        VERSION = version;
    }

    public static void init() {
        log.info("Initializing Inkraft. Ver: {}, Build at: {}", VERSION, BUILD_TIME != null ? BUILD_TIME : "B.C. 3200");
    }
}
