package org.pinkprison.pinkcore.api.command.exceptions;

public class WrongCommandUsageException extends Exception{

    public WrongCommandUsageException(String message) {
        super(message);
    }
}