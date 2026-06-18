package cx.rain.mc.inkraft.utility;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ItemStackHelper {
    private static final Logger log = LoggerFactory.getLogger(ItemStackHelper.class);

    public static Predicate<ItemStack> predicateIdOrTag(HolderLookup.Provider registries, String id) {
        if (id.startsWith("#")) {
            var tag = StringArgumentParseHelper.parseItemTag(id);
            return i -> i.is(tag);
        } else {
            var item = StringArgumentParseHelper.parseItem(registries, id);
            return i -> i.is(item);
        }
    }

    public static Predicate<ItemStack> predicateNbt(HolderLookup.Provider registries, String path, String value) {
        if (path.isEmpty() || value.isEmpty()) {
            return _ -> true;
        }

        var expected = StringArgumentParseHelper.parseNbt(value);
        var nbtPath = StringArgumentParseHelper.parseNbtPath(path);
        return item -> {
            try {
                var actual = nbtPath.get(saveItemStack(registries, item));
                return !actual.isEmpty() && actual.stream().allMatch(e -> e.equals(expected));
            } catch (CommandSyntaxException ignored) {
            }

            return false;
        };
    }

    public static Predicate<ItemStack> createPredicate(HolderLookup.Provider registries, String id, String tagPath, String tagValue) {
        return predicateIdOrTag(registries, id)
                .and(predicateNbt(registries, tagPath, tagValue));
    }

    public static List<ItemStack> match(ServerPlayer player, Predicate<ItemStack> predicate) {
        return player.getInventory()
                .getNonEquipmentItems()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static ItemStack createItemStack(HolderLookup.Provider registries, String id, String count, String tagPath, String tagValue) {
        var item = StringArgumentParseHelper.parseItem(registries, id);
        var result = new ItemStack(item);

        if (!count.isEmpty()) {
            var c = Integer.parseInt(count);
            result.setCount(c);
        }

        if (result.isEmpty()) {
            return ItemStack.EMPTY;
        }

        if (!tagPath.isEmpty() && !tagValue.isEmpty()) {
            var p = StringArgumentParseHelper.parseNbtPath(tagPath);
            var t = StringArgumentParseHelper.parseNbt(tagValue);
            var n = saveItemStack(registries, result);
            try {
                p.set(n, t);
                result = parseItemStack(registries, n);
            } catch (CommandSyntaxException ex) {
                log.warn("ItemStack parse error: ", ex);
            }
        }

        return result;
    }

    private static Tag saveItemStack(HolderLookup.Provider registries, ItemStack item) {
        var ops = registries.createSerializationContext(NbtOps.INSTANCE);
        return ItemStack.CODEC.encodeStart(ops, item).getOrThrow();
    }

    private static ItemStack parseItemStack(HolderLookup.Provider registries, Tag tag) {
        var ops = registries.createSerializationContext(NbtOps.INSTANCE);
        return ItemStack.CODEC.parse(ops, tag).getOrThrow();
    }
}
