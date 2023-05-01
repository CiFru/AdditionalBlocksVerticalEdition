package com.cifru.additionalblocks.vertical.mixin;

import com.cifru.additionalblocks.vertical.AdditionalBlocks;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created 01/05/2023 by SuperMartijn642
 */
@Mixin(value = Minecraft.class, priority = 1100)
public class MinecraftMixin {

    @Inject(
        at = @At(
            value = "INVOKE",
            target = "Ljava/lang/Thread;currentThread()Ljava/lang/Thread;",
            ordinal = 0,
            shift = At.Shift.AFTER
        ),
        method = "<init>"
    )
    private void afterInitClient(CallbackInfo ci){
        AdditionalBlocks.registerBlocks();
    }
}
