package io.github.cosmonetworks.modman

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required

object Core {
    @JvmStatic
    fun main(args: Array<String>) {
        val parser = ArgParser("ModMan")
        val directory by parser.option(
            ArgType.String, shortName = "i", description = "Mod directory"
        ).default("")
        val debug by parser.option(
            ArgType.Boolean, shortName = "d", description = "Turn on debug mode"
        ).default(false)
        val type by parser.option(
            ArgType.Choice<Type>(), shortName = "t", description = "Type of the mod"
        ).default(Type.FORGE)
        val mod by parser.option(
            ArgType.String, shortName = "m", description = "Name of mod"
        ).required()
        parser.parse(args)

        val modDir = getModDir(directory, type)
        println(modDir)
    }

    private fun getModDir(modDir: String, type: Type): String {
        val userHome = System.getProperty("user.home")
        return modDir.ifEmpty {
            val sep = "/"
            val mcDir = userHome + sep + ".minecraft"
            if (type != Type.GLASS) {
                mcDir + sep + "mods"
            } else {
                mcDir + sep + "shards"
            }
        }
    }
}