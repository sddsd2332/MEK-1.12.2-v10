package mekanism.common.util;

import mekanism.api.EnumColor;
import net.minecraft.client.Minecraft;

import mekanism.api.EnumColor.*;

import static mekanism.api.EnumColor.*;


public class TextUtils {

    private static final EnumColor[] fabulousness = new EnumColor[]{
            BLACK,
            RED,
            DARK_GREEN,
            BROWN,
            DARK_BLUE,
            PURPLE,
            DARK_AQUA,
            GREY,
            DARK_GREY,
            BRIGHT_PINK,
            BRIGHT_GREEN,
            YELLOW,
            INDIGO,
            PINK,
            ORANGE,
            WHITE};

    public static String makeFabulous(String input) {
        return ludicrousFormatting(input, fabulousness, 80.0, 1, 1);
    }

    private static final EnumColor[] sanic = new EnumColor[] { INDIGO, INDIGO, INDIGO, INDIGO, WHITE, INDIGO, WHITE, WHITE, INDIGO, WHITE, WHITE, INDIGO, RED, WHITE, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY, DARK_GREY };

    public static String makeSANIC(String input) {
        return ludicrousFormatting(input, sanic, 50.0, 2, 1);
    }

    public static String ludicrousFormatting(String input, EnumColor[] colours, double delay, int step, int posstep) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0) {
            delay = 0.001;
        }

        int offset = (int) Math.floor(Minecraft.getSystemTime() / delay) % colours.length;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            int col = ((i * posstep) + colours.length - offset) % colours.length;

            sb.append(colours[col].toString());
            sb.append(c);
        }

        return sb.toString();
    }
}