package net.bjnzoom2.createtestaddon.registry;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;

import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class ModBlocks {
    public static BlockEntry<Block> RAW_BRASS_BLOCK = REGISTRATE.block("raw_brass_block", Block::new)
            .lang("Block of Raw Brass")
            .simpleItem()
            .register();

    public static void register() {}
}
