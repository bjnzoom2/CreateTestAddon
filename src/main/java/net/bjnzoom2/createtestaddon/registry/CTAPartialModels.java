package net.bjnzoom2.createtestaddon.registry;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.bjnzoom2.createtestaddon.CreateTestAddon;

import java.util.HashMap;
import java.util.Map;

public class CTAPartialModels {
    public static final PartialModel CRANK_WHEEL_HANDLE = block("crank_wheel/handle");
    public static final PartialModel CRANK_WHEEL_BASE = block("crank_wheel/block");
    public static final PartialModel LARGE_CRANK_WHEEL_HANDLE = block("large_crank_wheel/handle");
    public static final PartialModel LARGE_CRANK_WHEEL_BASE = block("large_crank_wheel/block");

    private static PartialModel block(String path) {
        return PartialModel.of(CreateTestAddon.asResource("block/" + path));
    }

    public static void register() {

    }
}
