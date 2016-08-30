package com.teambrmodding.neotech.common.container.storage

import com.teambrmodding.neotech.common.tiles.storage.TileRFStorage
import com.teambr.bookshelf.common.container.BaseContainer
import net.minecraft.entity.player.InventoryPlayer

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Paul Davis <pauljoda>
  * @since 2/15/2016
  */
class ContainerRFStorage(playerInventory: InventoryPlayer, tile: TileRFStorage) extends
        BaseContainer(playerInventory, tile) {

    addSlotToContainer(new RestrictedSlot(tile, 0, 30, 20) {
        override def getSlotTexture = "neotech:gui/in"
    })
    addSlotToContainer(new RestrictedSlot(tile, 1, 30, 60) {
        override def getSlotTexture = "neotech:gui/out"
    })

    addPlayerInventorySlots(8, 84)
}
