package com.mihtam.chinhchuot;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ManHinhCaiDat extends Screen {
    private final Screen truoc;

    public ManHinhCaiDat(Screen truoc) {
        super(Component.literal("Chỉnh Chuột"));
        this.truoc = truoc;
    }

    @Override
    protected void init() {
        int x = width / 2 - 100;
        int y = height / 2 - 40;
        int rong = font.width(title);
        addRenderableWidget(new StringWidget((width - rong) / 2, y - 20, rong, 10, title, font));

        addRenderableWidget(Button.builder(nhanCheDo(), nut -> {
            CauHinh.bat = !CauHinh.bat;
            CauHinh.luu();
            nut.setMessage(nhanCheDo());
        }).bounds(x, y, 200, 20).build());

        addRenderableWidget(Button.builder(nhanGoc(), nut -> {
            CauHinh.goc = CauHinh.goc == 270 ? 90 : CauHinh.goc + 90;
            CauHinh.luu();
            nut.setMessage(nhanGoc());
        }).bounds(x, y + 24, 200, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Xong"), nut -> onClose())
                .bounds(x, y + 56, 200, 20).build());
    }

    private static Component nhanCheDo() {
        return Component.literal(CauHinh.bat ? "Chế độ: CHUỘT (đổi trục)" : "Chế độ: CẢM ỨNG (giữ nguyên)");
    }

    private static Component nhanGoc() {
        return Component.literal("Góc xoay chuột: " + CauHinh.goc + "°");
    }

    @Override
    public void onClose() {
        minecraft.setScreen(truoc);
    }
}
