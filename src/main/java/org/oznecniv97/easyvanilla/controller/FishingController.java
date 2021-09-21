package org.oznecniv97.easyvanilla.controller;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.projectile.FishingHook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oznecniv97.easyvanilla.ReflectionUtils;

public class FishingController {

    private static final int MIN_BITING_COUNT = 4;
    private static final int MIN_STOP_COUNT = 20;

    private static final Logger log = LogManager.getLogger();

    private static FishingController instance;

    private int bitingCount = 0;
    private int stopCount = 0;
    private boolean stopCheck = false;

    private FishingController() {}

    public static FishingController getInstance() {
        if(instance==null) {
            instance = new FishingController();
        }
        return instance;
    }

    public void checkPlayerTick(final FishingHook hook) {
        // controlla lo stato della canna da pesca a ogni tick del player
        if (!stopCheck && hook != null) {
                //procedo al controllo solo se lo stato è bobbing, quindi galleggiante
                var currentState = ReflectionUtils.getStringField(hook, "currentState");
                if (currentState.equals("BOBBING")) {

                    //controllo ricevo biting a true almeno MIN_BITING_COUNT volte
                    var biting = ReflectionUtils.getBooleanField(hook, "biting"); //se il pesce sta mordendo l'amo
                    if(biting && ++bitingCount > MIN_BITING_COUNT) {
                        //click tasto destro per pescare il pesce
                        KeyMapping.click(Minecraft.getInstance().options.keyUse.getKey());
                        //attivo lo stop per attendere di aver ritirato l'hook
                        stopCheck = true;
                        //reset biting count
                        bitingCount = 0;
                    }
                }
        } else if(stopCheck && hook==null && ++stopCount > MIN_STOP_COUNT) {
            //click tasto destro per ricominciare a pescare
            KeyMapping.click(Minecraft.getInstance().options.keyUse.getKey());
            stopCheck = false;
            stopCount = 0;
        }

    }

}
