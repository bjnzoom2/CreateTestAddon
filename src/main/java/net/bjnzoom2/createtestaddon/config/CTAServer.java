package net.bjnzoom2.createtestaddon.config;

import net.createmod.catnip.config.ConfigBase;

public class CTAServer extends ConfigBase {
    public final CTAStress stressValues = nested(0, CTAStress::new, Comments.stress);
    public final CTAKinetics kinetics = nested(0, CTAKinetics::new, Comments.kinetics);

    @Override
    public String getName() {
        return "server";
    }

    private static class Comments {
        static String stress = "Fine tune the kinetic stats of individual components";
        static String kinetics = "Parameters and abilities of Create: Test Addon's kinetic mechanisms";
    }
}
