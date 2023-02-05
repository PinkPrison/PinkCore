package org.pinkprison.pinkcore.api.command.exceptions;

public class NoSuchSubCommandException extends Exception {

    public NoSuchSubCommandException(String message) {
        super(message);
    }
}