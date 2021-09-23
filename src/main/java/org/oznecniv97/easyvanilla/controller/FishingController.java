package org.oznecniv97.easyvanilla.controller;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.projectile.FishingHook;

public class FishingController {

    private static final int MIN_BITING_COUNT = 4;
    private static final int MIN_STOP_COUNT = 20;

    private static FishingController instance;

    private boolean active = false;

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

    /**
     * method to start or stop fishing check
     */
    public void startOrStop() {
        //start/stop
        active = !active;
        //disable/enable pause on lost focus
        Minecraft.getInstance().options.pauseOnLostFocus = !active;
        Minecraft.getInstance().options.save();
        //if active, click key use to start fishing
        if(active) {
            KeyMapping.click(Minecraft.getInstance().options.keyUse.getKey());
        }
    }

    /**
     * check fishing hook status for every player tick
     * @param hook the player hook
     */
    public void checkPlayerTick(final FishingHook hook) {
        if(active) {
            if (!stopCheck && hook != null) {
                //check if hook is in bobbing state, biting is true and biting count is more than the minimum
                if (FishingHook.FishHookState.BOBBING.equals(hook.currentState)
                    && hook.biting
                    && ++bitingCount > MIN_BITING_COUNT
                ) {
                    //click use key to pick up the hook
                    KeyMapping.click(Minecraft.getInstance().options.keyUse.getKey());
                    //enable stop to wait hook picking up
                    stopCheck = true;
                    //reset biting count
                    bitingCount = 0;
                }
            } else if(stopCheck && hook==null && ++stopCount > MIN_STOP_COUNT) {
                //click use key to restart fishing
                KeyMapping.click(Minecraft.getInstance().options.keyUse.getKey());
                stopCheck = false;
                stopCount = 0;
            }
        } else {
            bitingCount = 0;
            stopCount = 0;
            stopCheck = false;
        }
    }

}
