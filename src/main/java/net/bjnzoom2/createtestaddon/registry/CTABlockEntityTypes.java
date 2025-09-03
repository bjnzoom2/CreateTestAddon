package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.crank.HandCrankRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.bjnzoom2.createtestaddon.block.kinetic.crank_wheel.CrankWheelBlockEntity;
import net.bjnzoom2.createtestaddon.block.kinetic.crank_wheel.CrankWheelVisual;
import net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs.encased.EncasedCustomCogwheelVisual;

import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class CTABlockEntityTypes {
    public static final BlockEntityEntry<CrankWheelBlockEntity> CRANK_WHEEL = REGISTRATE
            .blockEntity("crank_wheel", CrankWheelBlockEntity::new)
            .visual(() -> CrankWheelVisual::new)
            .validBlocks(CTABlocks.CRANK_WHEEL, CTABlocks.LARGE_CRANK_WHEEL)
            .renderer(() -> HandCrankRenderer::new)
            .register();

    public static void register() {
    }
}
