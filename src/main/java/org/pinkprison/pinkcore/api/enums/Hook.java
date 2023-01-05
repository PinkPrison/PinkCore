package org.pinkprison.pinkcore.api.enums;

public enum Hook {
    VAULT(false),
    PLACEHOLDERAPI(false),
    ACTIONBAR(true)
    ;

    private final boolean isBuiltIn;

    /**
     * @param paramBoolean Whether the hook is built into PinkCore or not.
     */
    Hook(boolean paramBoolean) {
        this.isBuiltIn = paramBoolean;
    }

    /**
     * @return Whether the hook is built into PinkCore or not.
     */
    public boolean isBuiltIn() {
        return isBuiltIn;
    }
}
