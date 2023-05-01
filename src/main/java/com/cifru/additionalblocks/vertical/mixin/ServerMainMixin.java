package com.cifru.additionalblocks.vertical.mixin;

import com.cifru.additionalblocks.vertical.AdditionalBlocks;
import net.minecraft.server.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created 27/07/2022 by SuperMartijn642
 */
@Mixin(value = Main.class, priority = 1100)
public class ServerMainMixin {

    @Inject(
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/Util;startTimerHackThread()V",
            ordinal = 0,
            shift = At.Shift.AFTER
        ),
        method = "main"
    )
    private static void afterInitServer(CallbackInfo ci){
        AdditionalBlocks.registerBlocks();
    }
}
