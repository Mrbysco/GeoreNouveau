package com.shynieke.georenouveau.registry;

import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.entity.GeOreGolem;
import com.shynieke.georenouveau.entity.LinkedGeOre;
import com.shynieke.georenouveau.entity.serializer.LinkedGeOreSerializer;
import com.shynieke.georenouveau.item.GeOreDowsingRod;
import com.shynieke.georenouveau.item.GeOreGolemCharm;
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

	public static final RegistryObject<GeOreGolemCharm> COAL_GEORE_GOLEM_CHARM = ITEMS.register("coal_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.COAL));
	public static final RegistryObject<GeOreGolemCharm> COPPER_GEORE_GOLEM_CHARM = ITEMS.register("copper_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.COPPER));
	public static final RegistryObject<GeOreGolemCharm> DIAMOND_GEORE_GOLEM_CHARM = ITEMS.register("diamond_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.DIAMOND));
	public static final RegistryObject<GeOreGolemCharm> EMERALD_GEORE_GOLEM_CHARM = ITEMS.register("emerald_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.EMERALD));
	public static final RegistryObject<GeOreGolemCharm> GOLD_GEORE_GOLEM_CHARM = ITEMS.register("gold_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.GOLD));
	public static final RegistryObject<GeOreGolemCharm> IRON_GEORE_GOLEM_CHARM = ITEMS.register("iron_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.IRON));
	public static final RegistryObject<GeOreGolemCharm> LAPIS_GEORE_GOLEM_CHARM = ITEMS.register("lapis_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.LAPIS));
	public static final RegistryObject<GeOreGolemCharm> QUARTZ_GEORE_GOLEM_CHARM = ITEMS.register("quartz_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.QUARTZ));
	public static final RegistryObject<GeOreGolemCharm> REDSTONE_GEORE_GOLEM_CHARM = ITEMS.register("redstone_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.REDSTONE));
	public static final RegistryObject<GeOreGolemCharm> ANCIENT_DEBRIS_GEORE_GOLEM_CHARM = ITEMS.register("ancient_debris_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.ANCIENT_DEBRIS));
	public static final RegistryObject<GeOreGolemCharm> RUBY_GEORE_GOLEM_CHARM = ITEMS.register("ruby_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.RUBY));
	public static final RegistryObject<GeOreGolemCharm> SAPPHIRE_GEORE_GOLEM_CHARM = ITEMS.register("sapphire_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.SAPPHIRE));
	public static final RegistryObject<GeOreGolemCharm> TOPAZ_GEORE_GOLEM_CHARM = ITEMS.register("topaz_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.TOPAZ));
	public static final RegistryObject<GeOreGolemCharm> ZINC_GEORE_GOLEM_CHARM = ITEMS.register("zinc_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.ZINC));
	public static final RegistryObject<GeOreGolemCharm> URANINITE_GEORE_GOLEM_CHARM = ITEMS.register("uraninite_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.URANINITE));
	public static final RegistryObject<GeOreGolemCharm> BLACK_QUARTZ_GEORE_GOLEM_CHARM = ITEMS.register("black_quartz_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.BLACK_QUARTZ));
	public static final RegistryObject<GeOreGolemCharm> MONAZITE_GEORE_GOLEM_CHARM = ITEMS.register("monazite_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.MONAZITE));
	public static final RegistryObject<GeOreGolemCharm> ALUMINUM_GEORE_GOLEM_CHARM = ITEMS.register("aluminum_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.ALUMINUM));
	public static final RegistryObject<GeOreGolemCharm> LEAD_GEORE_GOLEM_CHARM = ITEMS.register("lead_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.LEAD));
	public static final RegistryObject<GeOreGolemCharm> NICKEL_GEORE_GOLEM_CHARM = ITEMS.register("nickel_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.NICKEL));
	public static final RegistryObject<GeOreGolemCharm> OSMIUM_GEORE_GOLEM_CHARM = ITEMS.register("osmium_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.OSMIUM));
	public static final RegistryObject<GeOreGolemCharm> PLATINUM_GEORE_GOLEM_CHARM = ITEMS.register("platinum_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.PLATINUM));
	public static final RegistryObject<GeOreGolemCharm> SILVER_GEORE_GOLEM_CHARM = ITEMS.register("silver_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.SILVER));
	public static final RegistryObject<GeOreGolemCharm> TIN_GEORE_GOLEM_CHARM = ITEMS.register("tin_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.TIN));
	public static final RegistryObject<GeOreGolemCharm> TUNGSTEN_GEORE_GOLEM_CHARM = ITEMS.register("tungsten_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.TUNGSTEN));
	public static final RegistryObject<GeOreGolemCharm> URANIUM_GEORE_GOLEM_CHARM = ITEMS.register("uranium_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.URANIUM));
	public static final RegistryObject<GeOreGolemCharm> ALLTHEMODIUM_GEORE_GOLEM_CHARM = ITEMS.register("allthemodium_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.ALLTHEMODIUM));
	public static final RegistryObject<GeOreGolemCharm> VIBRANIUM_GEORE_GOLEM_CHARM = ITEMS.register("vibranium_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.VIBRANIUM));
	public static final RegistryObject<GeOreGolemCharm> UNOBTAINIUM_GEORE_GOLEM_CHARM = ITEMS.register("unobtainium_geore_golem_charm", () -> new GeOreGolemCharm(LinkedGeOre.UNOBTAINIUM));

	public static final RegistryObject<GeOreDowsingRod> COAL_GEORE_DOWSING_ROD = ITEMS.register("coal_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.COAL));
	public static final RegistryObject<GeOreDowsingRod> COPPER_GEORE_DOWSING_ROD = ITEMS.register("copper_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.COPPER));
	public static final RegistryObject<GeOreDowsingRod> DIAMOND_GEORE_DOWSING_ROD = ITEMS.register("diamond_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.DIAMOND));
	public static final RegistryObject<GeOreDowsingRod> EMERALD_GEORE_DOWSING_ROD = ITEMS.register("emerald_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.EMERALD));
	public static final RegistryObject<GeOreDowsingRod> GOLD_GEORE_DOWSING_ROD = ITEMS.register("gold_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.GOLD));
	public static final RegistryObject<GeOreDowsingRod> IRON_GEORE_DOWSING_ROD = ITEMS.register("iron_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.IRON));
	public static final RegistryObject<GeOreDowsingRod> LAPIS_GEORE_DOWSING_ROD = ITEMS.register("lapis_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.LAPIS));
	public static final RegistryObject<GeOreDowsingRod> QUARTZ_GEORE_DOWSING_ROD = ITEMS.register("quartz_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.QUARTZ));
	public static final RegistryObject<GeOreDowsingRod> REDSTONE_GEORE_DOWSING_ROD = ITEMS.register("redstone_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.REDSTONE));
	public static final RegistryObject<GeOreDowsingRod> ANCIENT_DEBRIS_GEORE_DOWSING_ROD = ITEMS.register("ancient_debris_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.ANCIENT_DEBRIS));
	public static final RegistryObject<GeOreDowsingRod> RUBY_GEORE_DOWSING_ROD = ITEMS.register("ruby_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.RUBY));
	public static final RegistryObject<GeOreDowsingRod> SAPPHIRE_GEORE_DOWSING_ROD = ITEMS.register("sapphire_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.SAPPHIRE));
	public static final RegistryObject<GeOreDowsingRod> TOPAZ_GEORE_DOWSING_ROD = ITEMS.register("topaz_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.TOPAZ));
	public static final RegistryObject<GeOreDowsingRod> ZINC_GEORE_DOWSING_ROD = ITEMS.register("zinc_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.ZINC));
	public static final RegistryObject<GeOreDowsingRod> URANINITE_GEORE_DOWSING_ROD = ITEMS.register("uraninite_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.URANINITE));
	public static final RegistryObject<GeOreDowsingRod> BLACK_QUARTZ_GEORE_DOWSING_ROD = ITEMS.register("black_quartz_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.BLACK_QUARTZ));
	public static final RegistryObject<GeOreDowsingRod> MONAZITE_GEORE_DOWSING_ROD = ITEMS.register("monazite_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.MONAZITE));
	public static final RegistryObject<GeOreDowsingRod> ALUMINUM_GEORE_DOWSING_ROD = ITEMS.register("aluminum_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.ALUMINUM));
	public static final RegistryObject<GeOreDowsingRod> LEAD_GEORE_DOWSING_ROD = ITEMS.register("lead_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.LEAD));
	public static final RegistryObject<GeOreDowsingRod> NICKEL_GEORE_DOWSING_ROD = ITEMS.register("nickel_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.NICKEL));
	public static final RegistryObject<GeOreDowsingRod> OSMIUM_GEORE_DOWSING_ROD = ITEMS.register("osmium_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.OSMIUM));
	public static final RegistryObject<GeOreDowsingRod> PLATINUM_GEORE_DOWSING_ROD = ITEMS.register("platinum_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.PLATINUM));
	public static final RegistryObject<GeOreDowsingRod> SILVER_GEORE_DOWSING_ROD = ITEMS.register("silver_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.SILVER));
	public static final RegistryObject<GeOreDowsingRod> TIN_GEORE_DOWSING_ROD = ITEMS.register("tin_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.TIN));
	public static final RegistryObject<GeOreDowsingRod> TUNGSTEN_GEORE_DOWSING_ROD = ITEMS.register("tungsten_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.TUNGSTEN));
	public static final RegistryObject<GeOreDowsingRod> URANIUM_GEORE_DOWSING_ROD = ITEMS.register("uranium_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.URANIUM));
	public static final RegistryObject<GeOreDowsingRod> ALLTHEMODIUM_GEORE_DOWSING_ROD = ITEMS.register("allthemodium_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.ALLTHEMODIUM));
	public static final RegistryObject<GeOreDowsingRod> VIBRANIUM_GEORE_DOWSING_ROD = ITEMS.register("vibranium_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.VIBRANIUM));
	public static final RegistryObject<GeOreDowsingRod> UNOBTAINIUM_GEORE_DOWSING_ROD = ITEMS.register("unobtainium_geore_dowsing_rod", () -> new GeOreDowsingRod(LinkedGeOre.UNOBTAINIUM));

	public static final RegistryObject<EntityDataSerializer<LinkedGeOre>> LINKED_SERIALIZER = ENTITY_DATA_SERIALIZER.register("linked_geore", () -> new LinkedGeOreSerializer());

	public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
		event.put(GEORE_GOLEM.get(), GeOreGolem.attributes().build());
	}
}
