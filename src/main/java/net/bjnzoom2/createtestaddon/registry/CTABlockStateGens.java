package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.Create;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.bjnzoom2.createtestaddon.CreateTestAddon;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.Objects;

import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;

public class CTABlockStateGens {
    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> cogwheel(String cogwheel) {
        return (ctx,prov)-> {
            axisBlock(ctx, prov, cogwheelModel(prov, cogwheel, true));
            cogwheelModel(prov, cogwheel, false);
        };
    }

    public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> largeCogwheel(String cogwheel) {
        return (ctx,prov)-> {
            axisBlock(ctx, prov, largeCogwheelModel(prov, cogwheel, true));
            largeCogwheelModel(prov, cogwheel, false);
        };
    }

    public static <T> ModelFile cogwheelModel(RegistrateProvider p, String cogwheel, boolean shaft) {
        if (isValidProvider(p))
            return Objects.requireNonNull(createModelInBlock(p, "cogwheel"+(shaft?"":"_shaftless")+"/" + cogwheel))
                    .parent(new ModelFile.UncheckedModelFile("create:block/cogwheel"+(shaft?"":"_shaftless")))
                    .texture("1_2",getCogwheelTexture(cogwheel))
                    .texture("particle",getShaftTexture(cogwheel));
        return null;
    }

    public static <T> ModelFile largeCogwheelModel(RegistrateProvider p,String cogwheel,boolean shaft) {
        if (isValidProvider(p))
            return Objects.requireNonNull(createModelInBlock(p, "large_cogwheel"+(shaft?"":"_shaftless")+"/" + cogwheel))
                    .parent(new ModelFile.UncheckedModelFile("create:block/large_cogwheel"+(shaft?"":"_shaftless")))
                    .texture("4",getLargeCogwheelTexture(cogwheel))
                    .texture("particle",getShaftTexture(cogwheel));
        return null;
    }

    public static ModelBuilder<? extends ModelBuilder<?>> createModelInBlock(RegistrateProvider p, String path){
        if (p instanceof RegistrateBlockstateProvider provider)
            return provider.models()
                    .getBuilder("block/"+path);
        else if (p instanceof RegistrateItemModelProvider provider)
            return provider.getBuilder("block/"+path);
        return null;
    }

    public static boolean isValidProvider(RegistrateProvider p){
        return p instanceof RegistrateBlockstateProvider || p instanceof RegistrateItemModelProvider;
    }

    public static String getShaftTexture(String shaft){
        if (shaft.equals("normal")) return Create.ID + ":block/axis";
        if (shaft.equals("bamboo")) return "minecraft:block/stripped_bamboo_block";
        if (isWoodenShaft(shaft)) return "minecraft:block/stripped_"+shaft+"_" + (shaft.equals("crimson") || shaft.equals("warped") ? "stem": "log");
        return CreateTestAddon.MOD_ID + ":block/shafts/"+shaft;
    }


    public static String getCogwheelTexture(String cogwheel) {
        if (cogwheel.equals("normal")) return Create.ID + ":block/cogwheel";
        return CreateTestAddon.MOD_ID + ":block/cogwheels/"+cogwheel;
    }

    public static String getLargeCogwheelTexture(String cogwheel) {
        if (cogwheel.equals("normal")) return Create.ID + ":block/large_cogwheel";
        return CreateTestAddon.MOD_ID + ":block/large_cogwheels/"+cogwheel;
    }
}
