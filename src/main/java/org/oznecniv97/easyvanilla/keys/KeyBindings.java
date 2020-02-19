package org.oznecniv97.easyvanilla.keys;

import java.awt.event.KeyEvent;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {

	public static KeyBinding startFishing;
	public static KeyBinding holdActionButton;

	public static void init() {
		startFishing		= new KeyBinding("key.startFishing"		, KeyEvent.VK_P, "key.categories.easyvanilla");
		holdActionButton	= new KeyBinding("key.holdActionButton"	, KeyEvent.VK_O, "key.categories.easyvanilla");

		// Register both KeyBindings to the ClientRegistry
		ClientRegistry.registerKeyBinding(startFishing);
		ClientRegistry.registerKeyBinding(holdActionButton);
	}

}
