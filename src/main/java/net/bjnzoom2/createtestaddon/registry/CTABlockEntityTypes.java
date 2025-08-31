package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.crank.HandCrankRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.bjnzoom2.createtestaddon.block.kinetic.crank_wheel.CrankWheelBlockEntity;
import net.bjnzoom2.createtestaddon.block.kinetic.crank_wheel.CrankWheelVisual;
import net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs.CustomCogwheelBlockEntityVisual;
import net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs.CustomCogwheelBlockEntityRenderer;
import net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs.encased.EncasedCustomCogwheelRenderer;
import net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs.encased.EncasedCustomCogwheelVisual;

import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class CTABlockEntityTypes {
    public static final BlockEntityEntry<CrankWheelBlockEntity> CRANK_WHEEL = REGISTRATE
            .blockEntity("crank_wheel", CrankWheelBlockEntity::new)
            .visual(() -> CrankWheelVisual::new)
            .validBlocks(CTABlocks.CRANK_WHEEL, CTABlocks.LARGE_CRANK_WHEEL)
            .renderer(() -> HandCrankRenderer::new)
            .register();

    public static final BlockEntityEntry<BracketedKineticBlockEntity> CUSTOM_COGWHEELS = REGISTRATE
            .blockEntity("wooden_cogwheels", BracketedKineticBlockEntity::new)
            .visual(() -> CustomCogwheelBlockEntityVisual::create, false)
            .validBlocks()
            .renderer(() -> CustomCogwheelBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_CUSTOM_COGWHEEL = REGISTRATE
            .blockEntity("encased_custom_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCustomCogwheelVisual::small, false)
            .validBlocks(AllBlocks.ANDESITE_ENCASED_COGWHEEL, AllBlocks.BRASS_ENCASED_COGWHEEL)
            .renderer(() -> EncasedCustomCogwheelRenderer::small)
            .register();

    public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_CUSTOM_LARGE_COGWHEEL = REGISTRATE
            .blockEntity("encased_custom_large_cogwheel", SimpleKineticBlockEntity::new)
            .visual(() -> EncasedCustomCogwheelVisual::large, false)
            .validBlocks(AllBlocks.ANDESITE_ENCASED_LARGE_COGWHEEL, AllBlocks.BRASS_ENCASED_LARGE_COGWHEEL)
            .renderer(() -> EncasedCustomCogwheelRenderer::large)
            .register();

    public static void register() {
    }
}
