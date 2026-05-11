package cx.rain.mc.inkraft.neoforge.permission;

import cx.rain.mc.inkraft.Inkraft;
import cx.rain.mc.inkraft.api.platform.permission.IPermissionHolder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.server.permission.PermissionAPI;
import net.neoforged.neoforge.server.permission.events.PermissionGatherEvent;
import net.neoforged.neoforge.server.permission.nodes.PermissionNode;
import net.neoforged.neoforge.server.permission.nodes.PermissionTypes;

@EventBusSubscriber(modid = Inkraft.MODID)
public class PermissionHolder implements IPermissionHolder {
    public static final PermissionNode<Boolean> PERMISSION_USE = bool("use", 0);
    public static final PermissionNode<Boolean> PERMISSION_ADMIN = bool("admin", 2);

    private static PermissionNode<Boolean> bool(String name, int defaultLevel) {
        return new PermissionNode<>(Inkraft.MODID, name, PermissionTypes.BOOLEAN,
                (player, uuid, context) -> player != null && hasCommandLevel(player.createCommandSourceStack(), defaultLevel));
    }

    @SubscribeEvent
    public static void registerPermission(PermissionGatherEvent.Nodes event) {
        event.addNodes(PERMISSION_USE);
        event.addNodes(PERMISSION_ADMIN);
    }

    private boolean check(CommandSourceStack source, PermissionNode<Boolean> node) {
        if (source.getEntity() instanceof ServerPlayer player) {
            return PermissionAPI.getPermission(player, node);
        } else {
            if (node == PERMISSION_USE) {
                return hasCommandLevel(source, 0);
            }

            if (node == PERMISSION_ADMIN) {
                return hasCommandLevel(source, 2);
            }
        }

        return false;
    }

    private static boolean hasCommandLevel(CommandSourceStack source, int defaultLevel) {
        return switch (defaultLevel) {
            case 0 -> Commands.LEVEL_ALL.check(source.permissions());
            case 1 -> Commands.LEVEL_MODERATORS.check(source.permissions());
            case 2 -> Commands.LEVEL_GAMEMASTERS.check(source.permissions());
            case 3 -> Commands.LEVEL_ADMINS.check(source.permissions());
            default -> Commands.LEVEL_OWNERS.check(source.permissions());
        };
    }

    @Override
    public boolean couldUse(CommandSourceStack source) {
        return check(source, PERMISSION_USE);
    }

    @Override
    public boolean isAdmin(CommandSourceStack source) {
        return check(source, PERMISSION_ADMIN);
    }
}
