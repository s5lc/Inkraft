package cx.rain.mc.inkraft.neoforge.listeners;

import cx.rain.mc.inkraft.Inkraft;
import cx.rain.mc.inkraft.listeners.ServerListeners;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = Inkraft.MODID)
public class ModServerListeners {
    @SubscribeEvent
    public static void afterServerTick(ServerTickEvent.Post event) {
        ServerListeners.onServerTick(event.getServer());
    }
}
