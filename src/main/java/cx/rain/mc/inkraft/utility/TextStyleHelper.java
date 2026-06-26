package cx.rain.mc.inkraft.utility;

import net.kyori.adventure.platform.modcommon.MinecraftAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.ParsingException;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextStyleHelper {
    private static final Logger log = LoggerFactory.getLogger(TextStyleHelper.class);
    private static final MiniMessage MINI_MESSAGE = MiniMessage.builder()
        .tags(StandardTags.defaults())
        .strict(false)
        .build();
    private static final LegacyComponentSerializer LEGACY_AMPERSAND = LegacyComponentSerializer.builder()
        .character(LegacyComponentSerializer.AMPERSAND_CHAR)
        .hexColors()
        .hexCharacter('H')
        .build();
    private static final LegacyComponentSerializer LEGACY_SECTION = LegacyComponentSerializer.builder()
        .extractUrls()
        .hexColors()
        .useUnusualXRepeatedCharacterHexFormat()
        .build();

    public static Component parseStyle(String text, HolderLookup.Provider registries) {
        try {
            var component = MINI_MESSAGE.deserialize(text);
            return MinecraftAudiences.nonWrappingSerializer(() -> registries).serialize(component);
        } catch (RuntimeException ex) {
            if (ex instanceof ParsingException pex) {
                var detailMessage = pex.detailMessage();
                if (detailMessage != null && detailMessage.contains("Legacy formatting codes")) {
                    // Legacy formatting code (§) is not supported by minimessage. Fallback silently.
                    var component = LEGACY_SECTION.deserialize(text);
                    return MinecraftAudiences.nonWrappingSerializer(() -> registries).serialize(component);
                }
            }
            log.warn("Failed to parse MiniMessage text: {}", text, ex);
            return Component.literal(text);
        }
    }
}
