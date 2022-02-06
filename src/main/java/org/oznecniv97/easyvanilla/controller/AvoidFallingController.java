package org.oznecniv97.easyvanilla.controller;

import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AvoidFallingController {

    private static final Logger log = LogManager.getLogger();

    private static AvoidFallingController instance;

    private boolean active = true;

    //TODO: add overlay when active
    public boolean isActive() {
        return active;
    }

    private AvoidFallingController() {
    }

    public static AvoidFallingController getInstance() {
        if(instance==null) {
            instance = new AvoidFallingController();
        }
        return instance;
    }

    /**
     * method to start or stop possible falling check
     */
    public void startOrStop() {
        //start/stop
        active = !active;
    }

    /**
     * check player position for every player tick
     * @param player the player
     */
    public void checkPlayerTick(final Player player) {
        if(active) {
            //posizione del player
            log.warn("position: {}", player.position());
            //delta di spostamento del player, y sempre infariore a 0, ma maggiore di -0.1 (es: -0.0784000015258789)
            log.warn("deltaMovement: {}", player.getDeltaMovement());

            //TODO: individuare se presente uno spostamento

            //TODO: individuare direzione spostamento

            //TODO: controllare se c'è un blocco "pericoloso" (lava/troppa aria)

            //TODO: bloccare il player

        }
    }

}
