package com.shynieke.georenouveau.entity;

import com.hollingsworth.arsnouveau.setup.ItemsRegistry;
import com.shynieke.geore.registry.GeOreRegistry;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum LinkedGeOre {
	DEFAULT("default", () -> Blocks.AMETHYST_BLOCK, () -> Blocks.BUDDING_AMETHYST,
			() -> Blocks.AMETHYST_CLUSTER, () -> Items.AMETHYST_SHARD, () -> ItemsRegistry.AMETHYST_GOLEM_CHARM.get()),
	COAL("coal", () -> GeOreRegistry.COAL_GEORE.getBlock().get(), () -> GeOreRegistry.COAL_GEORE.getBudding().get(),
			() -> GeOreRegistry.COAL_GEORE.getCluster().get(), () -> GeOreRegistry.COAL_GEORE.getShard().get(), () -> CompatRegistry.COAL_GEORE_GOLEM_CHARM.get()),
	COPPER("copper", () -> GeOreRegistry.COPPER_GEORE.getBlock().get(), () -> GeOreRegistry.COPPER_GEORE.getBudding().get(),
			() -> GeOreRegistry.COPPER_GEORE.getCluster().get(), () -> GeOreRegistry.COPPER_GEORE.getShard().get(), () -> CompatRegistry.COPPER_GEORE_GOLEM_CHARM.get()),
	DIAMOND("diamond", () -> GeOreRegistry.DIAMOND_GEORE.getBlock().get(), () -> GeOreRegistry.DIAMOND_GEORE.getBudding().get(),
			() -> GeOreRegistry.DIAMOND_GEORE.getCluster().get(), () -> GeOreRegistry.DIAMOND_GEORE.getShard().get(), () -> CompatRegistry.DIAMOND_GEORE_GOLEM_CHARM.get()),
	EMERALD("emerald", () -> GeOreRegistry.EMERALD_GEORE.getBlock().get(), () -> GeOreRegistry.EMERALD_GEORE.getBudding().get(),
			() -> GeOreRegistry.EMERALD_GEORE.getCluster().get(), () -> GeOreRegistry.EMERALD_GEORE.getShard().get(), () -> CompatRegistry.EMERALD_GEORE_GOLEM_CHARM.get()),
	GOLD("gold", () -> GeOreRegistry.GOLD_GEORE.getBlock().get(), () -> GeOreRegistry.GOLD_GEORE.getBudding().get(),
			() -> GeOreRegistry.GOLD_GEORE.getCluster().get(), () -> GeOreRegistry.GOLD_GEORE.getShard().get(), () -> CompatRegistry.GOLD_GEORE_GOLEM_CHARM.get()),
	IRON("iron", () -> GeOreRegistry.IRON_GEORE.getBlock().get(), () -> GeOreRegistry.IRON_GEORE.getBudding().get(),
			() -> GeOreRegistry.IRON_GEORE.getCluster().get(), () -> GeOreRegistry.IRON_GEORE.getShard().get(), () -> CompatRegistry.IRON_GEORE_GOLEM_CHARM.get()),
	LAPIS("lapis", () -> GeOreRegistry.LAPIS_GEORE.getBlock().get(), () -> GeOreRegistry.LAPIS_GEORE.getBudding().get(),
			() -> GeOreRegistry.LAPIS_GEORE.getCluster().get(), () -> GeOreRegistry.LAPIS_GEORE.getShard().get(), () -> CompatRegistry.LAPIS_GEORE_GOLEM_CHARM.get()),
	QUARTZ("quartz", () -> GeOreRegistry.QUARTZ_GEORE.getBlock().get(), () -> GeOreRegistry.QUARTZ_GEORE.getBudding().get(),
			() -> GeOreRegistry.QUARTZ_GEORE.getCluster().get(), () -> GeOreRegistry.QUARTZ_GEORE.getShard().get(), () -> CompatRegistry.QUARTZ_GEORE_GOLEM_CHARM.get()),
	REDSTONE("redstone", () -> GeOreRegistry.REDSTONE_GEORE.getBlock().get(), () -> GeOreRegistry.REDSTONE_GEORE.getBudding().get(),
			() -> GeOreRegistry.REDSTONE_GEORE.getCluster().get(), () -> GeOreRegistry.REDSTONE_GEORE.getShard().get(), () -> CompatRegistry.REDSTONE_GEORE_GOLEM_CHARM.get()),
	RUBY("ruby", () -> GeOreRegistry.RUBY_GEORE.getBlock().get(), () -> GeOreRegistry.RUBY_GEORE.getBudding().get(),
			() -> GeOreRegistry.RUBY_GEORE.getCluster().get(), () -> GeOreRegistry.RUBY_GEORE.getShard().get(), () -> CompatRegistry.RUBY_GEORE_GOLEM_CHARM.get()),
	SAPPHIRE("sapphire", () -> GeOreRegistry.SAPPHIRE_GEORE.getBlock().get(), () -> GeOreRegistry.SAPPHIRE_GEORE.getBudding().get(),
			() -> GeOreRegistry.SAPPHIRE_GEORE.getCluster().get(), () -> GeOreRegistry.SAPPHIRE_GEORE.getShard().get(), () -> CompatRegistry.SAPPHIRE_GEORE_GOLEM_CHARM.get()),
	TOPAZ("topaz", () -> GeOreRegistry.TOPAZ_GEORE.getBlock().get(), () -> GeOreRegistry.TOPAZ_GEORE.getBudding().get(),
			() -> GeOreRegistry.TOPAZ_GEORE.getCluster().get(), () -> GeOreRegistry.TOPAZ_GEORE.getShard().get(), () -> CompatRegistry.TOPAZ_GEORE_GOLEM_CHARM.get());

	public final String name;
	public final LazyLoadedValue<Block> georeBlock;
	public final LazyLoadedValue<Block> buddingBlock;
	public final LazyLoadedValue<Block> clusterBlock;
	public final LazyLoadedValue<Item> shardItem;
	public final LazyLoadedValue<Item> charmItem;

	LinkedGeOre(String name, Supplier<Block> blockSupplier, Supplier<Block> buddingSupplier, Supplier<Block> clusterSupplier,
				Supplier<Item> shardSupplier, Supplier<Item> charmSupplier) {
		this.name = name;
		this.georeBlock = new LazyLoadedValue<>(blockSupplier);
		this.buddingBlock = new LazyLoadedValue<>(buddingSupplier);
		this.clusterBlock = new LazyLoadedValue<>(clusterSupplier);
		this.shardItem = new LazyLoadedValue<>(shardSupplier);
		this.charmItem = new LazyLoadedValue<>(charmSupplier);
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

	@Nullable
	public static LinkedGeOre getByName(@Nullable String value) {
		for (LinkedGeOre linkedGeOre : values()) {
			if (linkedGeOre.name.equals(value)) {
				return linkedGeOre;
			}
		}
		return DEFAULT;
	}
}
