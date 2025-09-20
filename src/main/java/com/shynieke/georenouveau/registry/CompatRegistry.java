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

	public static final DeferredItem<GeOreGolemCharm> COAL_GEORE_GOLEM_CHARM = ITEMS.registerItem("coal_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.COAL));
	public static final DeferredItem<GeOreGolemCharm> COPPER_GEORE_GOLEM_CHARM = ITEMS.registerItem("copper_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.COPPER));
	public static final DeferredItem<GeOreGolemCharm> DIAMOND_GEORE_GOLEM_CHARM = ITEMS.registerItem("diamond_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.DIAMOND));
	public static final DeferredItem<GeOreGolemCharm> EMERALD_GEORE_GOLEM_CHARM = ITEMS.registerItem("emerald_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.EMERALD));
	public static final DeferredItem<GeOreGolemCharm> GOLD_GEORE_GOLEM_CHARM = ITEMS.registerItem("gold_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.GOLD));
	public static final DeferredItem<GeOreGolemCharm> IRON_GEORE_GOLEM_CHARM = ITEMS.registerItem("iron_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.IRON));
	public static final DeferredItem<GeOreGolemCharm> LAPIS_GEORE_GOLEM_CHARM = ITEMS.registerItem("lapis_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.LAPIS));
	public static final DeferredItem<GeOreGolemCharm> QUARTZ_GEORE_GOLEM_CHARM = ITEMS.registerItem("quartz_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.QUARTZ));
	public static final DeferredItem<GeOreGolemCharm> REDSTONE_GEORE_GOLEM_CHARM = ITEMS.registerItem("redstone_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.REDSTONE));
	public static final DeferredItem<GeOreGolemCharm> ANCIENT_DEBRIS_GEORE_GOLEM_CHARM = ITEMS.registerItem("ancient_debris_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.ANCIENT_DEBRIS));
	public static final DeferredItem<GeOreGolemCharm> RUBY_GEORE_GOLEM_CHARM = ITEMS.registerItem("ruby_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.RUBY));
	public static final DeferredItem<GeOreGolemCharm> SAPPHIRE_GEORE_GOLEM_CHARM = ITEMS.registerItem("sapphire_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.SAPPHIRE));
	public static final DeferredItem<GeOreGolemCharm> TOPAZ_GEORE_GOLEM_CHARM = ITEMS.registerItem("topaz_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.TOPAZ));
	public static final DeferredItem<GeOreGolemCharm> ZINC_GEORE_GOLEM_CHARM = ITEMS.registerItem("zinc_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.ZINC));
	public static final DeferredItem<GeOreGolemCharm> URANINITE_GEORE_GOLEM_CHARM = ITEMS.registerItem("uraninite_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.URANINITE));
	public static final DeferredItem<GeOreGolemCharm> BLACK_QUARTZ_GEORE_GOLEM_CHARM = ITEMS.registerItem("black_quartz_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.BLACK_QUARTZ));
	public static final DeferredItem<GeOreGolemCharm> MONAZITE_GEORE_GOLEM_CHARM = ITEMS.registerItem("monazite_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.MONAZITE));
	public static final DeferredItem<GeOreGolemCharm> ALUMINUM_GEORE_GOLEM_CHARM = ITEMS.registerItem("aluminum_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.ALUMINUM));
	public static final DeferredItem<GeOreGolemCharm> LEAD_GEORE_GOLEM_CHARM = ITEMS.registerItem("lead_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.LEAD));
	public static final DeferredItem<GeOreGolemCharm> NICKEL_GEORE_GOLEM_CHARM = ITEMS.registerItem("nickel_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.NICKEL));
	public static final DeferredItem<GeOreGolemCharm> OSMIUM_GEORE_GOLEM_CHARM = ITEMS.registerItem("osmium_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.OSMIUM));
	public static final DeferredItem<GeOreGolemCharm> PLATINUM_GEORE_GOLEM_CHARM = ITEMS.registerItem("platinum_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.PLATINUM));
	public static final DeferredItem<GeOreGolemCharm> SILVER_GEORE_GOLEM_CHARM = ITEMS.registerItem("silver_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.SILVER));
	public static final DeferredItem<GeOreGolemCharm> TIN_GEORE_GOLEM_CHARM = ITEMS.registerItem("tin_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.TIN));
	public static final DeferredItem<GeOreGolemCharm> TUNGSTEN_GEORE_GOLEM_CHARM = ITEMS.registerItem("tungsten_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.TUNGSTEN));
	public static final DeferredItem<GeOreGolemCharm> URANIUM_GEORE_GOLEM_CHARM = ITEMS.registerItem("uranium_geore_golem_charm", (properties) -> new GeOreGolemCharm(properties, LinkedGeOre.URANIUM));

	public static final DeferredItem<GeOreDowsingRod> COAL_GEORE_DOWSING_ROD = ITEMS.registerItem("coal_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.COAL));
	public static final DeferredItem<GeOreDowsingRod> COPPER_GEORE_DOWSING_ROD = ITEMS.registerItem("copper_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.COPPER));
	public static final DeferredItem<GeOreDowsingRod> DIAMOND_GEORE_DOWSING_ROD = ITEMS.registerItem("diamond_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.DIAMOND));
	public static final DeferredItem<GeOreDowsingRod> EMERALD_GEORE_DOWSING_ROD = ITEMS.registerItem("emerald_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.EMERALD));
	public static final DeferredItem<GeOreDowsingRod> GOLD_GEORE_DOWSING_ROD = ITEMS.registerItem("gold_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.GOLD));
	public static final DeferredItem<GeOreDowsingRod> IRON_GEORE_DOWSING_ROD = ITEMS.registerItem("iron_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.IRON));
	public static final DeferredItem<GeOreDowsingRod> LAPIS_GEORE_DOWSING_ROD = ITEMS.registerItem("lapis_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.LAPIS));
	public static final DeferredItem<GeOreDowsingRod> QUARTZ_GEORE_DOWSING_ROD = ITEMS.registerItem("quartz_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.QUARTZ));
	public static final DeferredItem<GeOreDowsingRod> REDSTONE_GEORE_DOWSING_ROD = ITEMS.registerItem("redstone_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.REDSTONE));
	public static final DeferredItem<GeOreDowsingRod> ANCIENT_DEBRIS_GEORE_DOWSING_ROD = ITEMS.registerItem("ancient_debris_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.ANCIENT_DEBRIS));
	public static final DeferredItem<GeOreDowsingRod> RUBY_GEORE_DOWSING_ROD = ITEMS.registerItem("ruby_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.RUBY));
	public static final DeferredItem<GeOreDowsingRod> SAPPHIRE_GEORE_DOWSING_ROD = ITEMS.registerItem("sapphire_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.SAPPHIRE));
	public static final DeferredItem<GeOreDowsingRod> TOPAZ_GEORE_DOWSING_ROD = ITEMS.registerItem("topaz_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.TOPAZ));
	public static final DeferredItem<GeOreDowsingRod> ZINC_GEORE_DOWSING_ROD = ITEMS.registerItem("zinc_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.ZINC));
	public static final DeferredItem<GeOreDowsingRod> URANINITE_GEORE_DOWSING_ROD = ITEMS.registerItem("uraninite_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.URANINITE));
	public static final DeferredItem<GeOreDowsingRod> BLACK_QUARTZ_GEORE_DOWSING_ROD = ITEMS.registerItem("black_quartz_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.BLACK_QUARTZ));
	public static final DeferredItem<GeOreDowsingRod> MONAZITE_GEORE_DOWSING_ROD = ITEMS.registerItem("monazite_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.MONAZITE));
	public static final DeferredItem<GeOreDowsingRod> ALUMINUM_GEORE_DOWSING_ROD = ITEMS.registerItem("aluminum_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.ALUMINUM));
	public static final DeferredItem<GeOreDowsingRod> LEAD_GEORE_DOWSING_ROD = ITEMS.registerItem("lead_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.LEAD));
	public static final DeferredItem<GeOreDowsingRod> NICKEL_GEORE_DOWSING_ROD = ITEMS.registerItem("nickel_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.NICKEL));
	public static final DeferredItem<GeOreDowsingRod> OSMIUM_GEORE_DOWSING_ROD = ITEMS.registerItem("osmium_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.OSMIUM));
	public static final DeferredItem<GeOreDowsingRod> PLATINUM_GEORE_DOWSING_ROD = ITEMS.registerItem("platinum_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.PLATINUM));
	public static final DeferredItem<GeOreDowsingRod> SILVER_GEORE_DOWSING_ROD = ITEMS.registerItem("silver_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.SILVER));
	public static final DeferredItem<GeOreDowsingRod> TIN_GEORE_DOWSING_ROD = ITEMS.registerItem("tin_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.TIN));
	public static final DeferredItem<GeOreDowsingRod> TUNGSTEN_GEORE_DOWSING_ROD = ITEMS.registerItem("tungsten_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.TUNGSTEN));
	public static final DeferredItem<GeOreDowsingRod> URANIUM_GEORE_DOWSING_ROD = ITEMS.registerItem("uranium_geore_dowsing_rod", (properties) -> new GeOreDowsingRod(properties, LinkedGeOre.URANIUM));

	public static final Supplier<EntityDataSerializer<LinkedGeOre>> LINKED_SERIALIZER = ENTITY_DATA_SERIALIZER.register("linked_geore", () ->
			EntityDataSerializer.forValueType(LinkedGeOre.STREAM_CODEC));

	public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
		event.put(GEORE_GOLEM.get(), GeOreGolem.attributes().build());
	}
}
