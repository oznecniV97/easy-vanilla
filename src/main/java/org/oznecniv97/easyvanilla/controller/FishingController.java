package org.oznecniv97.easyvanilla.controller;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.projectile.FishingHook;
import org.oznecniv97.easyvanilla.EasyVanillaConfig;

public class FishingController {

    private static final int MIN_BITING_COUNT = 4;

    private static FishingController instance;

    private final int autoFishingMinStopCount;
    private final int autoFishingMaxResetTime;

    private boolean active = false;

    public boolean isActive() {
        return active;
    }

    private int bitingCount = 0;
    private int stopCount = 0;
    private int resetCount = 0;
    private boolean stopCheck = false;

    private FishingController() {
        autoFishingMinStopCount = EasyVanillaConfig.CLIENT.autoFishingMinStopCount.get();
        autoFishingMaxResetTime = EasyVanillaConfig.CLIENT.autoFishingMaxResetTime.get();
    }

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
                    pickUpHook();
                }
                //reset part, to stop hooking if it is blocked
                if(autoFishingMaxResetTime != 0 && ++resetCount > autoFishingMaxResetTime) {
                    pickUpHook();
                }
            } else if(stopCheck && hook==null && ++stopCount > autoFishingMinStopCount) {
                //click use key to restart fishing
                KeyMapping.click(Minecraft.getInstance().options.keyUse.getKey());
                stopCheck = false;
                stopCount = 0;
                resetCount = 0;
            }
        } else {
            bitingCount = 0;
            stopCount = 0;
            resetCount = 0;
            stopCheck = false;
        }
    }

    private void pickUpHook() {
        //click use key to pick up the hook
        KeyMapping.click(Minecraft.getInstance().options.keyUse.getKey());
        //enable stop to wait hook picking up
        stopCheck = true;
        //reset biting count
        bitingCount = 0;
        resetCount = 0;
    }

}
