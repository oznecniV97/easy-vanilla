package org.oznecniv97.easyvanilla.registers;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.fmlclient.registry.ClientRegistry;

import java.awt.event.KeyEvent;

public class KeyBindingRegister {

	private KeyBindingRegister() {}

	public static final KeyMapping startFishing = new KeyMapping(
		"key.startFishing",
		KeyEvent.VK_P,
		"key.categories.easyvanilla"
	);
	public static final KeyMapping holdActionButton = new KeyMapping(
		"key.holdActionButton",
		KeyEvent.VK_O,
		"key.categories.easyvanilla"
	);

	public static void init() {
		// Register both KeyBindings to the ClientRegistry
		ClientRegistry.registerKeyBinding(startFishing);
		ClientRegistry.registerKeyBinding(holdActionButton);
	}

}
