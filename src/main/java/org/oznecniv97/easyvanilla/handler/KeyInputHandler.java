package org.oznecniv97.easyvanilla.handler;

import java.awt.event.KeyEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oznecniv97.easyvanilla.keys.KeyBindings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyInputHandler {

    private static final Logger log = LogManager.getLogger();

    private static final int altTab = 342;
    private static final int winTab = 343;
    private boolean holdActionButtonPressed = false;

	@SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
		//caso pressione un altro tasto dopo aver attivato holdActionButton
        if(holdActionButtonPressed
        && event.getKey() != KeyBindings.holdActionButton.getKey().getKeyCode()
        && event.getKey() != altTab
    	&& event.getKey() != winTab){
        	log.debug("Rilasciato holdActionButton, premuto {} ({})", KeyEvent.getKeyText(event.getKey()), event.getKey());
        	Minecraft.getInstance().gameSettings.pauseOnLostFocus = true;
        	Minecraft.getInstance().gameSettings.saveOptions();
        	KeyBinding.setKeyBindState(Minecraft.getInstance().gameSettings.keyBindUseItem.getKey(), false);
        	holdActionButtonPressed = false;
        } else
    	//caso pressione holdActionButton
    	if(KeyBindings.holdActionButton.isPressed()) {
        	log.debug("Premuto holdActionButton");
        	Minecraft.getInstance().gameSettings.pauseOnLostFocus = false;
        	Minecraft.getInstance().gameSettings.saveOptions();
        	KeyBinding.setKeyBindState(Minecraft.getInstance().gameSettings.keyBindUseItem.getKey(), true);
        	holdActionButtonPressed = true;
        } else
        //caso pressione tasto pesca
    	if(KeyBindings.startFishing.isPressed()){
        	log.debug("Premuto pesca");
        	//TODO start pesca
//        	Fisherman.getInstance().startOrStop();
        }
	}

}
