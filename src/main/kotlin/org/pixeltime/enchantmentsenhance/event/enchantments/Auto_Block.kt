package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Auto_Block : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "auto_block"))

    @EventHandler
    fun onBreak(blockBreakEvent: BlockBreakEvent) {
        val player = blockBreakEvent.player
        if (player.inventory.itemInHand != null && player.itemInHand.hasItemMeta() && player.itemInHand.itemMeta.hasLore() && KM.getLevel(translateAlternateColorCodes, player.itemInHand.itemMeta.lore) > 0) {
            this.autoBlock(player)
        }
    }

    fun autoBlock(player: Player) {
        try {
            val contents: Array<ItemStack> = player.inventory.contents
            for (i in contents.indices) {
                val itemStack = contents[i]
                val n = itemStack.amount / 9
                val n2 = itemStack.amount / 4
                if (n > 0) {
                    if (itemStack.type == Material.EMERALD) {
                        removeItems(player.inventory, Material.EMERALD, n * 9)
                        player.inventory.addItem(ItemStack(Material.EMERALD_BLOCK, n))
                    }
                    if (itemStack.type == Material.DIAMOND) {
                        removeItems(player.inventory, Material.DIAMOND, n * 9)
                        player.inventory.addItem(ItemStack(Material.DIAMOND_BLOCK, n))
                    }
                    if (itemStack.type == Material.IRON_INGOT) {
                        removeItems(player.inventory, Material.IRON_INGOT, n * 9)
                        player.inventory.addItem(ItemStack(Material.IRON_BLOCK, n))
                    }
                    if (itemStack.type == Material.GOLD_INGOT) {
                        removeItems(player.inventory, Material.GOLD_INGOT, n * 9)
                        player.inventory.addItem(ItemStack(Material.GOLD_BLOCK, n))
                    }
                    if (itemStack.type == Material.INK_SACK && itemStack.data.data.toInt() == 4) {
                        removeItems(player.inventory, Material.INK_SACK, n * 9)
                        player.inventory.addItem(ItemStack(Material.LAPIS_BLOCK, n))
                    }
                    if (itemStack.type == Material.COAL) {
                        removeItems(player.inventory, Material.COAL, n * 9)
                        player.inventory.addItem(ItemStack(Material.COAL_BLOCK, n))
                    }
                    if (itemStack.type == Material.REDSTONE) {
                        removeItems(player.inventory, Material.REDSTONE, n * 9)
                        player.inventory.addItem(ItemStack(Material.REDSTONE_BLOCK, n))
                    }
                }
                if (n2 > 0) {
                    if (itemStack.type == Material.CLAY_BALL) {
                        removeItems(player.inventory, Material.CLAY_BALL, n * 4)
                        player.inventory.addItem(ItemStack(Material.CLAY, n))
                    }
                    if (itemStack.type == Material.SNOW_BALL) {
                        removeItems(player.inventory, Material.SNOW_BALL, n * 9)
                        player.inventory.addItem(ItemStack(Material.SNOW, n))
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }

    companion object {
        fun removeItems(inventory: Inventory, material: Material, num: Int) {
            var n = num
            if (n <= 0) {
                return
            }
            val size = inventory.size
            var i = 0
            while (i < size) {
                val item = inventory.getItem(i)
                if (item != null) {
                    if (material == item.type) {
                        val amount = item.amount - n
                        if (amount > 0) {
                            item.amount = amount
                            break
                        }
                        inventory.clear(i)
                        n = -amount
                        if (n == 0) {
                            break
                        }
                    }
                }
                ++i
            }
        }
    }
}
