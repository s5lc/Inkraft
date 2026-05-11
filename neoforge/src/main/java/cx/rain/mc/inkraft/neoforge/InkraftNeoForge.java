package cx.rain.mc.inkraft.neoforge;

import cx.rain.mc.inkraft.Inkraft;
import cx.rain.mc.inkraft.neoforge.registry.ModAttachments;
import cx.rain.mc.inkraft.neoforge.registry.ModConditions;
import cx.rain.mc.inkraft.neoforge.registry.ModStoryFunctions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Inkraft.MODID)
public class InkraftNeoForge {
    public InkraftNeoForge(IEventBus bus) {
        Inkraft.init();
        ModAttachments.register(bus);
        ModConditions.register(bus);
        ModStoryFunctions.register(bus);
    }
}
