package cx.rain.mc.inkraft.fabric.registry;

import cx.rain.mc.inkraft.data.loot.condition.StoryCondition;
import cx.rain.mc.inkraft.utility.IdHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModConditions {
    public static void register() {
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, IdHelper.modLoc("story"), StoryCondition.MAP_CODEC);
    }
}
