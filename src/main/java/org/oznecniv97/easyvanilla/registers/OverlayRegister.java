package org.oznecniv97.easyvanilla.registers;

import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import org.oznecniv97.easyvanilla.overlay.AutoFishingOverlay;

public class OverlayRegister {

    public static void init() {

        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.CROSSHAIR_ELEMENT, "easyvanilla.auto_fishing_overlay", new AutoFishingOverlay());
    }
}
