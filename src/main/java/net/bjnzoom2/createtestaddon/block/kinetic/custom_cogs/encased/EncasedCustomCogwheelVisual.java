package net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs.encased;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.bjnzoom2.createtestaddon.registry.CTAPartialModels;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class EncasedCustomCogwheelVisual extends KineticBlockEntityVisual<KineticBlockEntity> {
    private final boolean large;

    protected RotatingInstance rotatingModel;
    protected RotatingInstance rotatingTopShaft;
    protected RotatingInstance rotatingBottomShaft;

    public static EncasedCustomCogwheelVisual small(VisualizationContext modelManager, KineticBlockEntity blockEntity, float partialTick) {
        return new EncasedCustomCogwheelVisual(modelManager, blockEntity, partialTick, false, Models.partial(getCogModel(blockEntity,false)));
    }

    public static EncasedCustomCogwheelVisual large(VisualizationContext modelManager, KineticBlockEntity blockEntity, float partialTick) {
        return new EncasedCustomCogwheelVisual(modelManager, blockEntity, partialTick, true, Models.partial(getCogModel(blockEntity,true)));
    }

    public EncasedCustomCogwheelVisual(VisualizationContext modelManager, KineticBlockEntity blockEntity, float partialTick, boolean large, Model model) {
        super(modelManager, blockEntity, partialTick);
        this.large = large;

        rotatingModel = instancerProvider().instancer(AllInstanceTypes.ROTATING, model)
                .createInstance();

        rotatingModel.setup(blockEntity)
                .setPosition(getVisualPosition())
                .rotateToFace(rotationAxis())
                .setChanged();

        RotatingInstance rotatingTopShaft = null;
        RotatingInstance rotatingBottomShaft = null;

        Block block = blockState.getBlock();
        if (block instanceof IRotate def) {
            for (Direction d : Iterate.directionsInAxis(rotationAxis())) {
                if (!def.hasShaftTowards(blockEntity.getLevel(), blockEntity.getBlockPos(), blockState, d))
                    continue;
                RotatingInstance instance = instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(AllPartialModels.SHAFT_HALF))
                        .createInstance();
                instance.setup(blockEntity)
                        .setPosition(getVisualPosition())
                        .rotateToFace(Direction.SOUTH, d)
                        .setChanged();

                if (large) {
                    instance.setRotationOffset(BracketedKineticBlockEntityRenderer.getShaftAngleOffset(rotationAxis(), pos));
                }

                if (d.getAxisDirection() == Direction.AxisDirection.POSITIVE) {
                    rotatingTopShaft = instance;
                } else {
                    rotatingBottomShaft = instance;
                }
            }
        }

        this.rotatingTopShaft = rotatingTopShaft;
        this.rotatingBottomShaft = rotatingBottomShaft;
    }

    @Override
    public void update(float partialTick) {
        rotatingModel.setup(blockEntity)
                .setChanged();
        if (rotatingTopShaft != null) rotatingTopShaft.setup(blockEntity)
                .setChanged();
        if (rotatingBottomShaft != null) rotatingBottomShaft.setup(blockEntity)
                .setChanged();
    }

    @Override
    public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
        consumer.accept(rotatingModel);
        consumer.accept(rotatingTopShaft);
        consumer.accept(rotatingBottomShaft);
    }

    @Override
    public void updateLight(float partialTick) {
        relight(rotatingModel, rotatingTopShaft, rotatingBottomShaft);
    }

    @Override
    protected void _delete() {
        rotatingModel.delete();
        if (rotatingTopShaft != null) rotatingTopShaft.delete();
        if (rotatingBottomShaft != null) rotatingBottomShaft.delete();
    }

    protected static PartialModel getCogModel(BlockEntity blockEntity, boolean large) {
        BlockState referenceState = blockEntity.getBlockState();
        Direction facing =
                Direction.fromAxisAndDirection(referenceState.getValue(BlockStateProperties.AXIS), Direction.AxisDirection.POSITIVE);
        PartialModel partial = large ? CTAPartialModels.SHAFTLESS_LARGE_COGS_MODELS.get(BuiltInRegistries.BLOCK.getKey(((EncasedCustomCogwheelBlock)referenceState.getBlock()).getCogwheel().get()).getPath().replaceAll("_large_cogwheel","")) : CTAPartialModels.SHAFTLESS_COGS_MODELS.get(BuiltInRegistries.BLOCK.getKey(((EncasedCustomCogwheelBlock)referenceState.getBlock()).getCogwheel().get()).getPath().replaceAll("_cogwheel",""));
        return partial;
    }
}
