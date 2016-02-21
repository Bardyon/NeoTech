package com.dyonovan.neotech.common.items.tools

import com.dyonovan.neotech.lib.Reference
import net.minecraft.block.Block
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemPickaxe, ItemStack}
import net.minecraft.util.BlockPos
import net.minecraft.world.World

/**
  * This file was created for Bookshelf API
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 2/21/2016
  */
class ElectricPickaxe extends ItemPickaxe(Item.ToolMaterial.EMERALD) with BaseElectricTool {

    setUnlocalizedName(Reference.MOD_ID + ":electricPickaxe")

    override def onBlockDestroyed(stack: ItemStack, world: World, blocPos: Block, pos: BlockPos, player: EntityLivingBase): Boolean = {
        extractEnergy(stack, 1, simulate = false)
        updateDamage(stack)
        true
    }

    override def onBlockStartBreak(stack: ItemStack, pos: BlockPos, player: EntityPlayer): Boolean = {
        getEnergyStored(stack) <= 0
    }
}
