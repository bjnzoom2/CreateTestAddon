package net.bjnzoom2.createtestaddon.block;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;

public class ModBlocks {
    public static BlockEntry<Block> RAW_BRASS_BLOCK;

    public static void register(Registrate REGISTRATE) {
        RAW_BRASS_BLOCK = REGISTRATE.block("raw_brass_block", Block::new)
                .lang("Block of Raw Brass")
                .simpleItem()
                .register();
    }
}
