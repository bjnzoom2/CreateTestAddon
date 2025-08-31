package net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.bjnzoom2.createtestaddon.registry.CTAPartialModels;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraftforge.registries.ForgeRegistries;

public class CustomCogwheelBlockEntityRenderer extends KineticBlockEntityRenderer<BracketedKineticBlockEntity> {
    public CustomCogwheelBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(BracketedKineticBlockEntity be, float partialTicks, PoseStack ms,
                              MultiBufferSource buffer, int light, int overlay) {

        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;

        if (!(be.getBlockState().getBlock() instanceof CustomCogwheelBlock block)) {
            super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
            return;
        }

        if (!block.isLargeCog()) {
            super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
            return;
        }

        // Large cogs sometimes have to offset their teeth by 11.25 degrees in order to
        // mesh properly

        Direction.Axis axis = getRotationAxisOf(be);
        Direction facing = Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE);
        if (CTAPartialModels.SHAFTLESS_LARGE_COGS_MODELS.get(ForgeRegistries.BLOCKS.getKey(be.getBlockState().getBlock()).getPath().replaceAll("_large_cogwheel","")) == null) return;
        renderRotatingBuffer(be,
                CachedBuffers.partialFacingVertical(CTAPartialModels.SHAFTLESS_LARGE_COGS_MODELS.get(ForgeRegistries.BLOCKS.getKey(be.getBlockState().getBlock()).getPath().replaceAll("_large_cogwheel","")), be.getBlockState(), facing),
                ms, buffer.getBuffer(RenderType.solid()), light);

        float angle = getAngleForLargeCogShaft(be, axis);
        SuperByteBuffer shaft =
                CachedBuffers.partialFacingVertical(AllPartialModels.COGWHEEL_SHAFT, be.getBlockState(), facing);
        kineticRotationTransform(shaft, be, axis, angle, light);
        shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));

    }

    public static float getAngleForLargeCogShaft(SimpleKineticBlockEntity be, Direction.Axis axis) {
        BlockPos pos = be.getBlockPos();
        float offset = getShaftAngleOffset(axis, pos);
        float time = AnimationTickHolder.getRenderTime(be.getLevel());
        float angle = ((time * be.getSpeed() * 3f / 10 + offset) % 360) / 180 * (float) Math.PI;
        return angle;
    }

    public static float getShaftAngleOffset(Direction.Axis axis, BlockPos pos) {
        float offset = 0;
        double d = (((axis == Direction.Axis.X) ? 0 : pos.getX()) + ((axis == Direction.Axis.Y) ? 0 : pos.getY())
                + ((axis == Direction.Axis.Z) ? 0 : pos.getZ())) % 2;
        if (d == 0)
            offset = 22.5f;
        return offset;
    }
}
