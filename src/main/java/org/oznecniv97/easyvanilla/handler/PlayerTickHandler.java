package org.oznecniv97.easyvanilla.handler;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.oznecniv97.easyvanilla.controller.AvoidFallingController;
import org.oznecniv97.easyvanilla.controller.FishingController;

public class PlayerTickHandler {

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        //TODO: check if item broke and if true, replace it, if available
//        final var selectedItem = event.player.getInventory().getSelected();

        //pass player fishing hook to FishingController
        FishingController.getInstance().checkPlayerTick(event.player.fishing);
        //pass player to AvoidFallingController
        AvoidFallingController.getInstance().checkPlayerTick(event.player);
    }

}
