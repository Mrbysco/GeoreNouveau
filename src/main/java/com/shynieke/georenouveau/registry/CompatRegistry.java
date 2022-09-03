package com.shynieke.georenouveau.registry;

import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.entity.GeOreGolem;
import com.shynieke.georenouveau.entity.LinkedGeOre;
import com.shynieke.georenouveau.entity.serializer.LinkedGeOreSerializer;
import com.shynieke.georenouveau.item.GeOreGolemCharm;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegistryObject;

public class CompatRegistry {
	public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZER = DeferredRegister.create(Keys.ENTITY_DATA_SERIALIZERS, GeOreNouveau.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GeOreNouveau.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GeOreNouveau.MOD_ID);

	public static final RegistryObject<EntityType<GeOreGolem>> GEORE_GOLEM = ENTITY_TYPES.register("geore_golem", () -> EntityType.Builder.<GeOreGolem>of(GeOreGolem::new, MobCategory.CREATURE).sized(1.0f, 1.0f).setTrackingRange(10).build("geore_golem"));

	public static final RegistryObject<Item> COAL_GEORE_GOLEM_CHARM = ITEMS.register("coal_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.COAL).withTooltip(Component.translatable("tooltip.ars_nouveau.coal_charm")));
	public static final RegistryObject<Item> COPPER_GEORE_GOLEM_CHARM = ITEMS.register("copper_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.COPPER).withTooltip(Component.translatable("tooltip.ars_nouveau.copper_charm")));
	public static final RegistryObject<Item> DIAMOND_GEORE_GOLEM_CHARM = ITEMS.register("diamond_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.DIAMOND).withTooltip(Component.translatable("tooltip.ars_nouveau.diamond_charm")));
	public static final RegistryObject<Item> EMERALD_GEORE_GOLEM_CHARM = ITEMS.register("emerald_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.EMERALD).withTooltip(Component.translatable("tooltip.ars_nouveau.emerald_charm")));
	public static final RegistryObject<Item> GOLD_GEORE_GOLEM_CHARM = ITEMS.register("gold_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.GOLD).withTooltip(Component.translatable("tooltip.ars_nouveau.gold_charm")));
	public static final RegistryObject<Item> IRON_GEORE_GOLEM_CHARM = ITEMS.register("iron_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.IRON).withTooltip(Component.translatable("tooltip.ars_nouveau.iron_charm")));
	public static final RegistryObject<Item> LAPIS_GEORE_GOLEM_CHARM = ITEMS.register("lapis_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.LAPIS).withTooltip(Component.translatable("tooltip.ars_nouveau.lapis_charm")));
	public static final RegistryObject<Item> QUARTZ_GEORE_GOLEM_CHARM = ITEMS.register("quartz_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.QUARTZ).withTooltip(Component.translatable("tooltip.ars_nouveau.quartz_charm")));
	public static final RegistryObject<Item> REDSTONE_GEORE_GOLEM_CHARM = ITEMS.register("redstone_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.REDSTONE).withTooltip(Component.translatable("tooltip.ars_nouveau.redstone_charm")));
	public static final RegistryObject<Item> RUBY_GEORE_GOLEM_CHARM = ITEMS.register("ruby_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.RUBY).withTooltip(Component.translatable("tooltip.ars_nouveau.ruby_charm")));
	public static final RegistryObject<Item> SAPPHIRE_GEORE_GOLEM_CHARM = ITEMS.register("sapphire_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.SAPPHIRE).withTooltip(Component.translatable("tooltip.ars_nouveau.sapphire_charm")));
	public static final RegistryObject<Item> TOPAZ_GEORE_GOLEM_CHARM = ITEMS.register("topaz_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.TOPAZ).withTooltip(Component.translatable("tooltip.ars_nouveau.topaz_charm")));

	public static final RegistryObject<EntityDataSerializer<LinkedGeOre>> LINKED_SERIALIZER = ENTITY_DATA_SERIALIZER.register("linked_geore", () -> new LinkedGeOreSerializer());

	public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
		event.put(GEORE_GOLEM.get(), GeOreGolem.attributes().build());
	}
}
