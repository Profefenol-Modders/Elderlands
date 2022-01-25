package io.github.profefenol.elderlands.blocks;

import io.github.profefenol.elderlands.gui.ModItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block PORTAL_FRAME = new PortalFrame(FabricBlockSettings.of(Material.PORTAL).strength(-1.0f, 3600000.0f));
    public static final Block UNSTABLE_ELDERIUM = new Block(FabricBlockSettings.of(Material.STONE).strength(7.0f, 10.0f));

    public static void modBlocks() {
        initializeBlocks();
    }

    public static void initializeBlocks() {
            Registry.register(Registry.BLOCK, new Identifier("elderlands", "elder_portal_frame"), PORTAL_FRAME);
            Registry.register(Registry.ITEM, new Identifier("elderlands", "elder_portal_frame"), new BlockItem(PORTAL_FRAME, new Item.Settings().group(ModItemGroup.ELDERLANDS_MISC)));
            Registry.register(Registry.BLOCK, new Identifier("elderlands", "unstable_elderium"), UNSTABLE_ELDERIUM);
            Registry.register(Registry.ITEM, new Identifier("elderlands", "unstable_elderium"), new BlockItem(UNSTABLE_ELDERIUM, new Item.Settings().group(ModItemGroup.ELDERLANDS_BLOCKS)));
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
