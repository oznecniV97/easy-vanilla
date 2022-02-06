package org.oznecniv97.easyvanilla.controller;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oznecniv97.easyvanilla.utils.AxisUtils;

import java.util.stream.Stream;

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
            //check if there's a horizontal movement
            if(!isPlayerInMovement(player)) {
                return;
            }
            //posizione del player
//            log.warn("position: {}", player.position());
            //delta di spostamento del player, y sempre infariore a 0, ma maggiore di -0.1 (es: -0.0784000015258789)
            log.warn("deltaMovement: {}", player.getDeltaMovement());
            //TODO: individuare direzione spostamento
            var directions = getPlayerDirections(player).toList();
            log.warn("getPlayerDirections.size: {}", directions.size());
            log.warn("getPlayerDirections: {}", directions);

            //TODO: controllare se c'è un blocco "pericoloso" (lava/troppa aria)

            //TODO: bloccare il player

        }
    }

    /**
     * check if the player is in horizontal movement
     * @param player the player
     * @return true if player is in horizontal movement
     */
    private boolean isPlayerInMovement(final Player player) {
        final var delta = player.getDeltaMovement();
        return delta.horizontalDistance() > 0;
    }

    /**
     * Get the directions where the player is in horizontal movement
     * @param player the player
     * @return stream of player directions
     */
    private Stream<Direction> getPlayerDirections(final Player player) {
        return AxisUtils.getHorizontalDirections(player.getDeltaMovement());
    }

}
