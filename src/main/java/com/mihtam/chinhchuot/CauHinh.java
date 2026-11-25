package com.mihtam.chinhchuot;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Lưu cài đặt vào config/chinhchuot.txt theo dạng "góc,bật". */
public final class CauHinh {
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("chinhchuot.txt");
    public static int goc = 90;
    public static boolean bat = true;

    static {
        try {
            if (Files.exists(FILE)) {
                String[] p = Files.readString(FILE).trim().split(",");
                int g = Integer.parseInt(p[0].trim());
                if (g == 90 || g == 180 || g == 270) goc = g;
                if (g == 0) bat = false;
                if (p.length > 1) bat = Boolean.parseBoolean(p[1].trim());
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("[chinhchuot] Lỗi đọc cấu hình: " + e);
        }
    }

    public static void luu() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, goc + "," + bat);
        } catch (IOException e) {
            System.err.println("[chinhchuot] Lỗi lưu cấu hình: " + e);
        }
    }
}
