package org.oznecniv97.easyvanilla;

import net.minecraftforge.common.ForgeConfigSpec;

public class EasyVanillaConfig {

    private EasyVanillaConfig() {}

    static final ForgeConfigSpec clientSpec;
    public static final Client CLIENT;
    static {
        final var specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static class Client {
        public final ForgeConfigSpec.IntValue autoFishingMinStopCount;

        Client(ForgeConfigSpec.Builder builder) {
            builder.comment("Client only settings, like the rest of the mod :P")
                    .push("client");

            autoFishingMinStopCount = builder
                    .comment("Tick to wait until restart fishing.")
                    .translation("conf.autoFishingMinStopCount")
                    .defineInRange("autoFishingMinStopCount", 20, 20, 1000);

            builder.pop();
        }
    }
}
