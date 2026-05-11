package cx.rain.mc.inkraft.neoforge.registry;

import cx.rain.mc.inkraft.Inkraft;
import cx.rain.mc.inkraft.neoforge.storage.InkPlayerDataNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Inkraft.MODID);

    public static final Supplier<AttachmentType<InkPlayerDataNeoForge>> PLAYER_DATA = ATTACHMENT_TYPES.register("player_data",
            () -> AttachmentType.serializable(InkPlayerDataNeoForge::new)
                    .copyOnDeath()
                    .build());

    public static void register(IEventBus bus) {
        ATTACHMENT_TYPES.register(bus);
    }
}
