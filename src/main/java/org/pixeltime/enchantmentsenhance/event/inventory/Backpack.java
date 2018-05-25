/*
 *     Copyright (C) 2017-Present HealPotion
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

package org.pixeltime.enchantmentsenhance.event.inventory;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.MM;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.GUI;
import org.pixeltime.enchantmentsenhance.util.Util;

/**
 * @author HealPotion
 * @version Feb 9, 2018
 */
public class Backpack extends GUI {

    public Backpack(Player p) {
        super(27, SettingsManager.lang.getString("Menu.gui.title"));
        for (int i = 0; i < MM.stoneTypes.size(); i++) {
            setItem(Util.getSlot(i + 1, 1 + (i / 9)), ItemManager
                    .stoneVisualized(i, p, true), player -> {
                Util.sendMessage("You can't take this out for now.",
                        player);
            });
        }
    }


    public static void sendInventoryAsText(Player player) {
        int[] inv = Inventory.getPlayer(player);
        Util.sendMessage(SettingsManager.lang.getString("Item.title"), player);
        for (int i = 0; i < inv.length; i++) {
            Util.sendMessage(SettingsManager.lang.getString("Item.listing")
                            .replaceAll("%ITEM%", SettingsManager.lang.getString("Item."
                                    + i)).replaceAll("%COUNT%", Integer.toString(inv[i])),
                    player);

        }
    }


    public static String getOneStoneCountAsString(Player player, int stoneId) {
        int[] inv = Inventory.getPlayer(player);
        return (SettingsManager.lang.getString("Item.listing").replaceAll(
                "%ITEM%", SettingsManager.lang.getString("Item." + stoneId))
                .replaceAll("%COUNT%", Integer.toString(inv[stoneId])));

    }
}