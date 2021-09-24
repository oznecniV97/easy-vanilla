package org.oznecniv97.easyvanilla;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.oznecniv97.easyvanilla.handler.KeyInputHandler;
import org.oznecniv97.easyvanilla.handler.PlayerTickHandler;
import org.oznecniv97.easyvanilla.keys.KeyBindings;

@Mod("easyvanilla")
public class EasyVanilla {

    public EasyVanilla() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, EasyVanillaConfig.clientSpec);

    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLInitialization);

    	MinecraftForge.EVENT_BUS.register(new KeyInputHandler());

		MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
    }

    /**
	 * Method for intercepting client initialization, useful for KeyBindings init
     */
    private void onFMLInitialization(final FMLClientSetupEvent event){
		KeyBindings.init();
    }

}
