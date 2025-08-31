package net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs.encased;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.bjnzoom2.createtestaddon.registry.CTAPartialModels;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class EncasedCustomCogwheelRenderer extends KineticBlockEntityRenderer<SimpleKineticBlockEntity> {
    private boolean large;

    public static EncasedCustomCogwheelRenderer small(BlockEntityRendererProvider.Context context) {
        return new EncasedCustomCogwheelRenderer(context, false);
    }

    public static EncasedCustomCogwheelRenderer large(BlockEntityRendererProvider.Context context) {
        return new EncasedCustomCogwheelRenderer(context, true);
    }

    public EncasedCustomCogwheelRenderer(BlockEntityRendererProvider.Context context, boolean large) {
        super(context);
        this.large = large;
    }

    @Override
    protected void renderSafe(SimpleKineticBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;

        BlockState blockState = be.getBlockState();
        Block block = blockState.getBlock();
        if (!(block instanceof IRotate def))
            return;

        Direction.Axis axis = getRotationAxisOf(be);
        BlockPos pos = be.getBlockPos();
        float angle = large ? BracketedKineticBlockEntityRenderer.getAngleForLargeCogShaft(be, axis)
                : getAngleForBe(be, pos, axis);

        for (Direction d : Iterate.directionsInAxis(getRotationAxisOf(be))) {
            if (!def.hasShaftTowards(be.getLevel(), be.getBlockPos(), blockState, d))
                continue;
            SuperByteBuffer shaft = CachedBuffers.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), d);
            kineticRotationTransform(shaft, be, axis, angle, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
        }
    }

    @Override
    protected SuperByteBuffer getRotatedModel(SimpleKineticBlockEntity be, BlockState state) {
        return CachedBuffers.partialFacingVertical(
                large ? CTAPartialModels.SHAFTLESS_LARGE_COGS_MODELS.get(ForgeRegistries.BLOCKS.getKey(((EncasedCustomCogwheelBlock)state.getBlock()).getCogwheel().get()).getPath().replaceAll("_large_cogwheel","")) : CTAPartialModels.COGS_MODELS.get(ForgeRegistries.BLOCKS.getKey(((EncasedCustomCogwheelBlock)state.getBlock()).getCogwheel().get()).getPath().replaceAll("_cogwheel","")), state,
                Direction.fromAxisAndDirection(state.getValue(EncasedCustomCogwheelBlock.AXIS), Direction.AxisDirection.POSITIVE));
    }
}
