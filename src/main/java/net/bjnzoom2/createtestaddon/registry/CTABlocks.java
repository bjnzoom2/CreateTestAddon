package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.AllTags;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.bjnzoom2.createtestaddon.block.kinetic.crank_wheel.CrankWheelBlock;
import net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs.CustomCogwheelBlock;
import net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs.CustomCogwheelBlockItem;
import net.bjnzoom2.createtestaddon.config.CTAStress;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import java.util.Objects;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOnly;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class CTABlocks {
    public static BlockEntry<Block> RAW_BRASS_BLOCK = REGISTRATE.block("raw_brass_block", Block::new)
            .lang("Block of Raw Brass")
            .simpleItem()
            .register();

    public static final BlockEntry<CustomCogwheelBlock> IRON_COGWHEEL = createCustomCogwheel("iron");
    public static final BlockEntry<CustomCogwheelBlock> IRON_LARGE_COGWHEEL = createLargeCustomCogwheel("iron");

    public static final BlockEntry<CrankWheelBlock.Small> CRANK_WHEEL = REGISTRATE.block("crank_wheel", CrankWheelBlock.Small::new)
            .initialProperties(SharedProperties::wooden)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .transform(CTAStress.setCapacity(8.0))
            .onRegister(BlockStressValues.setGeneratorSpeed(32))
            .tag(AllTags.AllBlockTags.BRITTLE.tag)
            .onRegister(ItemUseOverrides::addBlock)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CrankWheelBlock.Large> LARGE_CRANK_WHEEL = REGISTRATE.block("large_crank_wheel", CrankWheelBlock.Large::new)
            .initialProperties(SharedProperties::wooden)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .transform(CTAStress.setCapacity(8.0))
            .onRegister(BlockStressValues.setGeneratorSpeed(32))
            .tag(AllTags.AllBlockTags.BRITTLE.tag)
            .onRegister(ItemUseOverrides::addBlock)
            .item()
            .transform(customItemModel())
            .register();

    private static BlockEntry<CustomCogwheelBlock> createCustomCogwheel(String name) {
        return REGISTRATE.block(name + "_cogwheel", CustomCogwheelBlock::small)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD).mapColor(MapColor.DIRT))
                .transform(CTAStress.setNoImpact())
                .transform(axeOnly())
                .blockstate(CTABlockStateGens.cogwheel(name))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .item(CustomCogwheelBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(cogwheelModel(p, name,true))))
                .build()
                .register();
    }

    private static BlockEntry<CustomCogwheelBlock> createLargeCustomCogwheel(String name) {
        return REGISTRATE.block(name + "_large_cogwheel", CustomCogwheelBlock::large)
                .initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD).mapColor(MapColor.DIRT))
                .transform(axeOnly())
                .transform(CTAStress.setNoImpact())
                .blockstate(CTABlockStateGens.largeCogwheel(name))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .item(CustomCogwheelBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(Objects.requireNonNull(largeCogwheelModel(p, name,true))))
                .build()
                .register();
    }

    public static void register() {}
}
