package com.teambrmodding.neotech.managers

import java.util

import com.teambrmodding.neotech.registries._
import net.minecraft.command.ServerCommandManager

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Paul Davis <pauljoda>
  * @since 2/20/2016
  */
object RecipeManager {
    sealed trait RecipeType { def name : String }
    case object FluidFuels  extends RecipeType { val name = "fluidfuels" }
    case object Crusher     extends RecipeType { val name = "crusher" }
    case object Crucible    extends RecipeType { val name = "crucible" }
    case object Solidifier  extends RecipeType { val name = "solidifier" }
    case object Alloyer     extends RecipeType { val name = "alloyer" }
    case object Centrifuge  extends RecipeType { val name = "centrifuge" }

    lazy val recipeHandlers = new util.HashMap[RecipeType, AbstractRecipeHandler[_, _, _]]()

    def preInit() : Unit = {
        CraftingRecipeManager.preInit()
    }

    def init() : Unit = {
        recipeHandlers.put(Crucible,   new CrucibleRecipeHandler().loadHandler())
        recipeHandlers.put(Solidifier, new SolidifierRecipeHandler().loadHandler())
        recipeHandlers.put(FluidFuels, new FluidFuelRecipeHandler().loadHandler())
        recipeHandlers.put(Alloyer,    new AlloyerRecipeHandler().loadHandler())
        recipeHandlers.put(Crusher,    new CrusherRecipeHandler().loadHandler())
        recipeHandlers.put(Centrifuge, new CentrifugeRecipeHandler().loadHandler())
    }

    def initCommands(manager : ServerCommandManager) : Unit = {
        for(handlerType <- recipeHandlers.keySet().toArray) {
            val command = recipeHandlers.get(handlerType.asInstanceOf[RecipeType]).getCommand
            if(command != null)
                manager.registerCommand(command)
        }
    }

    /**
      * Used to get a handler Type, this way we know only defined handlers are cast and sent back, ensures
      * no bad casting (if done correctly)
      *
      * @param handlerType The handler Type
      * @tparam H The Handler Object
      * @return The Handler
      */
    def getHandler[H <: AbstractRecipeHandler[_, _, _]](handlerType : RecipeType) : H =
        recipeHandlers.get(handlerType).asInstanceOf[H]

    /**
      * Used to get Handles by name
      *
      * @param name The name of the handler, eg "Crusher"
      * @tparam H The recipe handler type
      * @return The recipe Handler
      */
    def getHandler[H <: AbstractRecipeHandler[_, _, _]](name : String)  : Option[H] = {
        for(x <- recipeHandlers.keySet().toArray) {
            val handler = x.asInstanceOf[RecipeType]
            if(handler.name.equalsIgnoreCase(name))
                return Option(recipeHandlers.get(handler).asInstanceOf[H])
        }
        None
    }
}
