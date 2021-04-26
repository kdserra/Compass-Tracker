# Compass-Tracker
 Minecraft Spigot plugin that creates compasses that point to players.
 
 [Commands](#cmds) | [Screenshots](#screenshots) | [Config](#config) | [Download](#download) | [Install](#install) | [Disclaimer](#disclaimer) | [Build Information](#buildinfo) | [License](#license)
 
# <a name="cmds"></a>Commands

    /tracker - Show all the tracker commands.
    /tracker <playerToTrack> - Create a compass that tracks a player.
    /tracker <playerToTrack> <player> - Give a compass to a player that tracks a player.
    /tracker <playerToTrack> all - Give a compass to everyone that tracks a player.

# <a name="screenshots"></a>Screenshots

<p align="center">
  <img src="https://github.com/kdserra/Compass-Tracker/blob/main/thumbnails/help.png?raw=true"/>
</p>

Preview of usingDistanceTracking and usingCoordinateTracking enabled.

<p align="center">
  <img src="https://github.com/kdserra/Compass-Tracker/blob/main/thumbnails/all.png?raw=true"/>
</p>

Preview of usingDistanceTracking and usingCoordinateTracking disabled.

<p align="center">
  <img src="https://github.com/kdserra/Compass-Tracker/blob/main/thumbnails/name.png?raw=true"/>
</p>

Preview of usingDistanceTracking enabled.

<p align="center">
  <img src="https://github.com/kdserra/Compass-Tracker/blob/main/thumbnails/dist.png?raw=true"/>
</p>

Preview of usingCoordinateTracking enabled.

<p align="center">
  <img src="https://github.com/kdserra/Compass-Tracker/blob/main/thumbnails/cord.png?raw=true"/>
</p>

# <a name="config"></a>Config

The configuration file is located in `./plugins/CommandTracker/config.yml`

config.yml

    usingDistanceTracking: false
    usingCoordinateTracking: false


|   Configuration Option  |                                               Description                                              | Value          |
|:-----------------------:|:------------------------------------------------------------------------------------------------------:|----------------|
|  usingDistanceTracking  | Provides the distance between the tracked player and the compass holder.  Example: Tracking: Notch 50m | true, or false |
| usingCoordinateTracking |           Provides the coordinate of the tracked player.  Example: Tracking: Notch (14,56,10)          | true, or false |

# <a name="download"></a>Download

Navigate to the [releases tab](https://github.com/kdserra/Compass-Tracker/releases) and download the latest version of the plugin for your Minecraft version.

# <a name="install"></a>Install

 1. Create a spigot, or spigot-plugin compatible server.
 2. [Download](#download) the plugin.
 3. Navigate to the root directory of the server.
 4. Drag the plugin into `./plugins`
 5. (Optional) [Configure](#config) the plugin.
 6. Run the server.

# <a name="download"></a>Disclaimer

Only Minecraft version 1.16.5 is supported.

# <a name="buildinfo"></a>Build Information

Built with IntelliJ IDEA Community Edition 2020.3
Built using [Minecraft Development](https://plugins.jetbrains.com/plugin/8327-minecraft-development) plugin for IntelliJ
Built for Minecraft version 1.16.5

To compile the binary:

 - Navigate to the Maven Panel
 - Open Lifecycle
 - Double-click compile
 - Open Plugins
 - Open Jar
 - Double-click jar:jar
 - Compiled binary should be located in `./target/`

<p align="center">
  <img src="https://github.com/kdserra/Compass-Tracker/blob/main/thumbnails/maven-panel.png?raw=true"/>
</p>

# <a name="license"></a>License

**Compass-Tracker** is licensed under the MIT License.  Dependencies are under their respective licenses.
