package com.shynieke.georenouveau.entity.goal;

import com.hollingsworth.arsnouveau.api.ANFakePlayer;
import com.hollingsworth.arsnouveau.common.entity.goal.amethyst_golem.HarvestClusterGoal;
import com.shynieke.georenouveau.entity.GeOreGolem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

import static com.hollingsworth.arsnouveau.api.util.BlockUtil.destroyBlockSafely;

public class GeOreHarvestClusterGoal extends HarvestClusterGoal {
	public GeOreGolem geOreGolem;

	public GeOreHarvestClusterGoal(GeOreGolem golem, Supplier<Boolean> canUse) {
		super(golem, canUse);
		this.geOreGolem = golem;
	}

	@Override
	public void harvest(BlockPos p) {
		if (!(golem.level instanceof ServerLevel level)) return;
		for (Direction d : Direction.values()) {
			BlockState state = level.getBlockState(p.relative(d));
			if (state.is(geOreGolem.getLinkedGeOre().getCluster())) {
				ItemStack stack = new ItemStack(Items.DIAMOND_PICKAXE);
				state.getBlock().playerDestroy(level, ANFakePlayer.getPlayer(level), p.relative(d), state, level.getBlockEntity(p), stack);
				destroyBlockSafely(level, p.relative(d), false, ANFakePlayer.getPlayer(level));
			}
		}
	}
}
