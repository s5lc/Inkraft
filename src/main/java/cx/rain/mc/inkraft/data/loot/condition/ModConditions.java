package cx.rain.mc.inkraft.data.loot.condition;

import com.mojang.serialization.MapCodec;
import cx.rain.mc.inkraft.Inkraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class ModConditions {
    public static final MapCodec<? extends LootItemCondition> STORY = StoryCondition.MAP_CODEC;

    public static void register() {
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE,
                Identifier.fromNamespaceAndPath(Inkraft.MODID, "story"), STORY);
    }
}
