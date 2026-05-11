package cx.rain.mc.inkraft.neoforge.data;

import cx.rain.mc.inkraft.Inkraft;
import cx.rain.mc.inkraft.neoforge.data.lang.EnUsProvider;
import cx.rain.mc.inkraft.neoforge.data.lang.ZhCnProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Inkraft.MODID)
public class ModData {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event) {
        var gen = event.getGenerator();
        var output = gen.getPackOutput();

        event.addProvider(new ZhCnProvider(output));
        event.addProvider(new EnUsProvider(output));
    }
}
