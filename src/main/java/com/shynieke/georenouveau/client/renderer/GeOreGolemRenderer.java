package com.shynieke.georenouveau.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.shynieke.georenouveau.client.model.GeOreGolemModel;
import com.shynieke.georenouveau.entity.GeOreGolem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.ars_nouveau.geckolib3.geo.render.built.GeoBone;
import software.bernie.ars_nouveau.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.ars_nouveau.geckolib3.util.RenderUtils;

import javax.annotation.Nullable;

public class GeOreGolemRenderer extends GeoEntityRenderer<GeOreGolem> {
	public GeOreGolemRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new GeOreGolemModel());
	}

	GeOreGolem golem;
	MultiBufferSource buffer;
	ResourceLocation text;

	@Override
	public RenderType getRenderType(GeOreGolem animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityCutoutNoCull(textureLocation);
	}

	@Override
	public void renderEarly(GeOreGolem animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
		this.golem = animatable;
		this.buffer = renderTypeBuffer;
		this.text = this.getTextureLocation(animatable);
		super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
	}

	@Override
	public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		if (bone.getName().equals("item")) {
			stack.pushPose();
			RenderUtils.moveToPivot(bone, stack);
			stack.translate(0, -0.10, 0);
			ItemStack itemstack = golem.getHeldStack();
			Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, ItemTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, stack, this.buffer, (int) golem.getOnPos().asLong());
			stack.popPose();
			bufferIn = buffer.getBuffer(RenderType.entityCutoutNoCull(text));
		}
		super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

}