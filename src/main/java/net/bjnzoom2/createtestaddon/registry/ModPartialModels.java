package net.bjnzoom2.createtestaddon.registry;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.bjnzoom2.createtestaddon.CreateTestAddon;

public class ModPartialModels {
    public static final PartialModel COG_CRANK_HANDLE = block("cog_crank/handle");

    private static PartialModel block(String path) {
        return new PartialModel(CreateTestAddon.asResource("block/" + path));
    }

    public static void init() {
        // init static fields
    }
}
