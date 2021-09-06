package org.oznecniv97.easyvanilla;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.oznecniv97.easyvanilla.handler.KeyInputHandler;
import org.oznecniv97.easyvanilla.keys.KeyBindings;

@Mod("easyvanilla")
public class EasyVanilla {

    public EasyVanilla() {

    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLInitialization);

    	MinecraftForge.EVENT_BUS.register(new KeyInputHandler());

//        MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
    }

    /**
     * Metodo per intercettare l'inizializzazione da parte del client, utile per inizializzare i KeyBindings
     */
    private void onFMLInitialization(final FMLClientSetupEvent event){
    	KeyBindings.init();
    }

}
