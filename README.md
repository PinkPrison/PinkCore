# PinkCore

A Core functionality plugin for projects made by the pinkprison development team

# API

### :exclamation: API Includes the following:

- An easier way to send actionbars and (sub)titles to players :tulip:

- Easy ways to create Items and Skulls primarily for GUI's :skull:

- Implements TriumpTeams Gui-System for easy use :eagle:

# CORE

### :exclamation: CORE Includes the following:

- Listeners to disable Weather events, Explosions and some World events 💥

- Functionality for cancelling crafting and placing of certain Items and Blocks :x:

- A Listener that (Hopefully) should fix a minecraft bug that lets players damage more than normal :heart:

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
