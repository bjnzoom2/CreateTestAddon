package net.bjnzoom2.createtestaddon.config;

import net.createmod.catnip.config.ConfigBase;

public class CTAKinetics extends ConfigBase {
    public final ConfigFloat cogCrankHungerMultiplier = f(.01f, 0, 1, "cogCrankHungerMultiplier", Comments.cogCrankHungerMultiplier);

    public final ConfigGroup stats = group(1, "stats", Comments.stats);

    @Override
    public String getName() {
        return "kinetics";
    }

    private static class Comments {
        static String cogCrankHungerMultiplier = "multiplier used for calculating exhaustion from speed when a Cog Crank is turned.";
        static String stats = "Configure speed/capacity levels for requirements and indicators.";
    }
}
