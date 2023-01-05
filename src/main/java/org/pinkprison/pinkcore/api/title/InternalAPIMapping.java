package org.pinkprison.pinkcore.api.title;

enum InternalAPIMapping {
    REFLECTION(ReflectionTitleAPI.class, MinecraftVersion.v1_12),
    BUKKIT(BukkitTitleAPI.class, MinecraftVersion.v1_17),
    ;
    private final Class<? extends InternalTitleAPI> apiClass;
    private final MinecraftVersion maxVersion;

    InternalAPIMapping(Class<? extends InternalTitleAPI> apiClass, MinecraftVersion maxVersion) {
        this.apiClass = apiClass;
        this.maxVersion = maxVersion;
    }

    static InternalTitleAPI create() {
        MinecraftVersion version = MinecraftVersion.get();
        for (InternalAPIMapping thisOne : values()) {
            if (version.isLessThanOrEqualTo(thisOne.maxVersion)) {
                try {
                    return thisOne.apiClass.newInstance();
                } catch (InstantiationException | IllegalAccessException ignored) {}
            }
        }
        return null;
    }
}
