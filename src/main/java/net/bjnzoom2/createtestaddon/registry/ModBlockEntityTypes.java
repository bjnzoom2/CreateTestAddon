package net.bjnzoom2.createtestaddon.registry;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.bjnzoom2.createtestaddon.block.kinetic.cog_crank.CogCrankBlockEntity;

import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class ModBlockEntityTypes {
    public static final BlockEntityEntry<CogCrankBlockEntity> COG_CRANK = REGISTRATE
            .blockEntity("cog_crank", CogCrankBlockEntity::new)
            .instance(() -> CogCrankInstance::new)
            .validBlocks(ModBlocks.COG_CRANK)
            .renderer(() -> CogCrankRenderer::new)
            .register();
}
