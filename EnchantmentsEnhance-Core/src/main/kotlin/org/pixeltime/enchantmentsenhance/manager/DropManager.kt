/*
 *     Copyright (C) 2017-Present HealPot
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.Material
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.util.Util
import org.pixeltime.enchantmentsenhance.util.XMaterial
import java.util.*
import kotlin.collections.ArrayList

class DropManager {


    companion object {
        @JvmStatic
        fun setUp() {
            SettingsManager.config.getStringList("lifeskill.mining").forEach {
                val mat = XMaterial.fromString(it).parseMaterial()
                if (!mining.contains(mat)) {
                    mining.add(mat)
                }
            }
            SettingsManager.config.getStringList("lifeskill.chopping").forEach {
                val mat = (XMaterial.fromString(it).parseMaterial())
                if (!chopping.contains(mat)) {
                    chopping.add(mat)
                }
            }
        }

        @JvmStatic
        val mining: ArrayList<Material> = ArrayList()
        @JvmStatic
        val chopping: ArrayList<Material> = ArrayList()
        @JvmField
        val miningChance = SettingsManager.config.getDouble(
                "reward.mining.chance")
        @JvmField
        val choppingChance = SettingsManager.config.getDouble(
                "reward.chopping.chance")
        @JvmField
        val fishingChance = SettingsManager.config.getDouble(
                "reward.fishing.chance")
        @JvmField
        val killingChance = SettingsManager.config.getDouble(
                "reward.killing.chance")
        @JvmField
        val breedingChance = SettingsManager.config.getDouble(
                "reward.breeding.chance")
        @JvmField
        val smeltingChance = SettingsManager.config.getDouble(
                "reward.smelting.chance")
        @JvmField
        val miningLootTable = SettingsManager.config
                .getIntegerList("reward.mining.drops")
        @JvmField
        val choppingLootTable = SettingsManager.config
                .getIntegerList("reward.chopping.drops")
        @JvmField
        val fishingLootTable = SettingsManager.config
                .getIntegerList("reward.fishing.drops")
        @JvmField
        val killingLootTable = SettingsManager.config
                .getIntegerList("reward.killing.drops")
        @JvmField
        val breedingLootTable = SettingsManager.config
                .getIntegerList("reward.breeding.drops")
        @JvmField
        val smeltingLootTable = SettingsManager.config
                .getIntegerList("reward.breeding.drops")

        @JvmStatic
        fun randomDrop(player: Player, table: List<Int>) {
            val stoneType = table[((0..table.size).random())]
            Main.getApi().addItem(
                    player.name, stoneType, 1)
            Util.sendMessage(SettingsManager.lang.getString("item.get").replace("%ITEM%", SettingsManager.lang.getString("item.$stoneType")), player)
        }

        @JvmStatic
        fun ClosedRange<Int>.random() =
                Random().nextInt(endInclusive - start) + start
    }
}