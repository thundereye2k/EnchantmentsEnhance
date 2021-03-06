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

package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.Material
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.PlayerDeathEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener


class Suicide : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("A chance to create an explosion when the player dies", "死后爆炸")
    }

    override fun lang(): Array<String> {
        return arrayOf("帝陨")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDeath(playerDeathEvent: PlayerDeathEvent) {
        val player = playerDeathEvent.entity


        try {
            val level = getLevel(player)
            if (level > 0 && (roll(level))) {
                for (i in 1..5) {
                    val tnt = player.world.spawn(player.getTargetBlock(HashSet<Material>(), 50).location.add(0.0, 1.0, 0.0), TNTPrimed::class.java)
                    (tnt as TNTPrimed).fuseTicks = 10
                }
            }
        } catch (ex: Exception) {
        }
    }
}
