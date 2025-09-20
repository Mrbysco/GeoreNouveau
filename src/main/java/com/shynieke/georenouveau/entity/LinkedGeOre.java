package com.shynieke.georenouveau.entity;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.shynieke.geore.registry.GeOreBlockReg;
import com.shynieke.geore.registry.GeOreRegistry;
import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.registry.CompatRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;
import java.util.function.IntFunction;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public enum LinkedGeOre implements StringRepresentable {
	DEFAULT(0, "default", () -> Blocks.AMETHYST_BLOCK, () -> Blocks.BUDDING_AMETHYST,
			() -> Blocks.AMETHYST_CLUSTER, () -> Items.AMETHYST_SHARD, ItemsRegistry.AMETHYST_GOLEM_CHARM::get),
	COAL(1, "coal", () -> GeOreRegistry.COAL_GEORE.getBlock().get(), () -> GeOreRegistry.COAL_GEORE.getBudding().get(),
			() -> GeOreRegistry.COAL_GEORE.getCluster().get(), () -> GeOreRegistry.COAL_GEORE.getShard().get(), CompatRegistry.COAL_GEORE_GOLEM_CHARM::get),
	COPPER(2, "copper", () -> GeOreRegistry.COPPER_GEORE.getBlock().get(), () -> GeOreRegistry.COPPER_GEORE.getBudding().get(),
			() -> GeOreRegistry.COPPER_GEORE.getCluster().get(), () -> GeOreRegistry.COPPER_GEORE.getShard().get(), CompatRegistry.COPPER_GEORE_GOLEM_CHARM::get),
	DIAMOND(3, "diamond", () -> GeOreRegistry.DIAMOND_GEORE.getBlock().get(), () -> GeOreRegistry.DIAMOND_GEORE.getBudding().get(),
			() -> GeOreRegistry.DIAMOND_GEORE.getCluster().get(), () -> GeOreRegistry.DIAMOND_GEORE.getShard().get(), CompatRegistry.DIAMOND_GEORE_GOLEM_CHARM::get),
	EMERALD(4, "emerald", () -> GeOreRegistry.EMERALD_GEORE.getBlock().get(), () -> GeOreRegistry.EMERALD_GEORE.getBudding().get(),
			() -> GeOreRegistry.EMERALD_GEORE.getCluster().get(), () -> GeOreRegistry.EMERALD_GEORE.getShard().get(), CompatRegistry.EMERALD_GEORE_GOLEM_CHARM::get),
	GOLD(5, "gold", () -> GeOreRegistry.GOLD_GEORE.getBlock().get(), () -> GeOreRegistry.GOLD_GEORE.getBudding().get(),
			() -> GeOreRegistry.GOLD_GEORE.getCluster().get(), () -> GeOreRegistry.GOLD_GEORE.getShard().get(), CompatRegistry.GOLD_GEORE_GOLEM_CHARM::get),
	IRON(6, "iron", () -> GeOreRegistry.IRON_GEORE.getBlock().get(), () -> GeOreRegistry.IRON_GEORE.getBudding().get(),
			() -> GeOreRegistry.IRON_GEORE.getCluster().get(), () -> GeOreRegistry.IRON_GEORE.getShard().get(), CompatRegistry.IRON_GEORE_GOLEM_CHARM::get),
	LAPIS(7, "lapis", () -> GeOreRegistry.LAPIS_GEORE.getBlock().get(), () -> GeOreRegistry.LAPIS_GEORE.getBudding().get(),
			() -> GeOreRegistry.LAPIS_GEORE.getCluster().get(), () -> GeOreRegistry.LAPIS_GEORE.getShard().get(), CompatRegistry.LAPIS_GEORE_GOLEM_CHARM::get),
	QUARTZ(8, "quartz", () -> GeOreRegistry.QUARTZ_GEORE.getBlock().get(), () -> GeOreRegistry.QUARTZ_GEORE.getBudding().get(),
			() -> GeOreRegistry.QUARTZ_GEORE.getCluster().get(), () -> GeOreRegistry.QUARTZ_GEORE.getShard().get(), CompatRegistry.QUARTZ_GEORE_GOLEM_CHARM::get),
	REDSTONE(9, "redstone", () -> GeOreRegistry.REDSTONE_GEORE.getBlock().get(), () -> GeOreRegistry.REDSTONE_GEORE.getBudding().get(),
			() -> GeOreRegistry.REDSTONE_GEORE.getCluster().get(), () -> GeOreRegistry.REDSTONE_GEORE.getShard().get(), CompatRegistry.REDSTONE_GEORE_GOLEM_CHARM::get),
	RUBY(10, "ruby", () -> GeOreRegistry.RUBY_GEORE.getBlock().get(), () -> GeOreRegistry.RUBY_GEORE.getBudding().get(),
			() -> GeOreRegistry.RUBY_GEORE.getCluster().get(), () -> GeOreRegistry.RUBY_GEORE.getShard().get(), CompatRegistry.RUBY_GEORE_GOLEM_CHARM::get),
	SAPPHIRE(11, "sapphire", () -> GeOreRegistry.SAPPHIRE_GEORE.getBlock().get(), () -> GeOreRegistry.SAPPHIRE_GEORE.getBudding().get(),
			() -> GeOreRegistry.SAPPHIRE_GEORE.getCluster().get(), () -> GeOreRegistry.SAPPHIRE_GEORE.getShard().get(), CompatRegistry.SAPPHIRE_GEORE_GOLEM_CHARM::get),
	TOPAZ(12, "topaz", () -> GeOreRegistry.TOPAZ_GEORE.getBlock().get(), () -> GeOreRegistry.TOPAZ_GEORE.getBudding().get(),
			() -> GeOreRegistry.TOPAZ_GEORE.getCluster().get(), () -> GeOreRegistry.TOPAZ_GEORE.getShard().get(), CompatRegistry.TOPAZ_GEORE_GOLEM_CHARM::get),
	ZINC(13, "zinc", () -> GeOreRegistry.ZINC_GEORE.getBlock().get(), () -> GeOreRegistry.ZINC_GEORE.getBudding().get(),
			() -> GeOreRegistry.ZINC_GEORE.getCluster().get(), () -> GeOreRegistry.ZINC_GEORE.getShard().get(), CompatRegistry.ZINC_GEORE_GOLEM_CHARM::get),
	ANCIENT_DEBRIS(14, "ancient_debris", () -> Blocks.NETHERITE_BLOCK, () -> GeOreRegistry.ANCIENT_DEBRIS_GEORE.getBudding().get(),
			() -> GeOreRegistry.ANCIENT_DEBRIS_GEORE.getCluster().get(), () -> GeOreRegistry.ANCIENT_DEBRIS_GEORE.getShard().get(), CompatRegistry.ANCIENT_DEBRIS_GEORE_GOLEM_CHARM::get),
	URANINITE(15, "uraninite", () -> GeOreRegistry.URANINITE_GEORE.getBlock().get(), () -> GeOreRegistry.URANINITE_GEORE.getBudding().get(),
			() -> GeOreRegistry.URANINITE_GEORE.getCluster().get(), () -> GeOreRegistry.URANINITE_GEORE.getShard().get(), CompatRegistry.URANINITE_GEORE_GOLEM_CHARM::get),
	BLACK_QUARTZ(16, "black_quartz", () -> GeOreRegistry.BLACK_QUARTZ_GEORE.getBlock().get(), () -> GeOreRegistry.BLACK_QUARTZ_GEORE.getBudding().get(),
			() -> GeOreRegistry.BLACK_QUARTZ_GEORE.getCluster().get(), () -> GeOreRegistry.BLACK_QUARTZ_GEORE.getShard().get(), CompatRegistry.BLACK_QUARTZ_GEORE_GOLEM_CHARM::get),
	MONAZITE(17, "monazite", () -> GeOreRegistry.MONAZITE_GEORE.getBlock().get(), () -> GeOreRegistry.MONAZITE_GEORE.getBudding().get(),
			() -> GeOreRegistry.MONAZITE_GEORE.getCluster().get(), () -> GeOreRegistry.MONAZITE_GEORE.getShard().get(), CompatRegistry.MONAZITE_GEORE_GOLEM_CHARM::get),
	ALUMINUM(18, "aluminum", () -> GeOreRegistry.ALUMINUM_GEORE.getBlock().get(), () -> GeOreRegistry.ALUMINUM_GEORE.getBudding().get(),
			() -> GeOreRegistry.ALUMINUM_GEORE.getCluster().get(), () -> GeOreRegistry.ALUMINUM_GEORE.getShard().get(), CompatRegistry.ALUMINUM_GEORE_GOLEM_CHARM::get),
	LEAD(19, "lead", () -> GeOreRegistry.LEAD_GEORE.getBlock().get(), () -> GeOreRegistry.LEAD_GEORE.getBudding().get(),
			() -> GeOreRegistry.LEAD_GEORE.getCluster().get(), () -> GeOreRegistry.LEAD_GEORE.getShard().get(), CompatRegistry.LEAD_GEORE_GOLEM_CHARM::get),
	NICKEL(20, "nickel", () -> GeOreRegistry.NICKEL_GEORE.getBlock().get(), () -> GeOreRegistry.NICKEL_GEORE.getBudding().get(),
			() -> GeOreRegistry.NICKEL_GEORE.getCluster().get(), () -> GeOreRegistry.NICKEL_GEORE.getShard().get(), CompatRegistry.NICKEL_GEORE_GOLEM_CHARM::get),
	OSMIUM(21, "osmium", () -> GeOreRegistry.OSMIUM_GEORE.getBlock().get(), () -> GeOreRegistry.OSMIUM_GEORE.getBudding().get(),
			() -> GeOreRegistry.OSMIUM_GEORE.getCluster().get(), () -> GeOreRegistry.OSMIUM_GEORE.getShard().get(), CompatRegistry.OSMIUM_GEORE_GOLEM_CHARM::get),
	PLATINUM(22, "platinum", () -> GeOreRegistry.PLATINUM_GEORE.getBlock().get(), () -> GeOreRegistry.PLATINUM_GEORE.getBudding().get(),
			() -> GeOreRegistry.PLATINUM_GEORE.getCluster().get(), () -> GeOreRegistry.PLATINUM_GEORE.getShard().get(), CompatRegistry.PLATINUM_GEORE_GOLEM_CHARM::get),
	SILVER(23, "silver", () -> GeOreRegistry.SILVER_GEORE.getBlock().get(), () -> GeOreRegistry.SILVER_GEORE.getBudding().get(),
			() -> GeOreRegistry.SILVER_GEORE.getCluster().get(), () -> GeOreRegistry.SILVER_GEORE.getShard().get(), CompatRegistry.SILVER_GEORE_GOLEM_CHARM::get),
	TIN(24, "tin", () -> GeOreRegistry.TIN_GEORE.getBlock().get(), () -> GeOreRegistry.TIN_GEORE.getBudding().get(),
			() -> GeOreRegistry.TIN_GEORE.getCluster().get(), () -> GeOreRegistry.TIN_GEORE.getShard().get(), CompatRegistry.TIN_GEORE_GOLEM_CHARM::get),
	TUNGSTEN(25, "tungsten", () -> GeOreRegistry.TUNGSTEN_GEORE.getBlock().get(), () -> GeOreRegistry.TUNGSTEN_GEORE.getBudding().get(),
			() -> GeOreRegistry.TUNGSTEN_GEORE.getCluster().get(), () -> GeOreRegistry.TUNGSTEN_GEORE.getShard().get(), CompatRegistry.TUNGSTEN_GEORE_GOLEM_CHARM::get),
	URANIUM(26, "uranium", () -> GeOreRegistry.URANIUM_GEORE.getBlock().get(), () -> GeOreRegistry.URANIUM_GEORE.getBudding().get(),
			() -> GeOreRegistry.URANIUM_GEORE.getCluster().get(), () -> GeOreRegistry.URANIUM_GEORE.getShard().get(), CompatRegistry.URANIUM_GEORE_GOLEM_CHARM::get),
	ALLTHEMODIUM(27, "allthemodium", getOptionalBlock(ResourceLocation.fromNamespaceAndPath("allthemodium", "allthemodium_block"), GeOreRegistry.ALLTHEMODIUM_GEORE), () -> GeOreRegistry.ALLTHEMODIUM_GEORE.getBudding().get(),
			() -> GeOreRegistry.ALLTHEMODIUM_GEORE.getCluster().get(), () -> GeOreRegistry.ALLTHEMODIUM_GEORE.getShard().get(), CompatRegistry.ALLTHEMODIUM_GEORE_GOLEM_CHARM::get),
	VIBRANIUM(28, "vibranium", getOptionalBlock(ResourceLocation.fromNamespaceAndPath("allthemodium", "vibranium_block"), GeOreRegistry.VIBRANIUM_GEORE), () -> GeOreRegistry.VIBRANIUM_GEORE.getBudding().get(),
			() -> GeOreRegistry.VIBRANIUM_GEORE.getCluster().get(), () -> GeOreRegistry.VIBRANIUM_GEORE.getShard().get(), CompatRegistry.VIBRANIUM_GEORE_GOLEM_CHARM::get),
	UNOBTAINIUM(29, "unobtainium", getOptionalBlock(ResourceLocation.fromNamespaceAndPath("allthemodium", "unobtainium_block"), GeOreRegistry.UNOBTAINIUM_GEORE), () -> GeOreRegistry.UNOBTAINIUM_GEORE.getBudding().get(),
			() -> GeOreRegistry.UNOBTAINIUM_GEORE.getCluster().get(), () -> GeOreRegistry.UNOBTAINIUM_GEORE.getShard().get(), CompatRegistry.UNOBTAINIUM_GEORE_GOLEM_CHARM::get);

	private static final IntFunction<LinkedGeOre> BY_ID = ByIdMap.continuous(LinkedGeOre::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
	public static final StreamCodec<ByteBuf, LinkedGeOre> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, LinkedGeOre::getId);
	private final ResourceLocation defaultTexture = ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "textures/entity/amethyst_golem.png");
	public final int id;
	public final String name;
	public final LazyLoadedValue<Block> georeBlock;
	public final LazyLoadedValue<Block> buddingBlock;
	public final LazyLoadedValue<Block> clusterBlock;
	public final LazyLoadedValue<Item> shardItem;
	public final LazyLoadedValue<Item> charmItem;
	public final ResourceLocation textureLocation;

	LinkedGeOre(int id, String name, Supplier<Block> blockSupplier, Supplier<Block> buddingSupplier, Supplier<Block> clusterSupplier,
	            Supplier<Item> shardSupplier, Supplier<Item> charmSupplier) {
		this.id = id;
		this.name = name;
		this.georeBlock = new LazyLoadedValue<>(blockSupplier);
		this.buddingBlock = new LazyLoadedValue<>(buddingSupplier);
		this.clusterBlock = new LazyLoadedValue<>(clusterSupplier);
		this.shardItem = new LazyLoadedValue<>(shardSupplier);
		this.charmItem = new LazyLoadedValue<>(charmSupplier);
		this.textureLocation = ResourceLocation.fromNamespaceAndPath(GeOreNouveau.MOD_ID, "textures/entity/" + name + "_golem.png");
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public Block getBlock() {
		return georeBlock.get();
	}

	public Block getBudding() {
		return buddingBlock.get();
	}

	public Block getCluster() {
		return clusterBlock.get();
	}

	public Item getShard() {
		return shardItem.get();
	}

	public Item getCharm() {
		return charmItem.get();
	}

	public ResourceLocation getTextureLocation() {
		if (this == DEFAULT)
			return defaultTexture;
		return textureLocation;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}

	private static Supplier<Block> getOptionalBlock(ResourceLocation modId, GeOreBlockReg fallback) {
		Optional<Block> optionalBlock = BuiltInRegistries.BLOCK.getOptional(modId);
		if (optionalBlock.isPresent()) {
			return optionalBlock::get;
		}
		return () -> fallback.getBlock().get();
	}
}
