package cx.rain.mc.inkraft.api.platform.storage;

import cx.rain.mc.inkraft.ModConstants;
import cx.rain.mc.inkraft.story.IStoryVariable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public interface IInkPlayerData extends IValueIOSerializable {
    @Nullable
    Identifier getStory();

    void setStory(@Nullable Identifier story);

    @Nullable
    String getState();

    void setState(@Nullable String state);

    boolean isEnded();

    void setEnded(boolean end);

    @Nullable
    UUID getContinuousToken();

    void setContinuousToken(@Nullable UUID token);

    boolean hasVariable(@NotNull String name);

    @Nullable
    IStoryVariable<?> getVariable(@NotNull String name);

    void setVariable(@NotNull String name, @NotNull IStoryVariable<?> value);

    void unsetVariable(@NotNull String name);

    @NotNull Map<String, IStoryVariable<?>> getVariables();

    void clearVariables();

    default boolean hasData() {
        return getStory() != null && getState() != null;
    }

    default void resetState() {
        setState(null);
//        setEnded(true);
        setContinuousToken(null);
    }

    default void clearData() {
        setStory(null);
        resetState();
        clearVariables();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    default <U, T extends IStoryVariable<U>> U getVariable(@NotNull String name, Class<T> type) {
        var v = getVariable(name);
        if (v != null && v.getClass().equals(type)) {
            return ((T) v).getValue();
        }
        return null;
    }

    @Override
    default void serialize(ValueOutput output) {
        var story = getStory();
        output.putString(ModConstants.Tags.STORY, story == null ? "" : story.toString());
        output.putString(ModConstants.Tags.STATE, Objects.requireNonNullElse(getState(), ""));
        output.putBoolean(ModConstants.Tags.ENDED, isEnded());

        var list = output.childrenList(ModConstants.Tags.VARIABLES);
        for (var entry : getVariables().entrySet()) {
            var item = list.addChild();
            item.putString(ModConstants.Tags.VARIABLE_ITEM_NAME, entry.getKey());
            item.putString(ModConstants.Tags.VARIABLE_ITEM_VALUE, entry.getValue().getValue().toString());
        }
    }

    @Override
    default void deserialize(ValueInput input) {
        clearData();

        var story = input.getStringOr(ModConstants.Tags.STORY, "");
        setStory(story.isBlank() ? null : Identifier.parse(story));

        var state = input.getStringOr(ModConstants.Tags.STATE, "");
        setState(state.isBlank() ? null : state);

        setEnded(input.getBooleanOr(ModConstants.Tags.ENDED, true));
        for (var item : input.childrenListOrEmpty(ModConstants.Tags.VARIABLES)) {
            var name = item.getStringOr(ModConstants.Tags.VARIABLE_ITEM_NAME, "");
            var value = IStoryVariable.fromString(item.getStringOr(ModConstants.Tags.VARIABLE_ITEM_VALUE, ""));
            setVariable(name, value);
        }
    }
}
