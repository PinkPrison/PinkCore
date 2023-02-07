# PinkCore
[![](https://jitpack.io/v/PinkPrison/PinkCore.svg)](https://jitpack.io/#PinkPrison/PinkCore)
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FPinkPrison%2FPinkCore&count_bg=%23FF00F6&title_bg=%23555555&icon=java.svg&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)
[![Discord](https://img.shields.io/discord/708090408201476126?color=7289DA&label=Discord&logo=Discord&logoColor=white)](https://discord.gg/CxNVKtyKhr)
![GitHub](https://img.shields.io/github/license/PinkPrison/PinkCore?color=blue&label=License&logo=GitHub)

A Core functionality plugin for projects made by the pinkprison development team

# API

### :exclamation: API Includes the following:

- An easier way to send actionbars and (sub)titles to players

- Easy ways to create Items and Skulls primarily for GUI's

- Implements TriumphTeams [Gui-System](https://github.com/TriumphTeam/triumph-gui) for easy use

- Various utillities

# CORE

### :exclamation: CORE Includes the following:

- Listeners to disable Weather events, Explosions and some World events 💥

- Functionality for cancelling crafting and placing of certain Items and Blocks :x:

- A Listener that fixes a minecraft bug that lets players damage more than normal :heart:

- A fully functional CommandBlocker that cancels specified commands (fully configurable) if the sender of the Command is not contained in the config.yml 👮

- Auto broadcasting of messages to the server, the messages can even have multiple lines 🤓

- For auto broadcasting you can also toggle specific messages in the config.yml so the ones specified only will be broadcasted if enabled 📣

# For Developers

### :exclamation: Implementation:

``` apache maven
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>com.github.PinkPrison.PinkCore</groupId>
        <artifactId>packaging</artifactId>
        <version>{VERSION}</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

- Make sure that the plugin.yml from your project (soft)depends on the PinkCore-VERSION.jar
