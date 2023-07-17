package com.shynieke.georenouveau.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.shynieke.georenouveau.client.model.GeOreGolemModel;
import com.shynieke.georenouveau.entity.GeOreGolem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.util.RenderUtils;

public class GeOreGolemRenderer extends GeoEntityRenderer<GeOreGolem> {
	public GeOreGolemRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new GeOreGolemModel());
	}

	GeOreGolem golem;
	MultiBufferSource buffer;
	ResourceLocation text;

	@Override
	public RenderType getRenderType(GeOreGolem animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityCutoutNoCull(texture);
	}

	@Override
	public void preRender(PoseStack poseStack, GeOreGolem animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.golem = animatable;
		this.buffer = bufferSource;
		this.text = this.getTextureLocation(animatable);
		super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void renderRecursively(PoseStack stack, GeOreGolem animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer bufferIn, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (bone.getName().equals("item")) {
			stack.pushPose();
			RenderUtils.translateToPivotPoint(stack, bone);
			stack.translate(0, -0.10, 0);
			ItemStack itemstack = golem.getHeldStack();
			Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, stack, this.buffer, animatable.level(), (int) golem.getOnPos().asLong());
			stack.popPose();
			bufferIn = buffer.getBuffer(RenderType.entityCutoutNoCull(text));
		}
		super.renderRecursively(stack, animatable, bone, renderType, bufferSource, bufferIn, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}