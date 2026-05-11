package cx.rain.mc.inkraft.data.story;

import cx.rain.mc.inkraft.Inkraft;
import cx.rain.mc.inkraft.engine.EngineManager;
import net.minecraft.core.MappedRegistry;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class StoryReloadListener implements PreparableReloadListener {
    public static final Identifier INKRAFT_STORY_LOADER = Identifier.fromNamespaceAndPath(Inkraft.MODID, "story_loader");

    public static final StoryReloadListener INSTANCE = new StoryReloadListener(EngineManager.getInstance().getStoryRegistry());

    protected static final String STORY_PATH = "inkraft_story";
    protected static final FileToIdConverter FILE_TO_ID_CONVERTER = new FileToIdConverter(STORY_PATH, ".ink.json");

    private final IDataRegistry<Identifier, String> registry;

    protected StoryReloadListener(IDataRegistry<Identifier, String> registry) {
        this.registry = registry;
    }

    @Override
    public CompletableFuture<Void> reload(SharedState currentReload,
                                          Executor taskExecutor,
                                          PreparationBarrier preparationBarrier,
                                          Executor reloadExecutor) {
        return CompletableFuture
                .supplyAsync(() -> prepare(currentReload.resourceManager()), taskExecutor)
                .thenCompose(preparationBarrier::wait)
                .thenAcceptAsync(this::apply, reloadExecutor);
    }

    private Map<Identifier, String> prepare(net.minecraft.server.packs.resources.ResourceManager resourceManager) {
        var stories = new HashMap<Identifier, String>();
        FILE_TO_ID_CONVERTER.listMatchingResources(resourceManager).forEach((path, resource) -> {
            try {
                stories.put(FILE_TO_ID_CONVERTER.fileToId(path),
                        IOUtils.toString(resource.open(), StandardCharsets.UTF_8));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return stories;
    }

    private void apply(Map<Identifier, String> stories) {
        registry.clear();
        stories.forEach(registry::add);
    }

    @Override
    public String getName() {
        return "StoryReloadListener";
    }
}
