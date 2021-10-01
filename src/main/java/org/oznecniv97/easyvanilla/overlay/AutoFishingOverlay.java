package org.oznecniv97.easyvanilla.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import org.lwjgl.opengl.GL11;
import org.oznecniv97.easyvanilla.controller.FishingController;

public class AutoFishingOverlay extends GuiComponent implements IIngameOverlay {

    @Override
    public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {
        if ( !Minecraft.getInstance().options.hideGui && FishingController.getInstance().isActive()) {
            //enable blend for alpha icons
            final var oldShaderColors = RenderSystem.getShaderColor();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
            //print bubble beside CROSS HAIR ELEMENT
            print(gui, mStack, 9, 9, 16, 18, (width / 2) - 18, (height / 2) - 9);
            //reset blend
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(oldShaderColors[0], oldShaderColors[1], oldShaderColors[2], oldShaderColors[3]);
        }
    }

    private void print(ForgeIngameGui gui, PoseStack mStack, int sizex, int sizey, int offsetx, int offsety, int posx, int posy) {
        gui.blit(mStack, posx, posy, offsetx, offsety, sizex, sizey);
    }
}
