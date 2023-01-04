package org.pinkprison.pinkcore.core.enums;

public enum Hook {
    VAULT(false),
    PLACEHOLDERAPI(false),
    ACTIONBAR(true)
    ;

    private final boolean isBuiltIn;
    Hook(boolean paramBoolean) {
        this.isBuiltIn = paramBoolean;
    }

    public boolean isBuiltIn() {
        return isBuiltIn;
    }
}
