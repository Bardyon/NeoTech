package com.dyonovan.neotech.common.items

import java.text.NumberFormat
import java.util.Locale

import com.dyonovan.neotech.common.entities.EntityNet
import com.dyonovan.neotech.managers.ItemManager
import com.teambr.bookshelf.client.gui.GuiColor
import com.teambr.bookshelf.common.items.traits.ItemBattery
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{EnumAction, ItemStack}
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.StatCollector
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

import scala.util.Random

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 2/10/2016
  */
class ItemMobGun extends BaseItem("mobGun", 1) with ItemBattery {

    final val RF_PER_USE = 2500

    override def setDefaultTags(stack: ItemStack): Unit = {
        var tier = 1
        if (stack.hasTagCompound && stack.getTagCompound.hasKey("Tier"))
            tier = stack.getTagCompound.getInteger("Tier")
        var energy = 0
        if (stack.hasTagCompound && stack.getTagCompound.hasKey("Energy"))
            energy = stack.getTagCompound.getInteger("Energy")
        val amount = getTierPower(tier)
        val tag = new NBTTagCompound
        tag.setInteger("EnergyCapacity", amount._1)
        tag.setInteger("MaxExtract", amount._2)
        tag.setInteger("MaxReceive", amount._2)
        tag.setInteger("Tier", tier)
        tag.setInteger("Energy", energy)
        stack.setTagCompound(tag)
    }

    /**
      * Defines amount of power Gun has based on Tier of Battery used in crafting
      *
      * @param t Battery Tier
      * @return Touple2(capacity, maxReceive)
      */
    def getTierPower(t: Int): (Int, Int) = {
        t match {
            case 1 => (25000, 2500)
            case 2 => (100000, 10000)
            case 3 => (1000000, 100000)
            case _ => (25000, 2500)
        }
    }

    override def onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer): ItemStack = {
        if (hasAmmo(player, remove = false) && extractEnergy(stack, RF_PER_USE, simulate = true) == RF_PER_USE) {
            player.setItemInUse(stack, getMaxItemUseDuration(stack))
        }
        else world.playSoundAtEntity(player, "fire.ignite", 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F))
        stack
    }

    private def hasAmmo(player: EntityPlayer, remove: Boolean): Boolean = {
        for (i <- 0 until player.inventory.getSizeInventory) {
            if (player.inventory.getStackInSlot(i) != null && !player.inventory.getStackInSlot(i).hasTagCompound &&
              player.inventory.getStackInSlot(i).getItem.isInstanceOf[ItemManager.mobNet.type]) {
                if (remove) player.inventory.decrStackSize(i, 1)
                return true
            }
        }
        false
    }

    override def getItemUseAction(stack: ItemStack): EnumAction = {
        EnumAction.BOW
    }

    override def getMaxItemUseDuration(stack: ItemStack): Int = 72000

    override def onPlayerStoppedUsing(stack: ItemStack, world: World, player: EntityPlayer, timeLeft: Int): Unit = {
        if (!world.isRemote) {
            hasAmmo(player, remove = true)
            extractEnergy(stack, RF_PER_USE, simulate = false)
            val heldTime = this.getMaxItemUseDuration(stack) - timeLeft
            var f: Float = heldTime.toFloat / 20.0F
            f = (f * f + f * 2.0F) / 3.0F
            if (f < 0.1D) return
            else if (f > 1.0F) f = 1.0F

            val net = new EntityNet(world, player, f * 2.0F)
            world.spawnEntityInWorld(net)
            world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F))
        }
    }

    @SideOnly(Side.CLIENT)
    override def addInformation(stack: ItemStack, player: EntityPlayer, list: java.util.List[String], boolean: Boolean): Unit = {
        if (stack.hasTagCompound) {
            list.add(GuiColor.ORANGE + StatCollector.translateToLocal("neotech.text.redstoneFlux"))
            list.add(NumberFormat.getNumberInstance(Locale.forLanguageTag(Minecraft.getMinecraft.gameSettings.language))
              .format(getEnergyStored(stack)) +
              " / " +
              NumberFormat.getNumberInstance(Locale.forLanguageTag(Minecraft.getMinecraft.gameSettings.language))
                .format(getMaxEnergyStored(stack)) + " RF")
        }
    }
}
