package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.crank.HandCrankRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.bjnzoom2.createtestaddon.block.kinetic.crank_wheel.CrankWheelBlockEntity;
import net.bjnzoom2.createtestaddon.block.kinetic.crank_wheel.CrankWheelVisual;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;

import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class CTABlockEntityTypes {
    public static final BlockEntityEntry<CrankWheelBlockEntity> CRANK_WHEEL = REGISTRATE
            .blockEntity("crank_wheel", CrankWheelBlockEntity::new)
            .visual(() -> CrankWheelVisual::new)
            .validBlocks(CTABlocks.CRANK_WHEEL, CTABlocks.LARGE_CRANK_WHEEL)
            .renderer(() -> HandCrankRenderer::new)
            .register();

    public static final BlockEntityEntry<KineticBlockEntity> CTA_ENCASED_SHAFT = REGISTRATE
            .blockEntity("cta_encased_shaft", KineticBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual::shaft, false)
            .validBlocks(CTABlocks.IRON_ENCASED_SHAFT)
            .renderer(() -> ShaftRenderer::new)
            .register();


    public static void register() {
    }
}
