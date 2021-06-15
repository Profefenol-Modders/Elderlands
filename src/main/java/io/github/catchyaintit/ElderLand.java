package io.github.catchyaintit;

import net.fabricmc.api.ModInitializer;

public class ElderLand implements ModInitializer {
	
        public static final ItemGroup ELDER_GROUP = FabricItemGroupBuilder.create(
        new Identifier(ElderLand.MOD_ID, "elder_group"))
        .icon(() -> new ItemStack(ModItems.GILDED_ELDERAN_BLOCK))
        .build();
	
	@Override
	public void onInitialize() {

	}
}
