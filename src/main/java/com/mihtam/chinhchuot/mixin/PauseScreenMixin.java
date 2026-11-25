package com.mihtam.chinhchuot.mixin;

import com.mihtam.chinhchuot.ManHinhCaiDat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen {
    protected PauseScreenMixin(Component title) {
        super(title);
    }

    // Nút mở menu ở góc trên bên trái màn hình tạm dừng
    @Inject(method = "init", at = @At("TAIL"))
    private void chinhchuot$themNut(CallbackInfo ci) {
        addRenderableWidget(Button.builder(Component.literal("Chỉnh Chuột"),
                        nut -> Minecraft.getInstance().setScreen(new ManHinhCaiDat((Screen) (Object) this)))
                .bounds(6, 6, 90, 20).build());
    }
}
