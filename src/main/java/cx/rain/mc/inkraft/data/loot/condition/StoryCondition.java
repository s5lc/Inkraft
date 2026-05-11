package cx.rain.mc.inkraft.data.loot.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import cx.rain.mc.inkraft.Inkraft;
import cx.rain.mc.inkraft.engine.EngineManager;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;

public record StoryCondition(Identifier storyId) implements LootItemCondition {
    public static final Identifier ANY = Identifier.fromNamespaceAndPath(Inkraft.MODID, "any");

    public static final Codec<StoryCondition> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Identifier.CODEC.optionalFieldOf("story", ANY).forGetter(StoryCondition::storyId))
                    .apply(instance, StoryCondition::new)
    );
    public static final MapCodec<StoryCondition> MAP_CODEC = MapCodec.assumeMapUnsafe(CODEC);
    @Override
    public @NotNull MapCodec<? extends LootItemCondition> codec() {
        return MAP_CODEC;
    }

    @Override
    public boolean test(LootContext lootContext) {
        var entity = lootContext.getOptionalParameter(LootContextParams.THIS_ENTITY);
        if (entity instanceof ServerPlayer player) {
            var instance = EngineManager.getInstance().get(player);
            if (ANY.equals(storyId)) {
                return !instance.isStoryEnded();
            } else {
                return storyId.equals(instance.getData().getStory()) && !instance.isStoryEnded();
            }
        }
        return false;
    }
}
