package com.shynieke.georenouveau.data;

import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GNDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeServer(), new Loots(packOutput));
		generator.addProvider(event.includeServer(), new Recipes(packOutput));
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new Language(packOutput));
			generator.addProvider(event.includeClient(), new ItemModels(packOutput, helper));
		}
	}

	private static class Language extends LanguageProvider {
		public Language(PackOutput packOutput) {
			super(packOutput, GeOreNouveau.MOD_ID, "en_us");
		}

		@Override
		protected void addTranslations() {
			addEntityType(CompatRegistry.GEORE_GOLEM, "GeOre Golem");
			generateCharmLang(CompatRegistry.COAL_GEORE_GOLEM_CHARM, "Coal");
			generateCharmLang(CompatRegistry.COPPER_GEORE_GOLEM_CHARM, "Copper");
			generateCharmLang(CompatRegistry.DIAMOND_GEORE_GOLEM_CHARM, "Diamond");
			generateCharmLang(CompatRegistry.EMERALD_GEORE_GOLEM_CHARM, "Emerald");
			generateCharmLang(CompatRegistry.GOLD_GEORE_GOLEM_CHARM, "Gold");
			generateCharmLang(CompatRegistry.IRON_GEORE_GOLEM_CHARM, "Iron");
			generateCharmLang(CompatRegistry.LAPIS_GEORE_GOLEM_CHARM, "Lapis");
			generateCharmLang(CompatRegistry.QUARTZ_GEORE_GOLEM_CHARM, "Quartz");
			generateCharmLang(CompatRegistry.REDSTONE_GEORE_GOLEM_CHARM, "Redstone");
			generateCharmLang(CompatRegistry.RUBY_GEORE_GOLEM_CHARM, "Ruby");
			generateCharmLang(CompatRegistry.SAPPHIRE_GEORE_GOLEM_CHARM, "Sapphire");
			generateCharmLang(CompatRegistry.TOPAZ_GEORE_GOLEM_CHARM, "Topaz");
			generateCharmLang(CompatRegistry.ZINC_GEORE_GOLEM_CHARM, "Zinc");

			generateDowsingLang(CompatRegistry.COAL_GEORE_DOWSING_ROD, "Coal");
			generateDowsingLang(CompatRegistry.COPPER_GEORE_DOWSING_ROD, "Copper");
			generateDowsingLang(CompatRegistry.DIAMOND_GEORE_DOWSING_ROD, "Diamond");
			generateDowsingLang(CompatRegistry.EMERALD_GEORE_DOWSING_ROD, "Emerald");
			generateDowsingLang(CompatRegistry.GOLD_GEORE_DOWSING_ROD, "Gold");
			generateDowsingLang(CompatRegistry.IRON_GEORE_DOWSING_ROD, "Iron");
			generateDowsingLang(CompatRegistry.LAPIS_GEORE_DOWSING_ROD, "Lapis");
			generateDowsingLang(CompatRegistry.QUARTZ_GEORE_DOWSING_ROD, "Quartz");
			generateDowsingLang(CompatRegistry.REDSTONE_GEORE_DOWSING_ROD, "Redstone");
			generateDowsingLang(CompatRegistry.RUBY_GEORE_DOWSING_ROD, "Ruby");
			generateDowsingLang(CompatRegistry.SAPPHIRE_GEORE_DOWSING_ROD, "Sapphire");
			generateDowsingLang(CompatRegistry.TOPAZ_GEORE_DOWSING_ROD, "Topaz");
			generateDowsingLang(CompatRegistry.ZINC_GEORE_DOWSING_ROD, "Zinc");
		}

		protected void generateCharmLang(RegistryObject<Item> registryObject, String name) {
			addItem(registryObject, name + " GeOre Golem Charm");
			add("tooltip.geore_nouveau." + name.toLowerCase(Locale.ROOT) + "_charm", "Obtained by performing the Ritual of Awakening near Budding " + name + " Geore");
		}

		protected void generateDowsingLang(RegistryObject<Item> registryObject, String name) {
			addItem(registryObject, name + " GeOre Dowsing Rod");
			add("tooltip.geore_nouveau." + name.toLowerCase(Locale.ROOT) + "_dowsing_rod", "Grants Magic Find and Scrying on use, causing magical creatures to glow and " + name + " Geore to be revealed through blocks. Can be used on Imbuement Chamber and Enchanting Apparatus to highlight linked pedestals.");
		}
	}

	private static class ItemModels extends ItemModelProvider {
		public ItemModels(PackOutput packOutput, ExistingFileHelper helper) {
			super(packOutput, GeOreNouveau.MOD_ID, helper);
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
			generateCharm(CompatRegistry.ZINC_GEORE_GOLEM_CHARM);

			generateRod(CompatRegistry.COAL_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.COPPER_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.DIAMOND_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.EMERALD_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.GOLD_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.IRON_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.LAPIS_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.QUARTZ_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.REDSTONE_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.RUBY_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.SAPPHIRE_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.TOPAZ_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.ZINC_GEORE_DOWSING_ROD);
		}

		protected void generateCharm(RegistryObject<Item> registryObject) {
			singleTexture(registryObject.getId().getPath(), new ResourceLocation("item/generated"),
					"layer0", modLoc("item/" + registryObject.getId().getPath()));
		}

		protected void generateRod(RegistryObject<Item> registryObject) {
			withExistingParent(registryObject.getId().getPath(), modLoc("item/dowsing_rod"));
		}
	}

	private static class Loots extends LootTableProvider {
		public Loots(PackOutput packOutput) {
			super(packOutput, Set.of(), List.of(
					new SubProviderEntry(CompatEntityLoot::new, LootContextParamSets.ENTITY)
			));
		}

		public static class CompatEntityLoot extends EntityLootSubProvider {
			protected CompatEntityLoot() {
				super(FeatureFlags.REGISTRY.allFlags());
			}

			@Override
			public void generate() {
				this.add(CompatRegistry.GEORE_GOLEM.get(), LootTable.lootTable());
			}

			@Override
			protected Stream<EntityType<?>> getKnownEntityTypes() {
				return CompatRegistry.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
			}
		}

		@Override
		protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
			map.forEach((name, table) -> table.validate(validationContext));
		}
	}

	private static class Recipes extends RecipeProvider {
		public Recipes(PackOutput packOutput) {
			super(packOutput);
		}

		@Override
		protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
			generateRodRecipe(CompatRegistry.COAL_GEORE_DOWSING_ROD, Items.COAL, recipeConsumer);
			generateRodRecipe(CompatRegistry.COPPER_GEORE_DOWSING_ROD, Items.COPPER_INGOT, recipeConsumer);
			generateRodRecipe(CompatRegistry.DIAMOND_GEORE_DOWSING_ROD, Items.DIAMOND, recipeConsumer);
			generateRodRecipe(CompatRegistry.EMERALD_GEORE_DOWSING_ROD, Items.EMERALD, recipeConsumer);
			generateRodRecipe(CompatRegistry.GOLD_GEORE_DOWSING_ROD, Items.GOLD_INGOT, recipeConsumer);
			generateRodRecipe(CompatRegistry.IRON_GEORE_DOWSING_ROD, Items.IRON_INGOT, recipeConsumer);
			generateRodRecipe(CompatRegistry.LAPIS_GEORE_DOWSING_ROD, Items.LAPIS_LAZULI, recipeConsumer);
			generateRodRecipe(CompatRegistry.QUARTZ_GEORE_DOWSING_ROD, Items.QUARTZ, recipeConsumer);
			generateRodRecipe(CompatRegistry.REDSTONE_GEORE_DOWSING_ROD, Items.REDSTONE, recipeConsumer);

			//Mod compat
			String gemsID = "gemsandcrystals";
			Item rubyItem = getModItem(new ResourceLocation(gemsID, "ruby"));
			if (rubyItem != null) {
				generateOptionalRodRecipe(CompatRegistry.RUBY_GEORE_DOWSING_ROD, rubyItem, gemsID, recipeConsumer);
			}

			Item sapphireItem = getModItem(new ResourceLocation(gemsID, "sapphire"));
			if (sapphireItem != null) {
				generateOptionalRodRecipe(CompatRegistry.SAPPHIRE_GEORE_DOWSING_ROD, sapphireItem, gemsID, recipeConsumer);
			}

			Item topazItem = getModItem(new ResourceLocation(gemsID, "topaz"));
			if (topazItem != null) {
				generateOptionalRodRecipe(CompatRegistry.TOPAZ_GEORE_DOWSING_ROD, topazItem, gemsID, recipeConsumer);
			}
		}

		private void generateRodRecipe(RegistryObject<Item> rod, ItemLike itemLike, Consumer<FinishedRecipe> consumer) {
			ShapedRecipeBuilder.shaped(RecipeCategory.MISC, rod.get())
					.pattern(" O ")
					.pattern("ORO")
					.pattern(" O ")
					.define('R', ItemsRegistry.DOWSING_ROD)
					.define('O', itemLike)
					.unlockedBy("has_dowsing_rod", has(ItemsRegistry.DOWSING_ROD))
					.unlockedBy("has_ore", has(itemLike))
					.save(consumer);
		}

		private Item getModItem(ResourceLocation itemLocation) {
			for (Item item : ForgeRegistries.ITEMS) {
				if (ForgeRegistries.ITEMS.getKey(item).equals(itemLocation)) {
					return item;
				}
			}
			return null;
		}

		private void generateOptionalRodRecipe(RegistryObject<Item> rod, ItemLike itemLike, String modid, Consumer<FinishedRecipe> consumer) {
			ConditionalRecipe.builder()
					.addCondition(new ModLoadedCondition(modid))
					.addRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, rod.get())
							.pattern(" O ")
							.pattern("ORO")
							.pattern(" O ")
							.define('R', ItemsRegistry.DOWSING_ROD)
							.define('O', itemLike)
							.unlockedBy("has_dowsing_rod", has(ItemsRegistry.DOWSING_ROD))
							.unlockedBy("has_ore", has(itemLike))
							::save)
					.build(consumer, new ResourceLocation(GeOreNouveau.MOD_ID, rod.getId().getPath()));
		}
	}
}
