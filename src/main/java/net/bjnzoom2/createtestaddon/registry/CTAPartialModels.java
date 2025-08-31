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

    public static final Map<String, PartialModel> COGS_MODELS = new HashMap<>();
    public static final Map<String, PartialModel> LARGE_COGS_MODELS = new HashMap<>();
    public static final Map<String, PartialModel> SHAFTLESS_COGS_MODELS = new HashMap<>();
    public static final Map<String, PartialModel> SHAFTLESS_LARGE_COGS_MODELS = new HashMap<>();
    static {
        String[] materials = new String[]{"iron"};
        for (String m : materials) {
            COGS_MODELS.put(m, CTAPartialModels.block("cogwheel/" + m));
            LARGE_COGS_MODELS.put(m, CTAPartialModels.block("large_cogwheel/" + m));
            SHAFTLESS_COGS_MODELS.put(m, CTAPartialModels.block("cogwheel_shaftless/" + m));
            SHAFTLESS_LARGE_COGS_MODELS.put(m, CTAPartialModels.block("large_cogwheel_shaftless/" + m));
        }
    }

    private static PartialModel block(String path) {
        return PartialModel.of(CreateTestAddon.asResource("block/" + path));
    }

    public static void register() {

    }
}
