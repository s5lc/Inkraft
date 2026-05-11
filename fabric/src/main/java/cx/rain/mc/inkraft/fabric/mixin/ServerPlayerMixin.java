package cx.rain.mc.inkraft.fabric.mixin;

import cx.rain.mc.inkraft.fabric.bridge.IInkraftServerPlayer;
import cx.rain.mc.inkraft.storage.InkPlayerData;
import cx.rain.mc.inkraft.utility.IdHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class ServerPlayerMixin implements IInkraftServerPlayer {
    @Unique
    private InkPlayerData inkraft$playerData = new InkPlayerData();

    @Override
    public InkPlayerData inkraft$getPlayerData() {
        return inkraft$playerData;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void inkraft$after$addAdditionalSaveData(ValueOutput output, CallbackInfo ci) {
        var data = output.child(IdHelper.modLoc("player_data").toString());
        inkraft$playerData.serialize(data);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void inkraft$after$readAdditionalSaveData(ValueInput input, CallbackInfo ci) {
        var data = input.childOrEmpty(IdHelper.modLoc("player_data").toString());
        inkraft$playerData.deserialize(data);
    }
}
