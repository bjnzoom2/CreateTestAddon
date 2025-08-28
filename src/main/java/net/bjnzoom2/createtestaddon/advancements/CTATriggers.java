package net.bjnzoom2.createtestaddon.advancements;

import net.minecraft.advancements.CriteriaTriggers;

import java.util.LinkedList;
import java.util.List;

public class CTATriggers {
    private static final List<CriterionTriggerBaseCTA<?>> triggers = new LinkedList<>();

    public static SimpleCTATrigger addSimple(String id) {
        return add(new SimpleCTATrigger(id));
    }

    private static <T extends CriterionTriggerBaseCTA<?>> T add(T instance) {
        triggers.add(instance);
        return instance;
    }

    public static void register() {
        triggers.forEach(CriteriaTriggers::register);
    }
}
