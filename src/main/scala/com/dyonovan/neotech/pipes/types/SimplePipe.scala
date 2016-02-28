package com.dyonovan.neotech.pipes.types

import com.dyonovan.neotech.pipes.collections.WorldPipes
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Paul Davis pauljoda
  * @since August 16, 2015
  *
  * This is the base pipe and interface for all pipes. Every pipe should extend this
  */
trait SimplePipe extends TileEntity {
    WorldPipes.notifyPipes()

    override def invalidate() = WorldPipes.notifyPipes()

    /**
      * Used as a simple check to see if the pipe can connect. At it's most basic, it just checks if the tile in that
      * direction is a pipe. This is mainly used for path finding but also on the renderer
 *
      * @param facing The direction from this block
      * @return
      */
    def canConnect(facing: EnumFacing): Boolean = {
        if (getWorld.getTileEntity(getPos) == null) return false
        getWorld.getTileEntity(getPos) match {
            case advanced : AdvancedPipe if advanced.isDisabled(facing) => return false
            case _ =>
        }
        getWorld.getTileEntity(getPos.offset(facing)) match {
            case advanced: AdvancedPipe => !advanced.isDisabled(facing.getOpposite)
            case pipe: SimplePipe => true
            case _ => true
        }
    }

    /**
      * Sometimes we need to know if the connection is more than just a pipe. Usually an inventory of some sort.
      * This is used primarily on the renderer to render the block on the pipe
      *
      * @param facing The direction from this block
      * @return
      */
    def isSpecialConnection(facing : EnumFacing) : Boolean = !getWorld.getTileEntity(getPos.offset(facing)).isInstanceOf[SimplePipe]

    /**
      * Convert the position to a long format
      *
      * @return
      */
    def getPosAsLong: Long = getPos.toLong

    /**
      * Called when this pipe is broken
      */
    def onPipeBroken() : Unit = {}
}
