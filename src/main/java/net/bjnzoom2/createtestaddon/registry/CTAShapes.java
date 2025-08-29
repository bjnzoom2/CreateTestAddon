package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.AllShapes;
import net.createmod.catnip.math.VoxelShaper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CTAShapes {
    public static final VoxelShaper COG_CRANK = shape(5, 0, 5, 11, 6, 11).add(2, 6, 2, 14, 10, 14).forDirectional();
    public static final VoxelShaper LARGE_COG_CRANK = shape(5, 0, 5, 11, 6, 11).add(0, 6, 0, 16, 10, 16).forDirectional();

    private static AllShapes.Builder shape(VoxelShape shape) {
        return new AllShapes.Builder(shape);
    }

    private static AllShapes.Builder shape(double x1, double y1, double z1, double x2, double y2, double z2) {
        return shape(cuboid(x1, y1, z1, x2, y2, z2));
    }

    private static VoxelShape cuboid(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Block.box(x1, y1, z1, x2, y2, z2);
    }
}
