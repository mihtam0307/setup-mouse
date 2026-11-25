package com.mihtam.chinhchuot.mixin;

import com.mihtam.chinhchuot.CauHinh;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Shadow private double accumulatedDX;
    @Shadow private double accumulatedDY;

    // Chỉ chạy khi xoay góc nhìn trong map, menu không bị ảnh hưởng
    @Inject(method = "turnPlayer", at = @At("HEAD"))
    private void chinhchuot$xoayTruc(double thoiGian, CallbackInfo ci) {
        double dx = accumulatedDX;
        double dy = accumulatedDY;
        if (!CauHinh.bat) return;
        switch (CauHinh.goc) {
            case 90 -> { accumulatedDX = -dy; accumulatedDY = dx; }
            case 180 -> { accumulatedDX = -dx; accumulatedDY = -dy; }
            case 270 -> { accumulatedDX = dy; accumulatedDY = -dx; }
            default -> { }
        }
    }
}
