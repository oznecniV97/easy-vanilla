package org.oznecniv97.easyvanilla.handler;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
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
        if (!stopCheck && hook != null) {
            checkHookState(hook);
        } else if(stopCheck && hook == null) {
            log.info("stopcheck true after hooked");
            stopCheck = false;
            lastNibble = 0;
        }
    }

    private int lastNibble = 0;
    private boolean stopCheck = false;

    private void checkHookState(FishingHook hook) {
        try {
            var currentState = getStringField(hook, "currentState");

            if (currentState.equals("BOBBING")) {
                var biting = getBooleanField(hook, "biting"); //se il pesce sta mordendo l'amo
                var nibble = getIntField(hook, "nibble"); //tempo prima che il pesce vada via
                var timeUntilLured = getIntField(hook, "timeUntilLured"); //tempo fino al prossimo arrivo del pesce
                var timeUntilHooked = getIntField(hook, "timeUntilHooked"); //tempo fino al morso del pesce

                if (biting) {
                    log.info("event.player.fishing.biting: true");
                }
                if (nibble != 0) {
                    log.info("event.player.fishing.nibble: {}", nibble);
                }
                if (timeUntilLured != 0) {
                    log.info("event.player.fishing.timeUntilLured: {}", timeUntilLured);
                }
                if (timeUntilHooked != 0) {
                    log.info("event.player.fishing.timeUntilHooked: {}", timeUntilHooked);
                }

                if (biting && nibble != 0) {
                    log.info("biting && nibble!=0");
                    if (lastNibble == 0) {
                        log.info("lastNibble==0");
                        lastNibble = nibble;
                    } else if (lastNibble != nibble) {
                        log.info("lastNibble!=nibble");
                        stopCheck = true;
                        lastNibble = 0;
                        KeyMapping.click(Minecraft.getInstance().options.keyUse.getKey());
                    }
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            log.error("Schiattata la reflection", e);
        }
    }

    private String getStringField(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return getField(obj, fieldName).get(obj).toString();
    }

    private int getIntField(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return getField(obj, fieldName).getInt(obj);
    }

    private boolean getBooleanField(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return getField(obj, fieldName).getBoolean(obj);
    }

    private Field getField(Object obj, String fieldName) throws NoSuchFieldException {
        final var field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

}
