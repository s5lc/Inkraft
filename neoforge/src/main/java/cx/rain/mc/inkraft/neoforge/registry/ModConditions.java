package cx.rain.mc.inkraft.neoforge.registry;

import com.mojang.serialization.MapCodec;
import cx.rain.mc.inkraft.Inkraft;
import cx.rain.mc.inkraft.data.loot.condition.StoryCondition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModConditions {
    public static final DeferredRegister<MapCodec<? extends LootItemCondition>> REGISTRY = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, Inkraft.MODID);

    public static final DeferredHolder<MapCodec<? extends LootItemCondition>, MapCodec<? extends LootItemCondition>> STORY = REGISTRY.register("story", () -> StoryCondition.MAP_CODEC);

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }

}
