package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.AllCreativeModeTabs;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.bjnzoom2.createtestaddon.CreateTestAddon;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.bjnzoom2.createtestaddon.CreateTestAddon.REGISTRATE;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateTestAddon.MOD_ID);

    public static RegistryObject<CreativeModeTab> CREATE_TEST_ADDON = CREATIVE_MODE_TABS.register("create_test_addon",
            () -> CreativeModeTab.builder()
                    .title(Component.literal("Create: Test Addon"))
                    .withTabsBefore(AllCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
                    .icon(ModItems.RAW_BRASS::asStack)
                    .displayItems((b, output) -> {
                        for (RegistryEntry<Item> item : REGISTRATE.getAll(Registries.ITEM)) {
                            output.accept(item.get());
                        }
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
