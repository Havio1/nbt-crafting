/*
 * Copyright 2020-2022 Siphalor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 */

package de.siphalor.nbtcrafting.mixin.client;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.recipebook.RecipeBookGhostSlots;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(RecipeBookGhostSlots.class)
public abstract class MixinRecipeBookGhostSlots {

	@Shadow
	@Final
	private List<RecipeBookGhostSlots.GhostInputSlot> slots;

	@Inject(
			method = "draw",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getGuiGhostRecipeOverlay()Lnet/minecraft/client/render/RenderLayer;", shift = Shift.BEFORE),
			locals = LocalCapture.CAPTURE_FAILSOFT
	)
	public void draw(DrawContext context, MinecraftClient minecraftClient, int xOffset, int yOffset, boolean bool, float float_1, CallbackInfo ci, int i) {
		if (i != 0) {
			RecipeBookGhostSlots.GhostInputSlot slot = slots.get(i);
			context.drawItemInSlot(minecraftClient.textRenderer, slot.getCurrentItemStack(), slot.getX() + xOffset, slot.getY() + yOffset);

		}
	}
}
