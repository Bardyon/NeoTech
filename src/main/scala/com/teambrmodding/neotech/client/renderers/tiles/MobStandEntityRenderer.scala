package com.teambrmodding.neotech.client.renderers.tiles

import com.teambrmodding.neotech.common.tiles.misc.TileMobStand
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.client.renderer.{VertexBuffer, GlStateManager, RenderHelper, Tessellator}
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import org.lwjgl.opengl.GL11

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 2/13/2016
  */
class MobStandEntityRenderer[T <: TileMobStand] extends TileEntitySpecialRenderer[T] {

    override def renderTileEntityAt(te: T, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int): Unit = {
        if (te.entity == null) return
        val ent = te.entity

        GlStateManager.pushMatrix()
        GlStateManager.translate(x + 0.5, y + 1.0D, z + 0.5)
        if(te.fitToBlock){
            val heightScale = 0.5F / ent.height
            val widthScale = 0.5F / ent.width
            if(heightScale >= widthScale)
                GlStateManager.scale(heightScale, heightScale, heightScale)
            else
                GlStateManager.scale(widthScale, widthScale, widthScale)
        } else
            GlStateManager.scale(1.0F - te.scale, 1.0F - te.scale, 1.0F - te.scale)

        if (ent.hasCustomName && te.renderName) {
            renderName(ent, ent.getDisplayName.getFormattedText, x + 0.5, y + 1.0D, z + 0.5, 32, te.getPos)
        }

        if(te.lookAtPlayer) {
            val manager = Minecraft.getMinecraft.getRenderManager
            var angle = Math.toDegrees(Math.atan2(te.getPos.getZ + 0.5 - manager.viewerPosZ, te.getPos.getX + 0.5 - manager.viewerPosX))
            if(angle < 0)
                angle += 360
            GlStateManager.rotate(-angle.toFloat - 90, 0.0F, 1.0F, 0.0F)
        } else
            GlStateManager.rotate(te.rotation * 360, 0.0F, 1.0F, 0.0F)
        RenderHelper.enableStandardItemLighting()
        val renderManager: RenderManager = Minecraft.getMinecraft.getRenderManager

        renderManager.setRenderShadow(false)
        GlStateManager.pushAttrib()
        renderManager.doRenderEntity(ent, 0.0, 0.0, 0.0, 0.0F, partialTicks, true)
        GlStateManager.popAttrib()
        GlStateManager.enableLighting()
        renderManager.setRenderShadow(true)

        GlStateManager.popMatrix()
    }

    def renderName(entityIn: Entity, str: String, x: Double, y: Double, z: Double, maxDistance: Int, pos: BlockPos): Unit = {
        val renderManager: RenderManager = Minecraft.getMinecraft.getRenderManager
        val player = Minecraft.getMinecraft.thePlayer
        val d0: Double = pos.distanceSqToCenter(player.getPosition.getX, player.getPosition.getY, player.getPosition.getZ)

        if (d0 <= (maxDistance * maxDistance).toDouble) {
            val fontRenderer: FontRenderer = renderManager.getFontRenderer
            val f: Float = 1.6F
            val f1: Float = 0.016666668F * f
            GlStateManager.pushMatrix()
            GlStateManager.translate(x.toFloat + 0.0F, y.toFloat + entityIn.height + 0.5F, z.toFloat)
            GL11.glNormal3f(0.0F, 1.0F, 0.0F)
            GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F)
            GlStateManager.rotate(renderManager.playerViewX, 1.0F, 0.0F, 0.0F)
            GlStateManager.scale(-f1, -f1, f1)
            GlStateManager.disableLighting()
            GlStateManager.depthMask(false)
            GlStateManager.disableDepth()
            GlStateManager.enableBlend()
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
            val tessellator: Tessellator = Tessellator.getInstance
            val worldrenderer: VertexBuffer = tessellator.getBuffer
            val i: Int = 0
            val j: Int = fontRenderer.getStringWidth(str) / 2
            GlStateManager.disableTexture2D()
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR)
            worldrenderer.pos((-j - 1).toDouble, (-1 + i).toDouble, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex()
            worldrenderer.pos((-j - 1).toDouble, (8 + i).toDouble, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex()
            worldrenderer.pos((j + 1).toDouble, (8 + i).toDouble, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex()
            worldrenderer.pos((j + 1).toDouble, (-1 + i).toDouble, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex()
            tessellator.draw()
            GlStateManager.enableTexture2D()
            fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, i, 553648127)
            GlStateManager.enableDepth()
            GlStateManager.depthMask(true)
            fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, i, -1)
            GlStateManager.enableLighting()
            GlStateManager.disableBlend()
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F)
            GlStateManager.popMatrix()
        }
    }
}
