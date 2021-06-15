package io.github.catchyaintit;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import io.catchyaintit.block.ModBlocks;
import io.catchyaintit.item.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
