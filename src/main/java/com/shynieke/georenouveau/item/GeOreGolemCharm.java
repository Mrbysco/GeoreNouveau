package com.shynieke.georenouveau.item;

import com.hollingsworth.arsnouveau.api.item.AbstractSummonCharm;
import com.hollingsworth.arsnouveau.common.block.tile.SummoningTile;
import com.shynieke.georenouveau.entity.GeOreGolem;
import com.shynieke.georenouveau.entity.LinkedGeOre;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class GeOreGolemCharm extends AbstractSummonCharm {
	private final LinkedGeOre linkedGeOre;

	public GeOreGolemCharm(LinkedGeOre linkedGeOre) {
		super(new Item.Properties());
		this.linkedGeOre = linkedGeOre;
		withTooltip(Component.translatable("tooltip.geore_nouveau." + linkedGeOre.getName() + "_charm"));
	}

	@Override
	public InteractionResult useOnBlock(UseOnContext context, Level level, BlockPos pos) {
		GeOreGolem golem = new GeOreGolem(CompatRegistry.GEORE_GOLEM.get(), level);
		golem.setPos(pos.getX(), pos.above().getY(), pos.getZ());
		golem.setLinkedGeOre(linkedGeOre);
		level.addFreshEntity(golem);
		return InteractionResult.SUCCESS;
	}

	@Override
	public InteractionResult useOnSummonTile(UseOnContext context, Level level, SummoningTile summoningTile, BlockPos pos) {
		return useOnBlock(context, level, pos);
	}
}
