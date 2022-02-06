package org.oznecniv97.easyvanilla.registers;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

import java.awt.event.KeyEvent;

public class KeyBindingRegister {

	private static final String EASY_VANILLA_CATEGORY = "key.categories.easyvanilla";

	private KeyBindingRegister() {}

	public static final KeyMapping startFishing = new KeyMapping(
		"key.startFishing",
		KeyEvent.VK_P,
		EASY_VANILLA_CATEGORY
	);
	public static final KeyMapping holdActionButton = new KeyMapping(
		"key.holdActionButton",
		KeyEvent.VK_O,
		EASY_VANILLA_CATEGORY
	);

	public static void init() {
		// Register all KeyBindings to the ClientRegistry
		ClientRegistry.registerKeyBinding(startFishing);
		ClientRegistry.registerKeyBinding(holdActionButton);
	}

}
