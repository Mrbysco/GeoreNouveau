package com.shynieke.georenouveau.entity;

import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.entity.AmethystGolem;
import com.hollingsworth.arsnouveau.common.entity.goal.GoBackHomeGoal;
import com.hollingsworth.arsnouveau.common.entity.goal.amethyst_golem.DepositAmethystGoal;
import com.hollingsworth.arsnouveau.setup.registry.DataComponentRegistry;
import com.shynieke.georenouveau.GeOreNouveau;
import com.shynieke.georenouveau.entity.goal.GeOreConvertBuddingGoal;
import com.shynieke.georenouveau.entity.goal.GeOreGrowClusterGoal;
import com.shynieke.georenouveau.entity.goal.GeOreHarvestClusterGoal;
import com.shynieke.georenouveau.entity.goal.GeOrePickupAmethystGoal;
import com.shynieke.georenouveau.registry.CompatRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class GeOreGolem extends AmethystGolem {
	public static final EntityDataAccessor<LinkedGeOre> LINKED_GEORE = SynchedEntityData.defineId(GeOreGolem.class, CompatRegistry.LINKED_SERIALIZER.get());

	public GeOreGolem(EntityType<? extends PathfinderMob> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new GoBackHomeGoal(this, this::getHome, 10, () -> true));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new GeOreConvertBuddingGoal(this, () -> convertCooldown <= 0 && getHome() != null && getMainHandItem().isEmpty()));
		this.goalSelector.addGoal(4, new GeOreGrowClusterGoal(this, () -> growCooldown <= 0 && getHome() != null && getMainHandItem().isEmpty()));
		this.goalSelector.addGoal(5, new GeOreHarvestClusterGoal(this, () -> harvestCooldown <= 0 && getHome() != null && !isImbueing() && getMainHandItem().isEmpty()));
		this.goalSelector.addGoal(2, new GeOrePickupAmethystGoal(this, () -> getHome() != null && pickupCooldown <= 0));
		this.goalSelector.addGoal(2, new DepositAmethystGoal(this, () -> getHome() != null && !getMainHandItem().isEmpty()));
	}

	@Override
	public void scanBlocks() {
		BlockPos pos = getHome().immutable();
		amethystBlocks = new ArrayList<>();
		buddingBlocks = new ArrayList<>();
		final LinkedGeOre linked = getLinkedGeOre();
		for (BlockPos b : BlockPos.betweenClosed(pos.below(3).south(5).east(5), pos.above(10).north(5).west(5))) {
			if (this.level().getBlockState(b).isAir())
				continue;
			if (this.level().getBlockState(b).is(linked.getBlock())) {
				amethystBlocks.add(b.immutable());
			}

			if (this.level().getBlockState(b).is(linked.getBudding())) {
				buddingBlocks.add(b.immutable());
			}
		}
	}


	@Override
	public void die(DamageSource source) {
		if (!this.level().isClientSide) {
			ItemStack stack = new ItemStack(getLinkedGeOre().getCharm());
			stack.set(DataComponentRegistry.PERSISTENT_FAMILIAR_DATA, createCharmData());
			this.level().addFreshEntity(new ItemEntity(this.level(), getX(), getY(), getZ(), stack));
			if (this.getMainHandItem() != null)
				this.level().addFreshEntity(new ItemEntity(this.level(), getX(), getY(), getZ(), this.getMainHandItem()));
		}

		//Manually triggering LivingEntity's die so that it doesn't drop Bailey's Amethyst Golem Charm
		if (CommonHooks.onLivingDeath(this, source)) return;
		if (!this.isRemoved() && !this.dead) {
			Entity entity = source.getEntity();
			LivingEntity livingentity = this.getKillCredit();
			if (this.deathScore >= 0 && livingentity != null) {
				livingentity.awardKillScore(this, this.deathScore, source);
			}

			if (this.isSleeping()) {
				this.stopSleeping();
			}

			if (!this.level().isClientSide && this.hasCustomName()) {
				GeOreNouveau.LOGGER.info("Named entity {} died: {}", this, this.getCombatTracker().getDeathMessage().getString());
			}

			this.dead = true;
			this.getCombatTracker().recheckStatus();
			if (this.level() instanceof ServerLevel serverLevel) {
				if (entity == null || entity.killedEntity(serverLevel, this)) {
					this.gameEvent(GameEvent.ENTITY_DIE);
					this.dropAllDeathLoot(serverLevel, source);
					this.createWitherRose(livingentity);
				}

				this.level().broadcastEntityEvent(this, (byte) 3);
			}

			this.setPose(Pose.DYING);
		}
	}

	@Override
	public boolean onDispel(@Nullable LivingEntity caster) {
		if (this.isRemoved())
			return false;

		if (!this.level().isClientSide) {
			ItemStack stack = new ItemStack(getLinkedGeOre().getCharm());
			stack.set(DataComponentRegistry.PERSISTENT_FAMILIAR_DATA, createCharmData());
			this.level().addFreshEntity(new ItemEntity(this.level(), getX(), getY(), getZ(), stack.copy()));
			stack = getMainHandItem();
			this.level().addFreshEntity(new ItemEntity(this.level(), getX(), getY(), getZ(), stack));
			ParticleUtil.spawnPoof((ServerLevel) this.level(), blockPosition());
			this.remove(RemovalReason.DISCARDED);
		}
		return true;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(LINKED_GEORE, LinkedGeOre.DEFAULT);
	}

	public void setLinkedGeOre(LinkedGeOre linked) {
		this.entityData.set(LINKED_GEORE, linked);
	}

	public LinkedGeOre getLinkedGeOre() {
		return this.entityData.get(LINKED_GEORE);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setLinkedGeOre(LinkedGeOre.values()[tag.getByte("LinkedGeOre")]);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putByte("LinkedGeOre", (byte) this.getLinkedGeOre().ordinal());
	}
}
