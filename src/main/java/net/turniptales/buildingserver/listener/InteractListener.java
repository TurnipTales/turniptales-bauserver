package net.turniptales.buildingserver.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static java.util.Objects.nonNull;
import static org.bukkit.Material.SPLASH_POTION;

public class InteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().isRightClick()) {
            ItemStack currentItem = event.getItem();
            if (nonNull(currentItem) && currentItem.getType().equals(SPLASH_POTION)) {
                event.setCancelled(true);

                Inventory inventory = event.getPlayer().getInventory();
                inventory.removeItem(event.getItem());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();
        if (nonNull(currentItem) && currentItem.getType().equals(SPLASH_POTION)) {
            event.setCancelled(true);
        }
    }
}
