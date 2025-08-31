package net.bjnzoom2.createtestaddon.block.kinetic.custom_cogs;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visual.BlockEntityVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import net.bjnzoom2.createtestaddon.registry.CTAPartialModels;
import net.minecraft.core.Direction;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class CustomCogwheelBlockEntityVisual {
    public static BlockEntityVisual<BracketedKineticBlockEntity> create(VisualizationContext context, BracketedKineticBlockEntity blockEntity, float partialTick) {
        if (ICogWheel.isLargeCog(blockEntity.getBlockState())) {
            return new LargeCogVisual(context, blockEntity, partialTick);
        } else {
            Model model = Models.partial(CTAPartialModels.COGS_MODELS.get(ForgeRegistries.BLOCKS.getKey(blockEntity.getBlockState().getBlock()).getPath().replaceAll("_cogwheel","")));
            return new SingleAxisRotatingVisual<>(context, blockEntity, partialTick, model);
        }
    }

    public static class LargeCogVisual extends SingleAxisRotatingVisual<BracketedKineticBlockEntity> {

        protected final RotatingInstance additionalShaft;

        private LargeCogVisual(VisualizationContext context, BracketedKineticBlockEntity blockEntity, float partialTick) {
            super(context, blockEntity, partialTick, Models.partial(CTAPartialModels.SHAFTLESS_LARGE_COGS_MODELS.get(ForgeRegistries.BLOCKS.getKey(blockEntity.getBlockState().getBlock()).getPath().replaceAll("_large_cogwheel",""))));

            Direction.Axis axis = KineticBlockEntityRenderer.getRotationAxisOf(blockEntity);

            additionalShaft = instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(AllPartialModels.COGWHEEL_SHAFT))
                    .createInstance();

            additionalShaft.rotateToFace(axis)
                    .setup(blockEntity)
                    .setRotationOffset(BracketedKineticBlockEntityRenderer.getShaftAngleOffset(axis, pos))
                    .setPosition(getVisualPosition())
                    .setChanged();
        }

        @Override
        public void update(float pt) {
            super.update(pt);
            additionalShaft.setup(blockEntity)
                    .setRotationOffset(BracketedKineticBlockEntityRenderer.getShaftAngleOffset(rotationAxis(), pos))
                    .setChanged();
        }

        @Override
        public void updateLight(float partialTick) {
            super.updateLight(partialTick);
            relight(additionalShaft);
        }

        @Override
        protected void _delete() {
            super._delete();
            additionalShaft.delete();
        }

        @Override
        public void collectCrumblingInstances(Consumer<Instance> consumer) {
            super.collectCrumblingInstances(consumer);
            consumer.accept(additionalShaft);
        }
    }
}
