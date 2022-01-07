package com.faithl.faithllevel

import com.faithl.faithllevel.api.FaithlLevelAPI
import org.bukkit.Bukkit
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.*
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.module.lang.sendLang
import taboolib.module.metrics.Metrics

object FaithlLevel:Plugin() {

    @Config("settings.yml", migrate = true,autoReload = true)
    lateinit var setting: Configuration
        private set

    override fun onLoad() {
        Metrics(13122, pluginVersion, runningPlatform)
    }

    override fun onEnable() {
        reload()
        console().sendLang("plugin-enabled", pluginVersion,KotlinVersion.CURRENT.toString())
    }

    override fun onDisable() {
        console().sendLang("plugin-disabled")
    }

    fun reload() {
        if (!FaithlLevelAPI.folderLevel.exists()) {
            releaseResourceFile("levels/example.yml")
        }
        if (!FaithlLevelAPI.folderScript.exists()) {
            releaseResourceFile("scripts/example.yml")
        }
        if (!FaithlLevelAPI.folderTrait.exists()) {
            releaseResourceFile("scripts/")
        }
        FaithlLevelAPI.reloadLevel()
        FaithlLevelAPI.reloadScript()
    }

}