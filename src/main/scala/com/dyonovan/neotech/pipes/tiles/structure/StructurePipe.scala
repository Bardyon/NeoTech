package com.dyonovan.neotech.pipes.tiles.structure

import com.dyonovan.neotech.pipes.blocks.{BlockPipe, BlockPipeSpecial, PipeProperties}
import com.dyonovan.neotech.pipes.types.SimplePipe
import net.minecraft.item.EnumDyeColor
import net.minecraft.util.EnumFacing

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Paul Davis pauljoda
  * @since August 15, 2015
  */
class StructurePipe extends SimplePipe {
    /**
      * Used as a simple check to see if the pipe can connect. At it's most basic, it just checks if the tile in that
      * direction is a pipe. This is mainly used for path finding but also on the renderer
      * @param facing The direction from this block
      * @return
      */
    override def canConnect(facing: EnumFacing): Boolean = {
        try {
            worldObj.getBlockState(pos.offset(facing))
        } catch {
            case npe : NullPointerException =>
                println("Block at: " + pos.offset(facing) + " does not have a blockstate. Please check here and notify the mod author")
                return false
            case _ : Throwable => return false
        }

        (worldObj.getBlockState(pos.offset(facing)).getBlock match {
            case block: BlockPipe if worldObj.getBlockState(pos.offset(facing)).getBlock.asInstanceOf[BlockPipe].colored && worldObj.getBlockState(pos).getBlock.asInstanceOf[BlockPipe].colored => //We are checking if it and us are colored
                if (worldObj.getBlockState(pos).getValue(PipeProperties.COLOR).ordinal() != 0)
                    worldObj.getBlockState(pos.offset(facing)).getValue(PipeProperties.COLOR).ordinal() == worldObj.getBlockState(pos).getValue(PipeProperties.COLOR).asInstanceOf[EnumDyeColor].ordinal() ||
                            worldObj.getBlockState(pos.offset(facing)).getValue(PipeProperties.COLOR).ordinal() == 0
                else
                    getWorld.getBlockState(pos.offset(facing)).getBlock.isInstanceOf[BlockPipe] ||
                            getWorld.getBlockState(pos.offset(facing)).getBlock.isInstanceOf[BlockPipeSpecial]
            case _ =>
                getWorld.getBlockState(pos.offset(facing)).getBlock.isInstanceOf[BlockPipe] ||
                        getWorld.getBlockState(pos.offset(facing)).getBlock.isInstanceOf[BlockPipeSpecial]
        }) && super.canConnect(facing)
    }
}
