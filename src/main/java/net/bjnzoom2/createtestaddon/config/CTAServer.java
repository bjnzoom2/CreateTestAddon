package net.bjnzoom2.createtestaddon.config;

import net.createmod.catnip.config.ConfigBase;

public class CTAServer extends ConfigBase {
    @Override
    public String getName() {
        return "server";
    }

    public final ConfigGroup infrastructure = group(0, "infrastructure", Comments.infrastructure);

    public final CTAKinetics kinetics = nested(0, CTAKinetics::new, Comments.kinetics);

    private static class Comments {
        static String kinetics = "Parameters and abilities of Create: Test Addon's kinetic mechanisms";
        static String infrastructure = "The Backbone of Create: Test Addon";
    }
}
