package com.shynieke.georenouveau.data;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GNDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeServer(), new Loots(generator));
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new Language(generator));
			generator.addProvider(event.includeClient(), new ItemModels(generator, helper));
		}
	}

	private static class Language extends LanguageProvider {
		public Language(DataGenerator gen) {
			super(gen, GeOreNouveau.MOD_ID, "en_us");
		}

		@Override
		protected void addTranslations() {
			addEntityType(CompatRegistry.GEORE_GOLEM, "GeOre Golem");
			generateLang(CompatRegistry.COAL_GEORE_GOLEM_CHARM, "Coal");
			generateLang(CompatRegistry.COPPER_GEORE_GOLEM_CHARM, "Copper");
			generateLang(CompatRegistry.DIAMOND_GEORE_GOLEM_CHARM, "Diamond");
			generateLang(CompatRegistry.EMERALD_GEORE_GOLEM_CHARM, "Emerald");
			generateLang(CompatRegistry.GOLD_GEORE_GOLEM_CHARM, "Gold");
			generateLang(CompatRegistry.IRON_GEORE_GOLEM_CHARM, "Iron");
			generateLang(CompatRegistry.LAPIS_GEORE_GOLEM_CHARM, "Lapis");
			generateLang(CompatRegistry.QUARTZ_GEORE_GOLEM_CHARM, "Quartz");
			generateLang(CompatRegistry.REDSTONE_GEORE_GOLEM_CHARM, "Redstone");
			generateLang(CompatRegistry.RUBY_GEORE_GOLEM_CHARM, "Ruby");
			generateLang(CompatRegistry.SAPPHIRE_GEORE_GOLEM_CHARM, "Sapphire");
			generateLang(CompatRegistry.TOPAZ_GEORE_GOLEM_CHARM, "Topaz");
		}

		protected void generateLang(RegistryObject<Item> registryObject, String name) {
			addItem(registryObject, name + " GeOre Golem Charm");
			add("tooltip.ars_nouveau." + name.toLowerCase(Locale.ROOT) + "_charm", "Obtained by performing the Ritual of Awakening near Budding " + name + " Geore");
		}
	}

	private static class ItemModels extends ItemModelProvider {
		public ItemModels(DataGenerator gen, ExistingFileHelper helper) {
			super(gen, GeOreNouveau.MOD_ID, helper);
		}

		@Override
		protected void registerModels() {
			generateCharm(CompatRegistry.COAL_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.COPPER_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.DIAMOND_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.EMERALD_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.GOLD_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.IRON_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.LAPIS_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.QUARTZ_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.REDSTONE_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.RUBY_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.SAPPHIRE_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.TOPAZ_GEORE_GOLEM_CHARM);
		}

		protected void generateCharm(RegistryObject<Item> registryObject) {
			singleTexture(registryObject.getId().getPath(), new ResourceLocation("item/generated"),
					"layer0", modLoc("items/amethyst_golem_charm"));
		}
	}

	private static class Loots extends LootTableProvider {
		public Loots(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
			return ImmutableList.of(
					Pair.of(Loots.CompatBlockTables::new, LootContextParamSets.ENTITY)
			);
		}

		public static class CompatBlockTables extends EntityLoot {

			@Override
			protected void addTables() {
				this.add(CompatRegistry.GEORE_GOLEM.get(), LootTable.lootTable());
			}

			@Override
			protected Iterable<EntityType<?>> getKnownEntities() {
				Stream<EntityType<?>> entities = CompatRegistry.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
				return (Iterable<EntityType<?>>) entities::iterator;
			}
		}

		@Override
		protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
			map.forEach((name, table) -> LootTables.validate(validationContext, name, table));
		}
	}
}
