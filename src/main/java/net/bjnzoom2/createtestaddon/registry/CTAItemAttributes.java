package net.bjnzoom2.createtestaddon.registry;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.content.logistics.item.filter.attribute.ItemAttribute;
import com.simibubi.create.content.logistics.item.filter.attribute.ItemAttributeType;
import net.bjnzoom2.createtestaddon.CreateTestAddon;
import net.minecraft.core.Registry;

public class CTAItemAttributes {
    public static void register() {
        LegacyDeserializers.register();
    }

    private static ItemAttributeType register(String id, ItemAttributeType type) {
        return Registry.register(CreateBuiltInRegistries.ITEM_ATTRIBUTE_TYPE, CreateTestAddon.asResource(id), type);
    }

    @SuppressWarnings("deprecation")
    public static class LegacyDeserializers {
        public static void register() {

        }

        private static void addLegacyDeserializer(ItemAttribute.LegacyDeserializer legacyDeserializer) {
            ItemAttribute.LegacyDeserializer.ALL.add(legacyDeserializer);
        }
    }
}
