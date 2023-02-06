package org.pinkprison.pinkcore.api.hooks.exceptions;

/**
 * Exception thrown when a hook is not enabled
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 * @since 2.0.0
 */
public final class HookNotEnabledException extends RuntimeException {

    /**
     * Constructs a new HookNotEnabledException with the specified detail message.
     *
     * @param message the detail message.
     */
    public HookNotEnabledException(String message) {
        super(message);
    }
}