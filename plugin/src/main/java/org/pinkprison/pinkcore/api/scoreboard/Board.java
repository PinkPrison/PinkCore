package org.pinkprison.pinkcore.api.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.pinkprison.pinkcore.api.utils.ColorUtils;

/**
 * Board class for creating a scoreboard for a player.
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author PandaPeter
 * @since 2.0.0
 */
public class Board {

    private final Scoreboard oldScoreboard;
    private final Scoreboard scoreboard;
    private final Objective board;
    private final String[] entries = new String[]{ "&1&r", "&2&r", "&3&r", "&4&r", "&5&r", "&6&r", "&7&r", "&8&r", "&9&r", "&0&r", "&a&r", "&b&r", "&c&r", "&d&r", "&e&r" };

    private final Player player;
    private String title = "";
    private final Team[] lines = new Team[15];
    private boolean shown = true;

    /**
     * Create a new scoreboard for a player
     *
     * @param player The player to create the scoreboard for
     */
    public Board(Player player) {
        this.player = player;
        this.oldScoreboard = player.getScoreboard();
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.board = scoreboard.registerNewObjective("board", "dummy");
        this.board.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.board.setDisplayName(" ");
        for (int i = 0; i < 15; i++) {
            lines[i] = scoreboard.registerNewTeam("line" + (i + 1));
            lines[i].addEntry(ColorUtils.color(entries[i]));
        }
    }

    /**
     * Set the title of the scoreboard
     *
     * @param title The title to set
     */
    public void setTitle(String title) {
        if (this.title.equals(title)) {
            return;
        }

        this.title = title;
        if (!this.shown) {
            return;
        }

        this.board.setDisplayName(title);
    }

    /**
     * Set a specific line of the scoreboard
     *
     * @param line The line to set
     * @param text The text to set
     */
    public void setLine(int line, String text) {
        if (line > 15 || line < 1) {
            return;
        }

        Team t = this.lines[line - 1];
        if (t.getPrefix().equals(text)) {
            return;
        }

        t.setPrefix(text);
        t.setSuffix("");
        this.board.getScore(ColorUtils.color(entries[line - 1])).setScore(line);
    }

    /**
     * Delete a specific line of the scoreboard
     *
     * @param line The line to delete
     */
    public void deleteLine(int line) {
        if (line > 15 || line < 1) {
            return;
        }

        Team t = this.lines[line - 1];
        if (t.getPrefix().equals("")) {
            return;
        }

        t.setPrefix("");
        t.setSuffix("");
        this.board.getScore(ColorUtils.color(entries[line - 1])).setScore(line);
    }

    /**
     * Clear the scoreboard
     */
    public void clearBoard() {
        for (int i = 0; i < 15; i++) {
            this.deleteLine(i + 1);
        }
    }

    /**
     * Toggle the scoreboard
     */
    public void toggle() {
        if (this.shown) {
            this.hide();
        } else {
            this.show();
        }
    }

    /**
     * Show the scoreboard
     */
    public void show() {
        if (this.shown) {
            return;
        }

        this.shown = true;
        this.updateLines();
        this.player.setScoreboard(this.scoreboard);
    }

    /**
     * Hide the scoreboard
     */
    public void hide() {
        if (!this.shown) {
            return;
        }

        this.shown = false;
        this.player.setScoreboard(this.oldScoreboard);
    }

    /**
     * Get the text of a specific line
     *
     * @param line The line to get the text from
     * @return The text of the line
     */
    public String getLine(int line) {
        return this.lines[line - 1].getPrefix();
    }

    /**
     * Get the title of the scoreboard
     *
     * @return The title of the scoreboard
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Check if the scoreboard is shown
     *
     * @return True if the scoreboard is shown, false otherwise
     */
    public boolean isShown() {
        return this.shown;
    }

    /**
     * Update the scoreboard
     */
    private void updateLines() {
        for (int i = 0; i < 15; i++) {
            Team t = lines[i];
            if (t.getPrefix().equals("")) {
                continue;
            }

            this.board.getScore(ColorUtils.color(entries[i])).setScore(i + 1);
        }
    }
}