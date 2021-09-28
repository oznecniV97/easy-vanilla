package org.oznecniv97.easyvanilla.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

public class AutoFishingOverlay extends GuiComponent implements IIngameOverlay {
    @Override
    public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {
        if (!Minecraft.getInstance().options.hideGui && gui.shouldDrawSurvivalElements())
        {
            Minecraft.getInstance().getProfiler()
        }
    }

    public static void drawExhaustionOverlay(float exhaustion, Minecraft mc, PoseStack poseStack, int right, int top, float alpha)
    {
        //bind mod icons
        RenderSystem.setShaderTexture(0, modIcons);

        float maxExhaustion = HungerHelper.getMaxExhaustion(mc.player);
        // clamp between 0 and 1
        float ratio = Math.min(1, Math.max(0, exhaustion / maxExhaustion));
        int width = (int) (ratio * 81);
        //altezza icone (9x9)
        int height = 9;

        enableAlpha(.75f);
        mc.gui.blit(poseStack, right - width, top, 81 - width, 18, width, height);
        disableAlpha(.75f);

        // rebind default icons
        RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
    }
}
