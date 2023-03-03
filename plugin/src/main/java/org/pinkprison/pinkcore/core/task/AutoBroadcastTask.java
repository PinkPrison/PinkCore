package org.pinkprison.pinkcore.core.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.pinkprison.pinkcore.api.utils.ColorUtils;

public class AutoBroadcastTask extends BukkitRunnable {

    private final String[] messages;
    private int index = 0;
    private boolean stop = false;

    public AutoBroadcastTask(String... messages) {
        this.messages = ColorUtils.color(messages);
    }

    @Override
    public void run() {
        if (stop) {
            cancel();
            return;
        }

        Bukkit.broadcastMessage(messages[index]); // Evt. modulus (index % messages.length)?
        index++;
        if (index >= messages.length) {
            index = 0;
        }
    }

    public void stop() {
        this.stop = true;
    }
}