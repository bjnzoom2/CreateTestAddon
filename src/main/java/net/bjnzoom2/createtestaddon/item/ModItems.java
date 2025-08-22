package net.bjnzoom2.createtestaddon.item;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

public class ModItems {
    public static ItemEntry<Item> RAW_BRASS;
    public static ItemEntry<Item> CRUSHED_RAW_BRASS;

    public static void register(Registrate REGISTRATE) {
        RAW_BRASS = REGISTRATE.item("raw_brass", Item::new)
                .register();
        CRUSHED_RAW_BRASS = REGISTRATE.item("crushed_raw_brass", Item::new)
                .register();
    }
}