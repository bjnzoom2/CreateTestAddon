package net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs;

import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CustomCogwheelBlock extends CogWheelBlock {
    public CustomCogwheelBlock(boolean large, Properties properties) {
        super(large, properties);
    }

    public static CustomCogwheelBlock small(BlockBehaviour.Properties properties) {
        return new CustomCogwheelBlock(false, properties);
    }

    public static CustomCogwheelBlock large(BlockBehaviour.Properties properties) {
        return new CustomCogwheelBlock(true, properties);
    }
}
