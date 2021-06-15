package io.github.catchyaintit;

import net.fabricmc.api.ModInitializer;

public class ElderLand implements ModInitializer {
	
        public static Logger LOGGER = LogManager.getLogger();

        public static final String MOD_ID = "platinum";
        public static final String MOD_NAME = "Platinum Mod";
	
        public static final ItemGroup ELDER_GROUP = FabricItemGroupBuilder.create(
        new Identifier(ElderLand.MOD_ID, "elder_group"))
        .icon(() -> new ItemStack(ModItems.GILDED_ELDERAN_BLOCK))
        .build();
	
        @Override
        public void onInitialize() {
            log(Level.INFO, "Initializing");

            ModItems.register();
            ModBlocks.register();
        }

        public static void log(Level level, String message){
            LOGGER.log(level, "["+MOD_NAME+"] " + message);
        }
}
