package org.oznecniv97.easyvanilla.handler;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.oznecniv97.easyvanilla.controller.FishingController;

public class PlayerTickHandler {

    @SubscribeEvent
    public void onServerPlayerTick(TickEvent.PlayerTickEvent event) {
        //TODO: controllare se l'item si rompe, così da rimpiazzarlo, se presente
//		event.player.getInventory().

        //pass player fishing hook to FishingController
        FishingController.getInstance().checkPlayerTick(event.player.fishing);
    }

}
