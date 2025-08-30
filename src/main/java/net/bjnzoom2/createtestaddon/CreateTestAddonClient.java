package net.bjnzoom2.createtestaddon;

import net.bjnzoom2.createtestaddon.registry.CTAPartialModels;
import net.bjnzoom2.createtestaddon.registry.CTAPonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class CreateTestAddonClient {
    public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        CTAPartialModels.register();
        modEventBus.addListener(CreateTestAddonClient::init);
    }

    public static void init(final FMLClientSetupEvent event) {
        PonderIndex.addPlugin(new CTAPonderPlugin());
    }
}
