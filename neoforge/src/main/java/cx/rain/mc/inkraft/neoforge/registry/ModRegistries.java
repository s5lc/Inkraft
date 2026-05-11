package cx.rain.mc.inkraft.neoforge.registry;

import cx.rain.mc.inkraft.registry.InkraftRegistries;
import cx.rain.mc.inkraft.story.function.IStoryFunction;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class ModRegistries {
    public static final Registry<IStoryFunction> STORY_FUNCTIONS = new RegistryBuilder<>(InkraftRegistries.STORY_FUNCTIONS)
            .disableRegistrationCheck()
            .create();
}
