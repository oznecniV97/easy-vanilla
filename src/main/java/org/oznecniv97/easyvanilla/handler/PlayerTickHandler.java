package org.oznecniv97.easyvanilla.handler;

//import net.minecraft.entity.projectile.FishingBobberEntity;
//import net.minecraftforge.event.TickEvent.PlayerTickEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.lang.reflect.Field;

public class PlayerTickHandler {

//    private static final Logger log = LogManager.getLogger();
//
//    @SubscribeEvent
//    public void onPlayerTick(PlayerTickEvent event) {
//        // controlla lo stato della canna da pesca a ogni tick del player
//        FishingBobberEntity bobber = event.player.fishingBobber;
//        if(bobber!=null) {
//			checkBobberState(bobber);
//        }
//    }
//
//    private int waterLevel = -1;
//
//	private void checkBobberState(FishingBobberEntity bobber) {
//		try {
//			Field f = bobber.getClass().getDeclaredField("currentState");
//			f.setAccessible(true);
//			String bobberState = f.get(bobber).toString();
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
//		} catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
//			log.error("Schiattata la reflection", e);
//		}
//	}

}
