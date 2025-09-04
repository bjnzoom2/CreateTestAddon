package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.bjnzoom2.createtestaddon.block.encased.CTAEncasedShaftBlock;
import net.bjnzoom2.createtestaddon.block.kinetic.crank_wheel.CrankWheelBlock;
import net.bjnzoom2.createtestaddon.config.CTAStress;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class CTABlocks {
    public static BlockEntry<Block> RAW_BRASS_BLOCK = REGISTRATE.block("raw_brass_block", Block::new)
            .lang("Block of Raw Brass")
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW))
            .simpleItem()
            .register();

    public static final BlockEntry<CasingBlock> IRON_CASING = REGISTRATE.block("steel_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> CTASpriteShifts.IRON_CASING))
            .register();

    public static final BlockEntry<CTAEncasedShaftBlock> IRON_ENCASED_SHAFT = REGISTRATE.block("steel_encased_shaft", p -> new CTAEncasedShaftBlock(p, IRON_CASING::get))
            .transform(CTABuilderTransformers.encasedShaft("iron", () -> CTASpriteShifts.IRON_CASING))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<CrankWheelBlock.Small> CRANK_WHEEL = REGISTRATE.block("crank_wheel", CrankWheelBlock.Small::new)
            .initialProperties(SharedProperties::wooden)
            .properties(p -> p.sound(SoundType.WOOD).mapColor(MapColor.PODZOL))
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
            .properties(p -> p.sound(SoundType.WOOD).mapColor(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .transform(CTAStress.setCapacity(8.0))
            .onRegister(BlockStressValues.setGeneratorSpeed(32))
            .tag(AllTags.AllBlockTags.BRITTLE.tag)
            .onRegister(ItemUseOverrides::addBlock)
            .item()
            .transform(customItemModel())
            .register();

    public static void register() {}
}
