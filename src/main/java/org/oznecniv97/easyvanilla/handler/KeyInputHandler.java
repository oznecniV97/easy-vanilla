package org.oznecniv97.easyvanilla.handler;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oznecniv97.easyvanilla.controller.FishingController;
import org.oznecniv97.easyvanilla.keys.KeyBindings;

import java.awt.event.KeyEvent;

public class KeyInputHandler {

    private static final Logger log = LogManager.getLogger();

    private static final int ALT_TAB = 342;
    private static final int WIN_TAB = 343;

    private boolean holdActionButtonPressed = false;

	@SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
		//caso pressione un altro tasto dopo aver attivato holdActionButton
        if(holdActionButtonPressed
        && event.getKey() != KeyBindings.holdActionButton.getKey().getValue()
        && event.getKey() != ALT_TAB
    	&& event.getKey() != WIN_TAB){
        	log.debug("Rilasciato holdActionButton, premuto {} ({})", KeyEvent.getKeyText(event.getKey()), event.getKey());
			Minecraft.getInstance().options.pauseOnLostFocus = true;
			Minecraft.getInstance().options.save();
            KeyMapping.set(Minecraft.getInstance().options.keyUse.getKey(), false);
        	holdActionButtonPressed = false;
        } else
    	//caso pressione holdActionButton
    	if(KeyBindings.holdActionButton.consumeClick()) {
        	log.debug("Premuto holdActionButton");
        	Minecraft.getInstance().options.pauseOnLostFocus = false;
        	Minecraft.getInstance().options.save();
            KeyMapping.set(Minecraft.getInstance().options.keyUse.getKey(), true);
        	holdActionButtonPressed = true;
        } else
        //caso pressione tasto pesca
    	if(KeyBindings.startFishing.consumeClick()){
        	log.debug("Premuto pesca");
        	//start auto-fishing
        	FishingController.getInstance().startOrStop();
        }
	}

}
