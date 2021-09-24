package org.oznecniv97.easyvanilla.handler;

import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.oznecniv97.easyvanilla.controller.FishingController;

public class PlayerTickHandler {

    @SubscribeEvent
    public void onServerPlayerTick(TickEvent.PlayerTickEvent event) {
        //TODO: check if item broke and if true, replace it, if available
        logItem(event.player.getInventory());

        //pass player fishing hook to FishingController
        FishingController.getInstance().checkPlayerTick(event.player.fishing);
    }

    private int lastSelected = -1;
    private boolean lastIsDamaged = false;
    private boolean lastIsDamageableItem = false;
    private int lastMaxDamage = -1;
    private int lastDamageValue = -1;
    private void logItem(Inventory inventory) {
        final var selectedItem = inventory.getSelected();

        if(lastSelected != inventory.selected) {
            lastSelected = inventory.selected;
            LogManager.getLogger().info("inventory.selected {}", inventory.selected);
        }

        if(lastIsDamageableItem != selectedItem.isDamageableItem()) {
            lastIsDamageableItem = selectedItem.isDamageableItem();
            LogManager.getLogger().info("selectedItem.isDamageableItem {}", selectedItem.isDamageableItem());
        }

        if(lastIsDamaged != selectedItem.isDamaged()) {
            lastIsDamaged = selectedItem.isDamaged();
            LogManager.getLogger().info("selectedItem.isDamaged {}", selectedItem.isDamaged());
        }

        if(lastMaxDamage != selectedItem.getMaxDamage()) {
            lastMaxDamage = selectedItem.getMaxDamage();
            LogManager.getLogger().info("selectedItem.getMaxDamage {}", selectedItem.getMaxDamage());
        }

        if(lastDamageValue != selectedItem.getDamageValue()) {
            lastDamageValue = selectedItem.getDamageValue();
            LogManager.getLogger().info("selectedItem.getDamageValue {}", selectedItem.getDamageValue());
        }
    }

    private int storedFocusedSlot = -1;

    //sostituire l'item quando si rompe
    private void sostituireItem(Inventory inventory) {
        final var selectedItem = inventory.getSelected();
        final var focusedSlot = inventory.selected;

        if(storedFocusedSlot!=focusedSlot) {
            //if focused slot is different from the last one, can't check the other parameters
            storedFocusedSlot = focusedSlot;
        } else if(selectedItem.isDamageableItem()) {
            //
            final var currentDamage = selectedItem.getDamageValue();

        }

        // Copy some info about current selected stack for auto-refill
//        storedStack = selectedItem.copy();
//        storedStackId = currentStackId;
//        storedStackDamage = currentStackDamage;
    }

}
