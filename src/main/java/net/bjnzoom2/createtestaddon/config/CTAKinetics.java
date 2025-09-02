package net.bjnzoom2.createtestaddon.config;

import net.createmod.catnip.config.ConfigBase;

public class CTAKinetics extends ConfigBase {
    public final ConfigFloat crankWheelHungerMultiplier = f(.01f, 0, 1, "crankWheelHungerMultiplier", Comments.crankWheelHungerMultiplier);

    @Override
    public String getName() {
        return "kinetics";
    }

    private static class Comments {
        static String crankWheelHungerMultiplier = "multiplier used for calculating exhaustion from speed when a crank wheel is turned.";
    }
}
