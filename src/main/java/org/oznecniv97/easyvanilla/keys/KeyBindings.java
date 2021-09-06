package org.oznecniv97.easyvanilla.keys;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.fmlclient.registry.ClientRegistry;

import java.awt.event.KeyEvent;

public class KeyBindings {

	public static KeyMapping startFishing;
	public static KeyMapping holdActionButton;

	public static void init() {
		startFishing		= new KeyMapping("key.startFishing"		, KeyEvent.VK_P, "key.categories.easyvanilla");
		holdActionButton	= new KeyMapping("key.holdActionButton"	, KeyEvent.VK_O, "key.categories.easyvanilla");

		// Register both KeyBindings to the ClientRegistry
		ClientRegistry.registerKeyBinding(startFishing);
		ClientRegistry.registerKeyBinding(holdActionButton);
	}

}
