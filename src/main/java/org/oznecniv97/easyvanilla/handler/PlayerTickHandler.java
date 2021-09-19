package org.oznecniv97.easyvanilla.handler;

import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

public class PlayerTickHandler {

    private static final Logger log = LogManager.getLogger();

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		//TODO: controllare se l'item si rompe, così da rimpiazzarlo, se presente
//		event.player.getInventory().

        // controlla lo stato della canna da pesca a ogni tick del player
		final var hook = event.player.fishing;
        if(hook!=null) {
			checkBobberState(hook);
        }
    }

//    private int waterLevel = -1;

	private void checkBobberState(FishingHook hook) {
		try {
			Field f = hook.getClass().getDeclaredField("currentState"); //FishingHook.FishHookState
			f.setAccessible(true);
			var currentState = f.get(hook).toString();
			f = hook.getClass().getDeclaredField("biting"); //bool
			f.setAccessible(true);
			var biting = f.getBoolean(hook);
			f = hook.getClass().getDeclaredField("life"); //int
			f.setAccessible(true);
			var life = f.getInt(hook);
			f = hook.getClass().getDeclaredField("nibble"); //int
			f.setAccessible(true);
			var nibble = f.getInt(hook);
			f = hook.getClass().getDeclaredField("timeUntilLured"); //int
			f.setAccessible(true);
			var timeUntilLured = f.getInt(hook);
			f = hook.getClass().getDeclaredField("timeUntilHooked"); //int
			f.setAccessible(true);
			var timeUntilHooked = f.getInt(hook);

			log.info("event.player.fishing.currentState: {}", currentState);
			log.info("event.player.fishing.biting: {}", biting);
			log.info("event.player.fishing.life: {}", life);
			log.info("event.player.fishing.nibble: {}", nibble);
			log.info("event.player.fishing.timeUntilLured: {}", timeUntilLured);
			log.info("event.player.fishing.timeUntilHooked: {}", timeUntilHooked);

//			if(bobberState.equals("BOBBING")) {
//				int yBobber = bobber.getPosition().getY();
//				if(waterLevel==-1) {
//					//TODO calcolare meglio il livello dell'acqua, magari vedendo direttamente la posizione del blocco
//					waterLevel = yBobber; //66
//				}
//				if(yBobber!=waterLevel) {
//					log.info(bobberState+" at position.Y: " + bobber.getPosition().getY());
//				}
//			} else {
//				waterLevel = -1;
//			}
		} catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			log.error("Schiattata la reflection", e);
		}
	}

}
