package cx.rain.mc.inkraft.neoforge.storage;

import cx.rain.mc.inkraft.storage.InkPlayerData;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

public class InkPlayerDataNeoForge extends InkPlayerData implements ValueIOSerializable {
    @Override
    public void serialize(ValueOutput output) {
        super.serialize(output);
    }

    @Override
    public void deserialize(ValueInput input) {
        super.deserialize(input);
    }
}
