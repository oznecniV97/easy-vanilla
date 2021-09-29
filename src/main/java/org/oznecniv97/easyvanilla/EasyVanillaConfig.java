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
        public final ForgeConfigSpec.IntValue autoFishingMaxResetTime;

        Client(ForgeConfigSpec.Builder builder) {
            builder.comment("Client only settings, like the rest of the mod :P")
                    .push("client");

            autoFishingMinStopCount = builder
                    .comment("Tick to wait until restart fishing.")
                    .translation("conf.autoFishingMinStopCount")
                    .defineInRange("autoFishingMinStopCount", 20, 20, 1000);

            autoFishingMaxResetTime = builder
                    .comment("Tick to max wait until restart fishing. This is useful for slow pc. 0 to disable it.")
                    .translation("conf.autoFishingMaxResetTime")
                    .defineInRange("autoFishingMaxResetTime", 1000, 0, 9999);

            builder.pop();
        }
    }
}
