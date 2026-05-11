package cx.rain.mc.inkraft.neoforge;

import cx.rain.mc.inkraft.neoforge.registry.ModAttachments;
import cx.rain.mc.inkraft.api.platform.permission.IPermissionHolder;
import cx.rain.mc.inkraft.neoforge.permission.PermissionHolder;
import cx.rain.mc.inkraft.api.platform.storage.IInkPlayerData;
import net.minecraft.server.level.ServerPlayer;

public class InkraftPlatformImpl {
    private static final IPermissionHolder PERMISSION_MANAGER = new PermissionHolder();

    public static IInkPlayerData getPlayerData(ServerPlayer player) {
        return player.getData(ModAttachments.PLAYER_DATA.get());
    }

    public static IPermissionHolder getPermissionManager() {
        return PERMISSION_MANAGER;
    }
}
