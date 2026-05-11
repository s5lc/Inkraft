package cx.rain.mc.inkraft.fabric;

import cx.rain.mc.inkraft.api.platform.permission.IPermissionHolder;
import cx.rain.mc.inkraft.fabric.bridge.IInkraftServerPlayer;
import cx.rain.mc.inkraft.fabric.platform.PermissionHolder;
import cx.rain.mc.inkraft.api.platform.storage.IInkPlayerData;
import net.minecraft.server.level.ServerPlayer;

public class InkraftPlatformImpl {
    private static final IPermissionHolder PERMISSION_MANAGER = new PermissionHolder();

    public static IInkPlayerData getPlayerData(ServerPlayer player) {
        return ((IInkraftServerPlayer) player).inkraft$getPlayerData();
    }

    public static IPermissionHolder getPermissionManager() {
        return PERMISSION_MANAGER;
    }
}
