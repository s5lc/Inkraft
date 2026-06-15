package cx.rain.mc.inkraft.utility;

import net.kyori.adventure.platform.modcommon.MinecraftAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
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

    public static Component parseStyle(String literalText, HolderLookup.Provider registries) {
        try {
            var adventureComponent = MINI_MESSAGE.deserialize(literalText);
            return MinecraftAudiences.nonWrappingSerializer(() -> registries).serialize(adventureComponent);
        } catch (RuntimeException ex) {
            log.warn("Failed to parse MiniMessage text: {}", literalText, ex);
            return Component.literal(literalText);
        }
    }
}
