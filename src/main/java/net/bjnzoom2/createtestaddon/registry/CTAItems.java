package net.bjnzoom2.createtestaddon.registry;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class CTAItems {
    public static ItemEntry<Item> RAW_BRASS = REGISTRATE.item("raw_brass", Item::new)
            .register();
    public static ItemEntry<Item> CRUSHED_RAW_BRASS = REGISTRATE.item("crushed_raw_brass", Item::new)
            .register();

    public static void register() {}
}