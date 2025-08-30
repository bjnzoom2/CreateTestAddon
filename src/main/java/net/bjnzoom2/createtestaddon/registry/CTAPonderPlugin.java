package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.bjnzoom2.createtestaddon.CreateTestAddon;
import net.bjnzoom2.createtestaddon.ponder.CrankWheelScenes;
import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.api.registration.*;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CTAPonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return CreateTestAddon.MOD_ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        register(helper);
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        register(helper);
    }

    @Override
    public void registerSharedText(SharedTextRegistrationHelper helper) {
        PonderPlugin.super.registerSharedText(helper);
    }

    @Override
    public void onPonderLevelRestore(PonderLevel ponderLevel) {
        PonderPlugin.super.onPonderLevelRestore(ponderLevel);
    }

    @Override
    public void indexExclusions(IndexExclusionHelper helper) {
        PonderPlugin.super.indexExclusions(helper);
    }

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemProviderEntry<?>> SCENE_HELPER = helper.withKeyFunction(RegistryEntry::getId);

        SCENE_HELPER.forComponents(CTABlocks.CRANK_WHEEL, CTABlocks.LARGE_CRANK_WHEEL)
                .addStoryBoard("crank_wheel", CrankWheelScenes::crankWheel, AllCreatePonderTags.KINETIC_SOURCES);
    }

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        PonderTagRegistrationHelper<RegistryEntry<?>> TAG_HELPER = helper.withKeyFunction(RegistryEntry::getId);

        TAG_HELPER.addToTag(AllCreatePonderTags.KINETIC_SOURCES)
                .add(CTABlocks.CRANK_WHEEL);
    }
}
