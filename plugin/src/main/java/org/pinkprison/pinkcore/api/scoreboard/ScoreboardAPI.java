package org.pinkprison.pinkcore.api.scoreboard;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Scoreboard API
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author PandaPeter
 * @since 2.0.0
 */
public class ScoreboardAPI {

    private static final Map<UUID, Board> BOARD_MAP = new HashMap<>();

    /**
     * Get the board for the player.<p>
     * If the player is offline, it will return null.<p>
     * If the player doesn't have a board, it will create one.<p>
     * If the player has a board, it will return the board.<p>
     *
     * @param player The player to get the board for.
     * @return The board for the player.
     */
    public static Board getBoard(Player player) {
        if (!player.isOnline()) {
            return null;
        }

        if (BOARD_MAP.containsKey(player.getUniqueId())) {
            return BOARD_MAP.get(player.getUniqueId());
        }

        Board board = new Board(player);
        BOARD_MAP.put(player.getUniqueId(), board);
        return board;
    }

    /**
     * Remove the board for the player.
     *
     * @param uuid The uuid of the player to remove the board for.
     */
    public static void removeBoard(UUID uuid) {
        BOARD_MAP.remove(uuid);
    }
}