package org.pinkprison.pinkcore.core.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.pinkprison.pinkcore.PinkCore;
import org.pinkprison.pinkcore.api.utils.ColorUtils;
import org.pinkprison.pinkcore.core.config.Loader;

public class BlockListener implements Listener {

    private final Loader loader;

    public BlockListener(PinkCore plugin, Loader loader) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.loader = loader;
    }

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent event) {
        final Block block = event.getBlock();
        final Player player = event.getPlayer();

        if (isBlacklisted(block, loader.getAntiPlaceBlacklistedBlocks().toArray(new String[0]))) {
            player.sendMessage(ColorUtils.getColored(loader.getAntiPlaceMessage()));
            event.setCancelled(true);
        }
    }

    private boolean isBlacklisted(Block placedBlock, String... blacklistedBlockIDs) {
        for (String blockId : blacklistedBlockIDs) {
            if (placedBlock.getType().toString().equalsIgnoreCase(blockId)) {
                return true;
            }
        }
        return false;
    }
}
