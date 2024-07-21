package com.shynieke.georenouveau.item;

import com.hollingsworth.arsnouveau.api.block.IPedestalMachine;
import com.hollingsworth.arsnouveau.api.scrying.SingleBlockScryer;
import com.hollingsworth.arsnouveau.common.items.ModItem;
import com.hollingsworth.arsnouveau.common.ritual.RitualScrying;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import com.shynieke.georenouveau.entity.LinkedGeOre;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class GeOreDowsingRod extends ModItem {
	private final LinkedGeOre linkedGeOre;

	public GeOreDowsingRod(Properties properties, LinkedGeOre linkedGeOre) {
		super(properties);
		this.linkedGeOre = linkedGeOre;
		withTooltip(Component.translatable("tooltip.geore_nouveau." + linkedGeOre.getName() + "_dowsing_rod"));
	}

	public GeOreDowsingRod(LinkedGeOre linkedGeOre) {
		this(ItemsRegistry.defaultItemProperties().durability(4), linkedGeOre);
	}


	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getLevel() instanceof ServerLevel && context.getLevel().getBlockEntity(context.getClickedPos()) instanceof IPedestalMachine ipm) {
			ipm.lightPedestal(context.getLevel());
			return InteractionResult.SUCCESS;
		}
		return super.useOn(context);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);
		heldStack.setDamageValue(player.getItemInHand(hand).getDamageValue() + 1);
		if (heldStack.getDamageValue() >= getMaxDamage(heldStack))
			heldStack.shrink(1);
		if (!level.isClientSide) {
			player.addEffect(new MobEffectInstance(level.holderOrThrow(ModPotions.MAGIC_FIND_EFFECT.getKey()), 60 * 20));
			SingleBlockScryer singleBlockScryer = new SingleBlockScryer(linkedGeOre.getBudding());
			RitualScrying.grantScrying((ServerPlayer) player, 60 * 20, singleBlockScryer);

		}
		return super.use(level, player, hand);
	}
}
