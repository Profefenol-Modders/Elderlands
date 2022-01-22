package io.github.profefenol.elderland.blocks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks implements ModInitializer {
    public static final Block PORTAL_FRAME = new PortalFrame(FabricBlockSettings.of(Material.PORTAL).strength(14.0f));
    public static final Block UNSTABLE_ELDERIUM = new Block(FabricBlockSettings.of(Material.STONE).strength(8.0f));
    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("elderlands", "elder_portal_frame"), PORTAL_FRAME);
        Registry.register(Registry.ITEM, new Identifier("elderlands", "elder_portal_frame"), new BlockItem(PORTAL_FRAME, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier("elderlands", "unstable_elderium"), UNSTABLE_ELDERIUM);
        Registry.register(Registry.ITEM, new Identifier("elderlands", "unstable_elderium"), new BlockItem(UNSTABLE_ELDERIUM, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS.setNoScrollbar())));
    }
    public static class PortalFrame extends Block {
        public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");

        @Override
        protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
            stateManager.add(ACTIVATED);
        }

        public PortalFrame(Settings settings) {
            super(settings);
            setDefaultState(getStateManager().getDefaultState().with(ACTIVATED, false));
        }
    }
}
