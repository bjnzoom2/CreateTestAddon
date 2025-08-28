package net.bjnzoom2.createtestaddon.advancements;

import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SimpleCTATrigger extends CriterionTriggerBaseCTA<SimpleCTATrigger.Instance> {
    public SimpleCTATrigger(String id) {
        super(id);
    }

    @Override
    public SimpleCTATrigger.Instance createInstance(JsonObject jsonObject, DeserializationContext deserializationContext) {
        return new SimpleCTATrigger.Instance(getId());
    }

    public void trigger(ServerPlayer player) {
        super.trigger(player, null);
    }

    public SimpleCTATrigger.Instance instance() {
        return new SimpleCTATrigger.Instance(getId());
    }

    public static class Instance extends CriterionTriggerBaseCTA.Instance {

        public Instance(ResourceLocation idIn) {
            super(idIn, ContextAwarePredicate.ANY);
        }

        @Override
        protected boolean test(@Nullable List<Supplier<Object>> suppliers) {
            return true;
        }
    }
}
