package cx.rain.mc.inkraft.api.platform.permission;

import net.minecraft.commands.CommandSourceStack;

public interface IPermissionHolder {
    boolean couldUse(CommandSourceStack source);

    boolean isAdmin(CommandSourceStack source);
}
