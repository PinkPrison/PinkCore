package org.pinkprison.pinkcore.api.command;

/**
 * CommandResult is a class that is used to return the result of a sub command execution.
 * It contains the sub command that was executed and the result of the execution.
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author PandaPeter
 * @since 2.0.0
 */
public class CommandResult {

    public enum Result {
        NO_SUB_COMMAND_FOUND,
        NO_PERMISSION,
        WRONG_USAGE,
        SUCCESS,
    }

    private final SubCommand command;
    private final Result result;

    private CommandResult(SubCommand command, Result result) {
        this.command = command;
        this.result = result;
    }

    /**
     * Returns the command that was executed.
     *
     * @return The command that was executed.
     */
    public SubCommand getCommand() {
        return this.command;
    }

    /**
     * Returns the result of the command execution.
     *
     * @return The result of the command execution.
     */
    public Result getResult() {
        return this.result;
    }

    /**
     * Returns a CommandResult with the result {@link Result#NO_SUB_COMMAND_FOUND}.
     *
     * @return The CommandResult.
     */
    public static CommandResult noSubCommandFound() {
        return new CommandResult(null, Result.NO_SUB_COMMAND_FOUND);
    }

    /**
     * Returns a CommandResult with the result {@link Result#NO_PERMISSION}.
     *
     * @param command The command that was executed.
     * @return The CommandResult.
     */
    public static CommandResult noPermission(SubCommand command) {
        return new CommandResult(command, Result.NO_PERMISSION);
    }

    /**
     * Returns a CommandResult with the result {@link Result#WRONG_USAGE}.
     *
     * @param command The command that was executed.
     * @return The CommandResult.
     */
    public static CommandResult wrongUsage(SubCommand command) {
        return new CommandResult(command, Result.WRONG_USAGE);
    }

    /**
     * Returns a CommandResult with the result {@link Result#SUCCESS}.
     *
     * @param command The command that was executed.
     * @return The CommandResult.
     */
    public static CommandResult success(SubCommand command) {
        return new CommandResult(command, Result.SUCCESS);
    }
}