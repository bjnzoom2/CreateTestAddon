package net.bjnzoom2.createtestaddon.block.kinetic.cog_crank;

import com.simibubi.create.content.kinetics.crank.HandCrankBlockEntity;
import dev.engine_room.flywheel.api.instance.Instancer;
import net.bjnzoom2.createtestaddon.registry.ModPartialModels;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraftforge.client.model.data.ModelData;

public class CogCrankBlockEntity extends HandCrankBlockEntity {
    public int inUse;
    public boolean backwards;
    public float independentAngle;
    public float chasingVelocity;

    public CogCrankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void turn(boolean back) {
        boolean update = false;

        if (getGeneratedSpeed() == 0 || back != backwards)
            update = true;

        inUse = 10;
        this.backwards = back;
        if (update && !level.isClientSide)
            updateGeneratedRotation();
    }

    @Override
    public float getIndependentAngle(float partialTicks) {
        return (independentAngle + partialTicks * chasingVelocity) / 360;
    }

    @Override
    public float getGeneratedSpeed() {
        Block block = getBlockState().getBlock();
        if (!(block instanceof CogCrankBlock))
            return 0;
        CogCrankBlock crank = (CogCrankBlock) block;
        int speed = (inUse == 0 ? 0 : clockwise() ? -1 : 1) * crank.getRotationSpeed();
        return speed;
    }

    @Override
    protected boolean clockwise() {
        return backwards;
    }

    @Override
    public void write(CompoundTag compound, boolean clientPacket) {
        compound.putInt("InUse", inUse);
        compound.putBoolean("Backwards", backwards);
        super.write(compound, clientPacket);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        inUse = compound.getInt("InUse");
        backwards = compound.getBoolean("Backwards");
        super.read(compound, clientPacket);
    }

    @Override
    public void tick() {
        super.tick();

        float actualSpeed = getSpeed();
        chasingVelocity += ((actualSpeed * 10 / 3f) - chasingVelocity) * .25f;
        independentAngle += chasingVelocity;

        if (inUse > 0) {
            inUse--;

            if (inUse == 0 && !level.isClientSide) {
                sequenceContext = null;
                updateGeneratedRotation();
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public SuperByteBuffer getRenderedHandle() {
        BlockState blockState = getBlockState();
        Direction facing = blockState.getOptionalValue(CogCrankBlock.FACING)
                .orElse(Direction.UP);
        return CachedBuffers.partialFacing(ModPartialModels.COG_CRANK_HANDLE, blockState, facing.getOpposite());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Instancer<ModelData> getRenderedHandleInstance(Material<ModelData> material) {
        BlockState blockState = getBlockState();
        Direction facing = blockState.getOptionalValue(CogCrankBlock.FACING)
                .orElse(Direction.UP);
        return material.getModel(ModPartialModels.COG_CRANK_HANDLE, blockState, facing.getOpposite());
    }
}
