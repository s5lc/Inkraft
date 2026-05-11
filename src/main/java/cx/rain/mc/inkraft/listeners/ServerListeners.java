package cx.rain.mc.inkraft.listeners;

import cx.rain.mc.inkraft.engine.EngineManager;
import net.minecraft.server.MinecraftServer;

public class ServerListeners {
    public static void onServerTick(MinecraftServer server) {
        EngineManager.getInstance().getTaskManager().tick(server);
    }
}
