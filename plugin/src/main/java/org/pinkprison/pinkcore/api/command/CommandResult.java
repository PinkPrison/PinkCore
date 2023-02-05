package org.pinkprison.pinkcore.api.command;

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

    public SubCommand getCommand() {
        return this.command;
    }

    public Result getResult() {
        return this.result;
    }

    public static CommandResult noSubCommandFound() {
        return new CommandResult(null, Result.NO_SUB_COMMAND_FOUND);
    }

    public static CommandResult noPermission(SubCommand command) {
        return new CommandResult(command, Result.NO_PERMISSION);
    }

    public static CommandResult wrongUsage(SubCommand command) {
        return new CommandResult(command, Result.WRONG_USAGE);
    }

    public static CommandResult success(SubCommand command) {
        return new CommandResult(command, Result.SUCCESS);
    }
}