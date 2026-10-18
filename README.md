## What is TimeChanger?
**TimeChanger** is a lightweight client-side mod that allows you to change the world time.

![Scrolling Time](https://cdn.modrinth.com/data/E8IhnBnt/images/ae0c215349be1de56c10c858912632094974af68.webp)

Available for **Fabric** and **NeoForge**.

### Features
* **Time Control**: Lock the time of day on your client to any value.
* **Time Scrolling**: Quickly adjust the time by holding a hotkey and using your mouse wheel.
* **Menu**: A built-in settings screen to toggle the override and pick the exact time. Open it from Mod Menu (Fabric), the Mods list (NeoForge), the command, or a keybind.

> Since **1.1**, [YACL](https://modrinth.com/mod/yacl) is no longer required. The menu is built in.

### Keybinds
* **Scroll Time (Default: M)**: Hold this key while scrolling the mouse wheel to move the time forward or back by 200 ticks per step.
* **Toggle Time (Default: N)**: Quickly enable or disable the time override.
* **Open Config (Default: unbound)**: Opens the settings menu. Set it in Controls.

### Commands and Usage
* `/timechanger menu`: Open the settings menu.
* `/timechanger set <0-23999>`: Set the time in ticks. This also turns the override on.
* `/timechanger toggle`: Enable or disable the time override.

Useful times: `0` sunrise · `6000` noon · `12000` sunset · `18000` midnight

### Requirements
* **Fabric**: [Fabric API](https://modrinth.com/mod/fabric-api). [Mod Menu](https://modrinth.com/mod/modmenu) is optional.
* **NeoForge**: No extra mods needed.
