package com.dyonovan.neotech.events

import com.dyonovan.neotech.lib.Reference
import com.dyonovan.neotech.registries.ConfigRegistry
import com.teambr.bookshelf.client.gui.GuiColor
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.text.{Style, TextComponentString}
import net.minecraft.util.text.event.ClickEvent
import net.minecraftforge.common.ForgeVersion
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.{FMLModContainer, Loader}

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 1/24/2016
  */
object OnPlayerLoginEvent {

    var firstTime = true

    @SubscribeEvent
    def onPlayerLogin(event: EntityJoinWorldEvent): Unit = {

        event.getEntity match {
            case player: EntityPlayer if event.getWorld.isRemote && ConfigRegistry.versionCheck && firstTime =>
                val mod = Loader.instance().getModList.toArray()
                var modContainer: FMLModContainer = null
                for (m <- mod) {
                    m match {
                        case container: FMLModContainer =>
                            if (container.getModId == Reference.MOD_ID) {
                                modContainer = container
                            }
                        case _ =>
                    }
                }
                val versionCheck = ForgeVersion.getResult(modContainer)
                if (versionCheck.status == ForgeVersion.Status.OUTDATED) {
                    val msg = GuiColor.ORANGE + "NEOTECH" + GuiColor.WHITE + " is outdated. Newset version is " + GuiColor.GREEN +
                      versionCheck.target + GuiColor.WHITE
                    player.addChatComponentMessage(new TextComponentString(msg))
                    val clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, versionCheck.url)
                    val chatStyle = new Style().setChatClickEvent(clickEvent)
                    val update = new TextComponentString("Update at " + versionCheck.url)
                    update.setChatStyle(chatStyle)
                    player.addChatComponentMessage(update)
                }
                firstTime = false
            case _ =>
        }
    }

}
