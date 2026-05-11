package cx.rain.mc.inkraft;

import cx.rain.mc.inkraft.api.platform.permission.IPermissionHolder;
import cx.rain.mc.inkraft.api.platform.storage.IInkPlayerData;
import cx.rain.mc.inkraft.story.function.IStoryFunction;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class InkraftPlatform {
    @ExpectPlatform
    public static IInkPlayerData getPlayerData(ServerPlayer player) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static IPermissionHolder getPermissionManager() {
        throw new AssertionError();
    }
}
