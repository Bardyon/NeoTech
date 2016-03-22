package com.dyonovan.neotech.client.gui.misc

import com.dyonovan.neotech.common.tiles.misc.TileChunkLoader
import com.teambr.bookshelf.client.gui.GuiBase
import com.teambr.bookshelf.client.gui.component.control.GuiComponentButton
import com.teambr.bookshelf.common.container.ContainerGeneric
import net.minecraftforge.fml.client.FMLClientHandler

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Paul Davis <pauljoda>
  * @since 1/19/2016
  */
class GuiChunkLoader(tile : TileChunkLoader)
        extends GuiBase[ContainerGeneric](new ContainerGeneric, 150, 50, "neotech.chunkLoader.title") {
    override def addComponents(): Unit = {
        components += new GuiComponentButton(5, 20, 15, 20, "<") {
            override def doAction(): Unit = {
                tile.setVariable(0, tile.diameter - 1)
                tile.sendValueToServer(0, tile.diameter)
                FMLClientHandler.instance().showGuiScreen(new GuiChunkLoader(tile))
            }
        }
        components += new GuiComponentButton(25, 20, 100, 20, tile.diameter.toString + " chunks around") {
            override def doAction(): Unit = {}
        }
        components += new GuiComponentButton(130, 20, 15, 20, ">") {
            override def doAction(): Unit = {
                tile.setVariable(0, tile.diameter + 1)
                tile.sendValueToServer(0, tile.diameter)
                FMLClientHandler.instance().showGuiScreen(new GuiChunkLoader(tile))
            }
        }
    }
}
