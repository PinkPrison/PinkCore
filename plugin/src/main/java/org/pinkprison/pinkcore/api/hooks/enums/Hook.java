package org.pinkprison.pinkcore.api.hooks.enums;

/**
 * Enum for the hooks
 *
 * <p>
 *     This class is part of the PinkCore project.
 *     <a href="https://github.com/PinkPrison/PinkCore">PinkCore</a> is licensed under the MIT license.
 * </p>
 * @author WildTooth
 */
public enum Hook {
    VAULT(false),
    PLACEHOLDERAPI(false),
    ACTIONBAR(true);

    private final boolean isBuiltIn;

    /**
     * @param isBuiltIn Whether the hook is built into PinkCore or not.
     */
    Hook(boolean isBuiltIn) {
        this.isBuiltIn = isBuiltIn;
    }

    /**
     * @return Whether the hook is built into PinkCore or not.
     */
    public boolean isBuiltIn() {
        return this.isBuiltIn;
    }
}
