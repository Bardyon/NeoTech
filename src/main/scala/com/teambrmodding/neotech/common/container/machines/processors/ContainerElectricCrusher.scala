package com.teambrmodding.neotech.common.container.machines.processors

import com.teambrmodding.neotech.common.container.machines.ContainerAbstractMachine
import com.teambrmodding.neotech.common.container.slot.SlotFurnaceOutputItemHandler
import com.teambrmodding.neotech.common.tiles.machines.processors.TileElectricCrusher
import net.minecraft.entity.player.InventoryPlayer

/**
 * This file was created for NeoTech
 *
 * NeoTech is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since August 13, 2015
 */
class ContainerElectricCrusher(playerInventory: InventoryPlayer, tile: TileElectricCrusher) extends
        ContainerAbstractMachine(playerInventory, tile) {

    addSlotToContainer(new RestrictedSlot(tile, 0, 41, 35))
    addSlotToContainer(new SlotFurnaceOutputItemHandler(playerInventory.player, tile, 1, 101, 35))
    addSlotToContainer(new SlotFurnaceOutputItemHandler(playerInventory.player, tile, 2, 131, 35))
    addPlayerInventorySlots(8, 84)
}
