package cx.rain.mc.inkraft.registry;

import cx.rain.mc.inkraft.story.function.IStoryFunction;
import cx.rain.mc.inkraft.utility.IdHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class InkraftRegistries {
    public static final ResourceKey<Registry<IStoryFunction>> STORY_FUNCTIONS = ResourceKey.createRegistryKey(IdHelper.modLoc("story_functions"));
}
