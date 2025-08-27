package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.bjnzoom2.createtestaddon.block.kinetic.cog_crank.CogCrankBlock;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class ModBlocks {
    public static BlockEntry<Block> RAW_BRASS_BLOCK = REGISTRATE.block("raw_brass_block", Block::new)
            .lang("Block of Raw Brass")
            .simpleItem()
            .register();

    public static final BlockEntry<CogCrankBlock> COG_CRANK = REGISTRATE.block("cog_crank", CogCrankBlock::new)
            .initialProperties(SharedProperties::wooden)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .transform(BlockStressDefaults.setCapacity(8.0))
            .transform(BlockStressDefaults.setGeneratorSpeed(CogCrankBlock::getSpeedRange))
            .tag(AllTags.AllBlockTags.BRITTLE.tag)
            .onRegister(ItemUseOverrides::addBlock)
            .item()
            .tab(ModCreativeModeTabs.CREATE_TEST_ADDON.getKey())
            .transform(customItemModel())
            .register();

    public static void register() {}
}
