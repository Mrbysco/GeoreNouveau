package com.shynieke.georenouveau.data;

import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.item.GeOreDowsingRod;
import com.shynieke.georenouveau.item.GeOreGolemCharm;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
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
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
			generateCharmLang(CompatRegistry.ANCIENT_DEBRIS_GEORE_GOLEM_CHARM, "Ancient Debris");
			generateCharmLang(CompatRegistry.RUBY_GEORE_GOLEM_CHARM, "Ruby");
			generateCharmLang(CompatRegistry.SAPPHIRE_GEORE_GOLEM_CHARM, "Sapphire");
			generateCharmLang(CompatRegistry.TOPAZ_GEORE_GOLEM_CHARM, "Topaz");
			generateCharmLang(CompatRegistry.ZINC_GEORE_GOLEM_CHARM, "Zinc");
			generateCharmLang(CompatRegistry.URANINITE_GEORE_GOLEM_CHARM, "Uraninite");
			generateCharmLang(CompatRegistry.BLACK_QUARTZ_GEORE_GOLEM_CHARM, "Black Quartz");
			generateCharmLang(CompatRegistry.MONAZITE_GEORE_GOLEM_CHARM, "Monazite");
			generateCharmLang(CompatRegistry.ALUMINUM_GEORE_GOLEM_CHARM, "Aluminum");
			generateCharmLang(CompatRegistry.LEAD_GEORE_GOLEM_CHARM, "Lead");
			generateCharmLang(CompatRegistry.NICKEL_GEORE_GOLEM_CHARM, "Nickel");
			generateCharmLang(CompatRegistry.OSMIUM_GEORE_GOLEM_CHARM, "Osmium");
			generateCharmLang(CompatRegistry.PLATINUM_GEORE_GOLEM_CHARM, "Platinum");
			generateCharmLang(CompatRegistry.SILVER_GEORE_GOLEM_CHARM, "Silver");
			generateCharmLang(CompatRegistry.TIN_GEORE_GOLEM_CHARM, "Tin");
			generateCharmLang(CompatRegistry.TUNGSTEN_GEORE_GOLEM_CHARM, "Tungsten");
			generateCharmLang(CompatRegistry.URANIUM_GEORE_GOLEM_CHARM, "Uranium");
			generateCharmLang(CompatRegistry.ALLTHEMODIUM_GEORE_GOLEM_CHARM, "Allthemodium");
			generateCharmLang(CompatRegistry.VIBRANIUM_GEORE_GOLEM_CHARM, "Vibranium");
			generateCharmLang(CompatRegistry.UNOBTAINIUM_GEORE_GOLEM_CHARM, "Unobtainium");

			generateDowsingLang(CompatRegistry.COAL_GEORE_DOWSING_ROD, "Coal");
			generateDowsingLang(CompatRegistry.COPPER_GEORE_DOWSING_ROD, "Copper");
			generateDowsingLang(CompatRegistry.DIAMOND_GEORE_DOWSING_ROD, "Diamond");
			generateDowsingLang(CompatRegistry.EMERALD_GEORE_DOWSING_ROD, "Emerald");
			generateDowsingLang(CompatRegistry.GOLD_GEORE_DOWSING_ROD, "Gold");
			generateDowsingLang(CompatRegistry.IRON_GEORE_DOWSING_ROD, "Iron");
			generateDowsingLang(CompatRegistry.LAPIS_GEORE_DOWSING_ROD, "Lapis");
			generateDowsingLang(CompatRegistry.QUARTZ_GEORE_DOWSING_ROD, "Quartz");
			generateDowsingLang(CompatRegistry.REDSTONE_GEORE_DOWSING_ROD, "Redstone");
			generateDowsingLang(CompatRegistry.ANCIENT_DEBRIS_GEORE_DOWSING_ROD, "Ancient Debris");
			generateDowsingLang(CompatRegistry.RUBY_GEORE_DOWSING_ROD, "Ruby");
			generateDowsingLang(CompatRegistry.SAPPHIRE_GEORE_DOWSING_ROD, "Sapphire");
			generateDowsingLang(CompatRegistry.TOPAZ_GEORE_DOWSING_ROD, "Topaz");
			generateDowsingLang(CompatRegistry.ZINC_GEORE_DOWSING_ROD, "Zinc");
			generateDowsingLang(CompatRegistry.URANINITE_GEORE_DOWSING_ROD, "Uraninite");
			generateDowsingLang(CompatRegistry.BLACK_QUARTZ_GEORE_DOWSING_ROD, "Black Quartz");
			generateDowsingLang(CompatRegistry.MONAZITE_GEORE_DOWSING_ROD, "Monazite");
			generateDowsingLang(CompatRegistry.ALUMINUM_GEORE_DOWSING_ROD, "Aluminum");
			generateDowsingLang(CompatRegistry.LEAD_GEORE_DOWSING_ROD, "Lead");
			generateDowsingLang(CompatRegistry.NICKEL_GEORE_DOWSING_ROD, "Nickel");
			generateDowsingLang(CompatRegistry.OSMIUM_GEORE_DOWSING_ROD, "Osmium");
			generateDowsingLang(CompatRegistry.PLATINUM_GEORE_DOWSING_ROD, "Platinum");
			generateDowsingLang(CompatRegistry.SILVER_GEORE_DOWSING_ROD, "Silver");
			generateDowsingLang(CompatRegistry.TIN_GEORE_DOWSING_ROD, "Tin");
			generateDowsingLang(CompatRegistry.TUNGSTEN_GEORE_DOWSING_ROD, "Tungsten");
			generateDowsingLang(CompatRegistry.URANIUM_GEORE_DOWSING_ROD, "Uranium");
			generateDowsingLang(CompatRegistry.ALLTHEMODIUM_GEORE_DOWSING_ROD, "Allthemodium");
			generateDowsingLang(CompatRegistry.VIBRANIUM_GEORE_DOWSING_ROD, "Vibranium");
			generateDowsingLang(CompatRegistry.UNOBTAINIUM_GEORE_DOWSING_ROD, "Unobtainium");
		}

		protected void generateCharmLang(RegistryObject<GeOreGolemCharm> registryObject, String name) {
			addItem(registryObject, name + " GeOre Golem Charm");
			add("tooltip.geore_nouveau." + name.replace(" ", "_").toLowerCase(Locale.ROOT) + "_charm", "Obtained by performing the Ritual of Awakening near Budding " + name + " GeOre");
		}

		protected void generateDowsingLang(RegistryObject<GeOreDowsingRod> registryObject, String name) {
			addItem(registryObject, name + " GeOre Dowsing Rod");
			add("tooltip.geore_nouveau." + name.replace(" ", "_").toLowerCase(Locale.ROOT) + "_dowsing_rod", "Grants Magic Find and Scrying on use, causing magical creatures to glow and " + name + " GeOre to be revealed through blocks. Can be used on Imbuement Chamber and Enchanting Apparatus to highlight linked pedestals.");
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
			generateCharm(CompatRegistry.ANCIENT_DEBRIS_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.RUBY_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.SAPPHIRE_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.TOPAZ_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.ZINC_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.URANINITE_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.BLACK_QUARTZ_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.MONAZITE_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.ALUMINUM_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.LEAD_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.NICKEL_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.OSMIUM_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.PLATINUM_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.SILVER_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.TIN_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.TUNGSTEN_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.URANIUM_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.ALLTHEMODIUM_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.VIBRANIUM_GEORE_GOLEM_CHARM);
			generateCharm(CompatRegistry.UNOBTAINIUM_GEORE_GOLEM_CHARM);

			generateRod(CompatRegistry.COAL_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.COPPER_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.DIAMOND_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.EMERALD_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.GOLD_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.IRON_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.LAPIS_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.QUARTZ_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.REDSTONE_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.ANCIENT_DEBRIS_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.RUBY_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.SAPPHIRE_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.TOPAZ_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.ZINC_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.URANINITE_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.BLACK_QUARTZ_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.MONAZITE_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.ALUMINUM_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.LEAD_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.NICKEL_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.OSMIUM_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.PLATINUM_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.SILVER_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.TIN_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.TUNGSTEN_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.URANIUM_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.ALLTHEMODIUM_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.VIBRANIUM_GEORE_DOWSING_ROD);
			generateRod(CompatRegistry.UNOBTAINIUM_GEORE_DOWSING_ROD);
		}

		protected void generateCharm(RegistryObject<GeOreGolemCharm> registryObject) {
			String path = registryObject.getId().getPath();
			singleTexture(path, mcLoc("item/generated"),
					"layer0", modLoc("item/" + path.replace("_geore_golem_", "_golem_")));
		}

		protected void generateRod(RegistryObject<GeOreDowsingRod> registryObject) {
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
		protected void buildRecipes(Consumer<FinishedRecipe> output) {
			generateRodRecipe(CompatRegistry.COAL_GEORE_DOWSING_ROD, Items.COAL, output);
			generateRodRecipe(CompatRegistry.COPPER_GEORE_DOWSING_ROD, Items.COPPER_INGOT, output);
			generateRodRecipe(CompatRegistry.DIAMOND_GEORE_DOWSING_ROD, Items.DIAMOND, output);
			generateRodRecipe(CompatRegistry.EMERALD_GEORE_DOWSING_ROD, Items.EMERALD, output);
			generateRodRecipe(CompatRegistry.GOLD_GEORE_DOWSING_ROD, Items.GOLD_INGOT, output);
			generateRodRecipe(CompatRegistry.IRON_GEORE_DOWSING_ROD, Items.IRON_INGOT, output);
			generateRodRecipe(CompatRegistry.LAPIS_GEORE_DOWSING_ROD, Items.LAPIS_LAZULI, output);
			generateRodRecipe(CompatRegistry.QUARTZ_GEORE_DOWSING_ROD, Items.QUARTZ, output);
			generateRodRecipe(CompatRegistry.REDSTONE_GEORE_DOWSING_ROD, Items.REDSTONE, output);
			generateRodRecipe(CompatRegistry.ANCIENT_DEBRIS_GEORE_DOWSING_ROD, Items.ANCIENT_DEBRIS, output);

			generateRodRecipe(CompatRegistry.RUBY_GEORE_DOWSING_ROD, createTag("gems/ruby"), output);
			generateRodRecipe(CompatRegistry.SAPPHIRE_GEORE_DOWSING_ROD, createTag("gems/sapphire"), output);
			generateRodRecipe(CompatRegistry.TOPAZ_GEORE_DOWSING_ROD, createTag("gems/topaz"), output);
			generateRodRecipe(CompatRegistry.ZINC_GEORE_DOWSING_ROD, createTag("ingots/zinc"), output);
			generateRodRecipe(CompatRegistry.URANINITE_GEORE_DOWSING_ROD, createTag("raw_materials/uraninite"), output);
			generateRodRecipe(CompatRegistry.BLACK_QUARTZ_GEORE_DOWSING_ROD, createTag("gems/black_quartz"), output);
			generateRodRecipe(CompatRegistry.MONAZITE_GEORE_DOWSING_ROD, createTag("dusts/monazite"), output);
			generateRodRecipe(CompatRegistry.ALUMINUM_GEORE_DOWSING_ROD, createTag("ingots/aluminum"), output);
			generateRodRecipe(CompatRegistry.LEAD_GEORE_DOWSING_ROD, createTag("ingots/lead"), output);
			generateRodRecipe(CompatRegistry.NICKEL_GEORE_DOWSING_ROD, createTag("ingots/nickel"), output);
			generateRodRecipe(CompatRegistry.OSMIUM_GEORE_DOWSING_ROD, createTag("ingots/osmium"), output);
			generateRodRecipe(CompatRegistry.PLATINUM_GEORE_DOWSING_ROD, createTag("ingots/platinum"), output);
			generateRodRecipe(CompatRegistry.SILVER_GEORE_DOWSING_ROD, createTag("ingots/silver"), output);
			generateRodRecipe(CompatRegistry.TIN_GEORE_DOWSING_ROD, createTag("ingots/tin"), output);
			generateRodRecipe(CompatRegistry.TUNGSTEN_GEORE_DOWSING_ROD, createTag("ingots/tungsten"), output);
			generateRodRecipe(CompatRegistry.URANIUM_GEORE_DOWSING_ROD, createTag("ingots/uranium"), output);
			generateRodRecipe(CompatRegistry.ALLTHEMODIUM_GEORE_DOWSING_ROD, createTag("ingots/allthemodium"), output);
			generateRodRecipe(CompatRegistry.VIBRANIUM_GEORE_DOWSING_ROD, createTag("ingots/vibranium"), output);
			generateRodRecipe(CompatRegistry.UNOBTAINIUM_GEORE_DOWSING_ROD, createTag("ingots/unobtainium"), output);
		}

		private TagKey<Item> createTag(String tagName) {
			return TagKey.create(BuiltInRegistries.ITEM.key(), new ResourceLocation("forge", tagName));
		}

		private void generateRodRecipe(RegistryObject<GeOreDowsingRod> rod, ItemLike itemLike, Consumer<FinishedRecipe> output) {
			ShapedRecipeBuilder.shaped(RecipeCategory.MISC, rod.get())
					.pattern(" O ")
					.pattern("ORO")
					.pattern(" O ")
					.define('R', ItemsRegistry.DOWSING_ROD)
					.define('O', itemLike)
					.unlockedBy("has_dowsing_rod", has(ItemsRegistry.DOWSING_ROD))
					.unlockedBy("has_ore", has(itemLike))
					.save(output);
		}

		private void generateRodRecipe(RegistryObject<GeOreDowsingRod> rod, TagKey<Item> itemTag, Consumer<FinishedRecipe> output) {
			ConditionalRecipe.builder()
					.addCondition(new NotCondition(new TagEmptyCondition(itemTag.location())))
					.addRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, rod.get())
							.pattern(" O ")
							.pattern("ORO")
							.pattern(" O ")
							.define('R', ItemsRegistry.DOWSING_ROD)
							.define('O', itemTag)
							.unlockedBy("has_dowsing_rod", has(ItemsRegistry.DOWSING_ROD))
							.unlockedBy("has_ore", has(itemTag))
							::save)
					.build(output, new ResourceLocation(GeOreNouveau.MOD_ID, rod.getId().getPath()));
		}
	}
}
