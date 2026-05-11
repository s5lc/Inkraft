package cx.rain.mc.inkraft.data.story;

import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StoryRegistry implements IDataRegistry<Identifier, String> {
    private final Map<Identifier, String> stories = new HashMap<>();

    public StoryRegistry() {
    }

    @Override
    public void add(Identifier resourceLocation, String json) {
        stories.put(resourceLocation, json);
    }

    @Override
    public void clear() {
        stories.clear();
    }

    @Override
    public Set<Identifier> getAll() {
        return stories.keySet();
    }

    @Override
    public @Nullable String get(Identifier path) {
        return stories.get(path);
    }

    @Override
    public boolean has(@Nullable Identifier path) {
        return stories.containsKey(path);
    }
}
