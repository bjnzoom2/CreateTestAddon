package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import net.bjnzoom2.createtestaddon.CreateTestAddon;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SpriteShifter;

public class CTASpriteShifts {
    public static final CTSpriteShiftEntry IRON_CASING = omni("iron_casing");
    public static final CTSpriteShiftEntry IRON_ENCASED_COGWHEEL_SIDE = vertical("iron_encased_cogwheel_side"), IRON_ENCASED_COGWHEEL_OTHERSIDE = horizontal("iron_encased_cogwheel_side");

    public static CTSpriteShiftEntry omni(String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }

    public static CTSpriteShiftEntry horizontal(String name) {
        return getCT(AllCTTypes.HORIZONTAL_KRYPPERS, name);
    }

    private static CTSpriteShiftEntry vertical(String name) {
        return getCT(AllCTTypes.VERTICAL, name);
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, CreateTestAddon.asResource("block/" + blockTextureName), CreateTestAddon.asResource("block/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }

    private static SpriteShiftEntry get(String originalLocation, String targetLocation) {
        return SpriteShifter.get(CreateTestAddon.asResource(originalLocation), CreateTestAddon.asResource(targetLocation));
    }
}
