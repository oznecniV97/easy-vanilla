package org.oznecniv97.easyvanilla;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.oznecniv97.easyvanilla.handler.KeyInputHandler;
import org.oznecniv97.easyvanilla.handler.PlayerTickHandler;
import org.oznecniv97.easyvanilla.registers.KeyBindingRegister;
import org.oznecniv97.easyvanilla.registers.OverlayRegister;

@Mod("easyvanilla")
public class EasyVanilla {

    public EasyVanilla() {
        //register client config file
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, EasyVanillaConfig.clientSpec);

        //add listener to event bus
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLInitialization);

        //register key input handler to minecraft event bus
    	MinecraftForge.EVENT_BUS.register(new KeyInputHandler());

        //register player tick handler to minecraft event bus
		MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
    }

    /**
	 * Method for intercepting client initialization, useful for registers init
     */
    private void onFMLInitialization(final FMLClientSetupEvent event){
        //register for key bindings
		KeyBindingRegister.init();
        //register for overlays
        OverlayRegister.init();
    }

}
