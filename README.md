# Discord Radio Bot

Plays radio streams directly within your Discord server.
This bot has no commands; it's for playing radio streams only.
You can specify your own radio stream in the config.
<br>

## Supported Platforms
Discord uses the [Discord Audio & Video End-to-End Encryption (DAVE)](https://daveprotocol.com/) protocol which requires
the use of native libraries to work. This bot uses the [JDAVE](https://github.com/MinnDevelopment/jdave) and 
[udpqueue.rs](https://github.com/MinnDevelopment/udpqueue.rs) to support this protocol.
Since these libraries are native, they only support certain platforms. 

This bot is currently only supported on the following platforms:

| Platform | Architecture      |
|----------|-------------------|
| Linux    | x86_64, aarch64   |
| Windows  | x86_64            |
| MacOS    | Darwin (Untested) |


## How to

### Requirements

You need the following:

1. Create a new Bot User:
   First, you need to create a new bot account. <br />
   Head over to the <a href="//discord.com/developers">Discord Developer Portal</a>, create a new bot instance, and get
   the bot token.
2. A working JRE setup:
   If you do not have one already, get it here:  <a href="//adoptopenjdk.net/releases.html">AdoptOpenJDK</a>

### Running the Bot

Download the latest <a href="//github.com/parzival-space/discord-radio-bot/releases">release</a>.

Then you have to create a file called ``application.properties`` in the same directory as the bot (the jar file you you
just downloaded).
Fill it like so:

```properties
discord.token=<<your token>>
discord.channel=<<your channel id>>
stream.url=<<your url>>
```

And run it: ``java -jar radiobot.jar``
