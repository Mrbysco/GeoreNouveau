package com.shynieke.georenouveau.entity;

import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.shynieke.geore.registry.GeOreRegistry;
import com.shynieke.georenouveau.registry.CompatRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.IntFunction;
import java.util.function.Supplier;

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
			() -> GeOreRegistry.ZINC_GEORE.getCluster().get(), () -> GeOreRegistry.ZINC_GEORE.getShard().get(), CompatRegistry.ZINC_GEORE_GOLEM_CHARM::get);

	private static final IntFunction<LinkedGeOre> BY_ID = ByIdMap.continuous(LinkedGeOre::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
	public static final StringRepresentable.EnumCodec<LinkedGeOre> CODEC = StringRepresentable.fromEnum(LinkedGeOre::values);
	public static final StreamCodec<ByteBuf, LinkedGeOre> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, LinkedGeOre::getId);

	public final int id;
	public final String name;
	public final LazyLoadedValue<Block> georeBlock;
	public final LazyLoadedValue<Block> buddingBlock;
	public final LazyLoadedValue<Block> clusterBlock;
	public final LazyLoadedValue<Item> shardItem;
	public final LazyLoadedValue<Item> charmItem;

	LinkedGeOre(int id, String name, Supplier<Block> blockSupplier, Supplier<Block> buddingSupplier, Supplier<Block> clusterSupplier,
	            Supplier<Item> shardSupplier, Supplier<Item> charmSupplier) {
		this.id = id;
		this.name = name;
		this.georeBlock = new LazyLoadedValue<>(blockSupplier);
		this.buddingBlock = new LazyLoadedValue<>(buddingSupplier);
		this.clusterBlock = new LazyLoadedValue<>(clusterSupplier);
		this.shardItem = new LazyLoadedValue<>(shardSupplier);
		this.charmItem = new LazyLoadedValue<>(charmSupplier);
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

	@Override
	public String getSerializedName() {
		return this.name;
	}
}
