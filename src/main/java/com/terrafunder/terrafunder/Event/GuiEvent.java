package com.terrafunder.terrafunder.Event;

import com.terrafunder.terrafunder.Team.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiEvent implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item == null) return;
        if (item.getType() == Material.CHEST && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Sélecteur de team")){
            Inventory inv = Bukkit.createInventory(null, 45, "§6Sélecteur de team");
            for(int i = 0; i< Teams.teams.size(); i++){
                ItemStack banner = new ItemStack(Material.BANNER);
                ItemMeta itemMeta = banner.getItemMeta();
                itemMeta.setDisplayName(Teams.teams.get(i).getColor() + "Rejoignez la Team " + Teams.teams.get(i).getName());
                banner.setItemMeta(itemMeta);
                inv.setItem(i, banner);
            }
            player.openInventory(inv);
        }

    }
    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        if (current == null) return;
        if (inv.getName().equalsIgnoreCase("§6Sélecteur de team"))
        {
            event.setCancelled(true);
            if(current.getType() == Material.BANNER && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+ "Rejoignez la Team " + "DEFENSEUR"))
            {
                player.closeInventory();
            }
        }
    }

}