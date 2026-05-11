package cx.rain.mc.inkraft.listeners;

import com.mojang.brigadier.CommandDispatcher;
import cx.rain.mc.inkraft.command.InkraftCommand;
import net.minecraft.commands.CommandSourceStack;

public class RegistryListeners {
    public static void onRegisterCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(InkraftCommand.INKRAFT);
    }
}
