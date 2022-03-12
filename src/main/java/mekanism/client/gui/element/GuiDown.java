package mekanism.client.gui.element;


import mekanism.client.gui.IGuiWrapper;
import mekanism.common.util.MekanismUtils;
import net.minecraft.util.ResourceLocation;

public  class GuiDown extends GuiElement {

    private final int xLocation;
    private final int yLocation;
    private final int textureX;
    private final int textureY;

    private StorageOverlay overlay = null;

    private final int width;
    private final int height;

    public GuiDown(StorageType type, IGuiWrapper gui, ResourceLocation def, int x, int y) {
        super(MekanismUtils.getResource(MekanismUtils.ResourceType.GUI_ELEMENT, "GuiDown.png"), gui, def);

        xLocation = x;
        yLocation = y;

        width = type.width;
        height = type.height;

        textureX = type.textureX;
        textureY = type.textureY;
    }

    @Override
    public Rectangle4i getBounds(int guiWidth, int guiHeight) {
        return new Rectangle4i(guiWidth + xLocation, guiHeight + yLocation, width, height);
    }

    @Override
    public void renderBackground(int xAxis, int yAxis, int guiWidth, int guiHeight) {
        mc.renderEngine.bindTexture(RESOURCE);
        guiObj.drawTexturedRect(guiWidth + xLocation, guiHeight + yLocation, textureX, textureY, width, height);
        if (overlay != null) {
            int w = overlay.width;
            int h = overlay.height;
            int xLocationOverlay = xLocation + (width - w) / 2;
            int yLocationOverlay = yLocation + (height - h) / 2;
            guiObj.drawTexturedRect(guiWidth + xLocationOverlay, guiHeight + yLocationOverlay, overlay.textureX, overlay.textureY, w, h);
        }
        mc.renderEngine.bindTexture(defaultLocation);
    }

    @Override
    public void renderForeground(int xAxis, int yAxis) {
    }

    @Override
    public void preMouseClicked(int xAxis, int yAxis, int button) {
    }

    @Override
    public void mouseClicked(int xAxis, int yAxis, int button) {
    }

    public enum StorageType {
        NORMAL(8,20,0,0);


        public final int width;
        public final int height;

        public final int textureX;
        public final int textureY;

        StorageType(int w, int h, int x, int y) {
            width = w;
            height = h;

            textureX = x;
            textureY = y;
        }

    }

    public enum StorageOverlay {
        DOWN(8,20,9,0),
        DOWN2(8,20,17,0);


        public final int width;
        public final int height;

        public final int textureX;
        public final int textureY;

        StorageOverlay(int w, int h, int x, int y) {
            width = w;
            height = h;

            textureX = x;
            textureY = y;
        }
    }
}
