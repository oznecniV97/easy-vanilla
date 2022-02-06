package org.oznecniv97.easyvanilla.registers;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

import java.awt.event.KeyEvent;

public class KeyBindingRegister {

	private static final String EASY_VANILLA_CATEGORY = "key.categories.easyvanilla";

	private KeyBindingRegister() {}

	public static final KeyMapping startStopFishing = new KeyMapping(
		"key.startStopFishing",
		KeyEvent.VK_P,
		EASY_VANILLA_CATEGORY
	);
	public static final KeyMapping startStopAvoidFalling = new KeyMapping(
		"key.startStopAvoidFalling",
		KeyEvent.VK_O,
		EASY_VANILLA_CATEGORY
	);
	public static final KeyMapping holdActionButton = new KeyMapping(
		"key.holdActionButton",
		KeyEvent.VK_I,
		EASY_VANILLA_CATEGORY
	);

	public static void init() {
		// Register all KeyBindings to the ClientRegistry
		ClientRegistry.registerKeyBinding(startStopFishing);
		ClientRegistry.registerKeyBinding(startStopAvoidFalling);
		ClientRegistry.registerKeyBinding(holdActionButton);
	}

}
