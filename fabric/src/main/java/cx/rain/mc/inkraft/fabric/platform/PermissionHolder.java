package cx.rain.mc.inkraft.fabric.platform;

import cx.rain.mc.inkraft.api.platform.permission.IPermissionHolder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class PermissionHolder implements IPermissionHolder {

    @Override
    public boolean couldUse(CommandSourceStack source) {
        return Commands.LEVEL_ALL.check(source.permissions());
    }

    @Override
    public boolean isAdmin(CommandSourceStack source) {
        return Commands.LEVEL_GAMEMASTERS.check(source.permissions());
    }
}
