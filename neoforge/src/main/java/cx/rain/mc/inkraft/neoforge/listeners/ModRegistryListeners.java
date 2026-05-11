package cx.rain.mc.inkraft.neoforge.listeners;

import cx.rain.mc.inkraft.Inkraft;
import cx.rain.mc.inkraft.data.story.StoryReloadListener;
import cx.rain.mc.inkraft.listeners.RegistryListeners;
import cx.rain.mc.inkraft.neoforge.registry.ModRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@EventBusSubscriber(modid = Inkraft.MODID)
public class ModRegistryListeners {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        RegistryListeners.onRegisterCommands(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddServerReloadListenersEvent event) {
        event.addListener(StoryReloadListener.INKRAFT_STORY_LOADER, StoryReloadListener.INSTANCE);
    }

    @SubscribeEvent
    public static void onNewRegistry(NewRegistryEvent event) {
        event.register(ModRegistries.STORY_FUNCTIONS);
    }
}
