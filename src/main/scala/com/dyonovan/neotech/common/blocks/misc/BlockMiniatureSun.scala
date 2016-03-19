package com.dyonovan.neotech.common.blocks.misc

import com.dyonovan.neotech.client.gui.misc.GuiFertilizer
import com.dyonovan.neotech.common.blocks.BaseBlock
import com.dyonovan.neotech.common.blocks.states.NeoStates
import com.dyonovan.neotech.common.container.misc.ContainerFertilizer
import com.dyonovan.neotech.common.tiles.misc.TileFertilizer
import com.dyonovan.neotech.managers.ItemManager
import com.teambr.bookshelf.common.blocks.traits.DropsItems
import com.teambr.bookshelf.common.tiles.traits.OpensGui
import net.minecraft.block.material.Material
import net.minecraft.block.state.{BlockStateContainer, IBlockState}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.{Entity, EntityLivingBase}
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.{AxisAlignedBB, BlockPos}
import net.minecraft.util.{EnumBlockRenderType, EnumFacing}
import net.minecraft.world.{IBlockAccess, World}

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since August 22, 2015
  */

class BlockMiniatureSun(name: String, tileEntity: Class[_ <: TileEntity]) extends
        BaseBlock(Material.iron, name, tileEntity) with OpensGui with DropsItems {

    setLightLevel(1.0F)
    setDefaultState(this.blockState.getBaseState
            .withProperty(NeoStates.ON_BLOCK, 6.asInstanceOf[Integer]))

    override def isFullBlock(state : IBlockState): Boolean = false
    override def isFullCube(state : IBlockState) : Boolean = false
    override def isOpaqueCube(state : IBlockState) : Boolean = false

    override def onBlockPlaced(world: World, pos: BlockPos, facing: EnumFacing, hitX : Float, hitY : Float, hitZ : Float, meta : Int, placer : EntityLivingBase) : IBlockState = {
        var attachedSide = 6

        placer match {
            case player : EntityPlayer =>
                if(player.isSneaking)
                    return getDefaultState.withProperty(NeoStates.ON_BLOCK, attachedSide.asInstanceOf[Integer])
            case _ =>
        }

        if (attachedSide == 6 && world.getBlockState(pos.offset(facing.getOpposite)) != null && world.getBlockState(pos.offset(facing.getOpposite)).getBlock.isSideSolid(world.getBlockState(pos.offset(facing.getOpposite)), world, pos.offset(facing.getOpposite), facing)) {
            attachedSide = facing.getOpposite.ordinal()
        }

        if(attachedSide == 6) {
            for (dir <- EnumFacing.values()) {
                if (attachedSide == 6 && world.getBlockState(pos.offset(dir)) != null && world.getBlockState(pos.offset(dir)).getBlock.isSideSolid(world.getBlockState(pos.offset(dir)), world, pos.offset(dir), dir.getOpposite))
                    attachedSide = dir.ordinal()
            }
        }

        getDefaultState.withProperty(NeoStates.ON_BLOCK, attachedSide.asInstanceOf[Integer])
    }

    override def rotateBlock(world : World, pos : BlockPos, side : EnumFacing) : Boolean = {
        var attached = world.getBlockState(pos).getValue(NeoStates.ON_BLOCK)
        val tag = new NBTTagCompound
        if(world.getTileEntity(pos) != null)
            world.getTileEntity(pos).writeToNBT(tag)
        attached += 1
        if(attached > 6)
            attached = 0
        world.setBlockState(pos, getDefaultState.withProperty(NeoStates.ON_BLOCK, attached))
        if(tag != null && world.getTileEntity(pos) != null)
            world.getTileEntity(pos).readFromNBT(tag)
        true
    }

    /**
      * Convert the given metadata into a BlockState for this Block
      */
    override def getStateFromMeta(meta: Int): IBlockState = {
        getDefaultState.withProperty(NeoStates.ON_BLOCK, meta.asInstanceOf[Integer])
    }

    /**
      * Convert the BlockState into the correct metadata value
      */
    override def getMetaFromState(state: IBlockState): Int = {
        state.getValue(NeoStates.ON_BLOCK).asInstanceOf[Int]
    }

    override def createBlockState: BlockStateContainer = {
        new BlockStateContainer(this, NeoStates.ON_BLOCK)
    }

    override def getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB = {
        if(source.getBlockState(pos).getValue(NeoStates.ON_BLOCK).asInstanceOf[Int] == 6) {
            this.setBlockBounds(6F / 16F, 6F / 16F, 6F / 16F, 10F / 16F, 10F / 16F, 10F / 16F)
        } else {
            EnumFacing.getFront(source.getBlockState(pos).getValue(NeoStates.ON_BLOCK).asInstanceOf[Int]) match {
                case EnumFacing.UP =>
                    this.setBlockBounds(6F / 16F, 12F / 16F, 6F / 16F, 10F / 16F, 16F / 16F, 10F / 16F)
                case EnumFacing.DOWN =>
                    this.setBlockBounds(6F / 16F, 0F / 16F, 6F / 16F, 10F / 16F, 4 / 16F, 10F / 16F)
                case EnumFacing.SOUTH =>
                    this.setBlockBounds(6F / 16F, 6F / 16F, 12F / 16F, 10F / 16F, 10F / 16F, 16F / 16F)
                case EnumFacing.NORTH =>
                    this.setBlockBounds(6F / 16F, 6F / 16F, 0F / 16F, 10F / 16F, 10F / 16F, 4F / 16F)
                case EnumFacing.EAST =>
                    this.setBlockBounds(12F / 16F, 6F / 16F, 6 / 16F, 16F / 16F, 10F / 16F, 10F / 16F)
                case EnumFacing.WEST =>
                    this.setBlockBounds(0F / 16F, 6F / 16F, 6F / 16F, 4F / 16F, 10F / 16F, 10F / 16F)
                case _ =>
                    this.setBlockBounds(6F / 16F, 6F / 16F, 0F / 16F, 10F / 16F, 10F / 16F, 10F / 16F)
            }
        }
    }

    def facingToInt(facing : EnumFacing) : Int = facing.ordinal()

    override def addCollisionBoxToList(state: IBlockState, worldIn: World, pos: BlockPos, mask : AxisAlignedBB, list : java.util.List[AxisAlignedBB], collidingEntity : Entity) = {
        this.getBoundingBox(state, worldIn, pos)
        super.addCollisionBoxToList(state, worldIn, pos, mask, list, collidingEntity)
    }

    override def getRenderType(state : IBlockState) : EnumBlockRenderType = EnumBlockRenderType.MODEL

    override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
        if(player.inventory.getCurrentItem != null && player.inventory.getCurrentItem.getItem == ItemManager.wrench)
            null
        else
            new ContainerFertilizer(player.inventory, world.getTileEntity(new BlockPos(x, y, z)).asInstanceOf[TileFertilizer])
    }

    override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
        if(player.inventory.getCurrentItem != null && player.inventory.getCurrentItem.getItem == ItemManager.wrench)
            null
        else
            new GuiFertilizer(player.inventory, world.getTileEntity(new BlockPos(x, y, z)).asInstanceOf[TileFertilizer])
    }
}
