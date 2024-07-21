package com.shynieke.georenouveau.registry;

import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.entity.GeOreGolem;
import com.shynieke.georenouveau.entity.LinkedGeOre;
import com.shynieke.georenouveau.item.GeOreDowsingRod;
import com.shynieke.georenouveau.item.GeOreGolemCharm;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class CompatRegistry {
	public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZER = DeferredRegister.create(NeoForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, GeOreNouveau.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, GeOreNouveau.MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GeOreNouveau.MOD_ID);

	public static final Supplier<EntityType<GeOreGolem>> GEORE_GOLEM = ENTITY_TYPES.register("geore_golem", () ->
			EntityType.Builder.<GeOreGolem>of(GeOreGolem::new, MobCategory.CREATURE)
					.sized(1.0f, 1.0f)
					.setTrackingRange(10)
					.build("geore_golem"));

	public static final DeferredItem<GeOreGolemCharm> COAL_GEORE_GOLEM_CHARM = ITEMS.register("coal_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.COAL));
	public static final DeferredItem<GeOreGolemCharm> COPPER_GEORE_GOLEM_CHARM = ITEMS.register("copper_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.COPPER));
	public static final DeferredItem<GeOreGolemCharm> DIAMOND_GEORE_GOLEM_CHARM = ITEMS.register("diamond_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.DIAMOND));
	public static final DeferredItem<GeOreGolemCharm> EMERALD_GEORE_GOLEM_CHARM = ITEMS.register("emerald_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.EMERALD));
	public static final DeferredItem<GeOreGolemCharm> GOLD_GEORE_GOLEM_CHARM = ITEMS.register("gold_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.GOLD));
	public static final DeferredItem<GeOreGolemCharm> IRON_GEORE_GOLEM_CHARM = ITEMS.register("iron_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.IRON));
	public static final DeferredItem<GeOreGolemCharm> LAPIS_GEORE_GOLEM_CHARM = ITEMS.register("lapis_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.LAPIS));
	public static final DeferredItem<GeOreGolemCharm> QUARTZ_GEORE_GOLEM_CHARM = ITEMS.register("quartz_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.QUARTZ));
	public static final DeferredItem<GeOreGolemCharm> REDSTONE_GEORE_GOLEM_CHARM = ITEMS.register("redstone_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.REDSTONE));
	public static final DeferredItem<GeOreGolemCharm> RUBY_GEORE_GOLEM_CHARM = ITEMS.register("ruby_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.RUBY));
	public static final DeferredItem<GeOreGolemCharm> SAPPHIRE_GEORE_GOLEM_CHARM = ITEMS.register("sapphire_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.SAPPHIRE));
	public static final DeferredItem<GeOreGolemCharm> TOPAZ_GEORE_GOLEM_CHARM = ITEMS.register("topaz_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.TOPAZ));
	public static final DeferredItem<GeOreGolemCharm> ZINC_GEORE_GOLEM_CHARM = ITEMS.register("zinc_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.ZINC));

	public static final DeferredItem<GeOreDowsingRod> COAL_GEORE_DOWSING_ROD = ITEMS.register("coal_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.COAL));
	public static final DeferredItem<GeOreDowsingRod> COPPER_GEORE_DOWSING_ROD = ITEMS.register("copper_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.COPPER));
	public static final DeferredItem<GeOreDowsingRod> DIAMOND_GEORE_DOWSING_ROD = ITEMS.register("diamond_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.DIAMOND));
	public static final DeferredItem<GeOreDowsingRod> EMERALD_GEORE_DOWSING_ROD = ITEMS.register("emerald_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.EMERALD));
	public static final DeferredItem<GeOreDowsingRod> GOLD_GEORE_DOWSING_ROD = ITEMS.register("gold_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.GOLD));
	public static final DeferredItem<GeOreDowsingRod> IRON_GEORE_DOWSING_ROD = ITEMS.register("iron_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.IRON));
	public static final DeferredItem<GeOreDowsingRod> LAPIS_GEORE_DOWSING_ROD = ITEMS.register("lapis_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.LAPIS));
	public static final DeferredItem<GeOreDowsingRod> QUARTZ_GEORE_DOWSING_ROD = ITEMS.register("quartz_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.QUARTZ));
	public static final DeferredItem<GeOreDowsingRod> REDSTONE_GEORE_DOWSING_ROD = ITEMS.register("redstone_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.REDSTONE));
	public static final DeferredItem<GeOreDowsingRod> RUBY_GEORE_DOWSING_ROD = ITEMS.register("ruby_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.RUBY));
	public static final DeferredItem<GeOreDowsingRod> SAPPHIRE_GEORE_DOWSING_ROD = ITEMS.register("sapphire_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.SAPPHIRE));
	public static final DeferredItem<GeOreDowsingRod> TOPAZ_GEORE_DOWSING_ROD = ITEMS.register("topaz_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.TOPAZ));
	public static final DeferredItem<GeOreDowsingRod> ZINC_GEORE_DOWSING_ROD = ITEMS.register("zinc_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.ZINC));

	public static final Supplier<EntityDataSerializer<LinkedGeOre>> LINKED_SERIALIZER = ENTITY_DATA_SERIALIZER.register("linked_geore", () ->
			EntityDataSerializer.forValueType(LinkedGeOre.STREAM_CODEC));

	public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
		event.put(GEORE_GOLEM.get(), GeOreGolem.attributes().build());
	}
}
