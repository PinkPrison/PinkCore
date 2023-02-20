# PinkCore
[![](https://jitpack.io/v/PinkPrison/PinkCore.svg)](https://jitpack.io/#PinkPrison/PinkCore)
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FPinkPrison%2FPinkCore&count_bg=%23FF00F6&title_bg=%23555555&icon=java.svg&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)
[![Discord](https://img.shields.io/discord/1072269834211049542?color=7289DA&label=Discord&logo=Discord&logoColor=white)](https://discord.gg/CxNVKtyKhr)
![GitHub](https://img.shields.io/github/license/PinkPrison/PinkCore?color=blue&label=License&logo=GitHub)

En kernefunktionalitets plugin for projekter lavet af PinkPrison's udviklingshold.

# Indholdsfortegnelse

- [API](#api)
- [Core](#core)
- [For Udviklere](#for-udviklere)
- [Eksempler](#eksempler)

# API

### :exclamation: API'en indeholder følgende:

- En nemmere måde at sende actionbars og (sub)titles til spillere

- Nemme måder at oprette Items og Skulls

- Implementerer TriumphTeams [Gui-System](https://github.com/TriumphTeam/triumph-gui) for nem brug

- Scoreboard system til at oprette Scoreboards

- Kommando system til at oprette kommandoer

- Støtte af PlaceholderAPI og Vault

- Forskellige hjælpefunktioner

# Core

### :exclamation: Core funktionaliteterne indeholder følgende

- Listeners til at deaktivere Weather events, Explosions og nogle World events 💥

- Funktionalitet til at annullere crafting og placering af visse Items og Blocks :x:

- En Listener, der fikser en Minecraft-fejl, der tillader spillere at skade mere end normalt :heart:

- En fuldt funktionel CommandBlocker, der annullerer angivne kommandoer (fuldt konfigurerbar) hvis afsenderen af kommandoen ikke er indeholdt i config.yml 👮

- Automatisk broadcast af beskeder til serveren, beskederne kan endda have flere linjer 🤓

- Til automatisk broadcast kan du også vælge specifikke beskeder i config.yml, så de angivne kun vil blive broadcastet hvis aktiveret 📣

# For Udviklere

### :exclamation: Implementation:

``` apache maven
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

``` apache maven
<dependencies>
    <dependency>
        <groupId>com.github.PinkPrison.PinkCore</groupId>
        <artifactId>packaging</artifactId>
        <version>{VERSION}</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

- Husk at dit plugins `plugin.yml` skal `(soft)depends` dette plugin `PinkCore`

### :exclamation: Version Information:

*Link til nyeste releases: https://github.com/PinkPrison/PinkCore/releases/*

```yaml
╔════════════════════════════════════════════════════════════════════╗
║                               PINKCORE                             ║
╠══════════════╦═════════════════╦════════════════╦══════════════════╣
║   Version    ║   Pre-Release   ║   Updateable   ║   Downloadable   ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║      ?       ║        ?        ║        ?       ║         ?        ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║ 2.0.0-alpha1 ║        Yes      ║       No       ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║    1.4.0     ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║    1.3.6     ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║    1.3.5     ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║    1.3.4     ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║    1.3.3     ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║    1.3.2     ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║    1.3.1     ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║    1.3.0     ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║    1.2.1     ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║     1.2      ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║     1.1      ║        No       ║       Yes      ║        Yes       ║
╠══════════════╬═════════════════╬════════════════╬══════════════════╣
║     1.0      ║        No       ║       Yes      ║        No        ║
╚══════════════╩═════════════════╩════════════════╩══════════════════╝
```

# Eksempler

Eksempler vil komme på et senere tidspunkt når version 2.0.0 er blevet udgivet.
