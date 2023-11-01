# Discord Radio Bot

> [!IMPORTANT]
> You are currently viewing the Java rewrite of the bot. 
> I am not done yet, and some features are missing. 
> If you are looking for the old Node.js-based version, click <a href="//github.com/parzival-space/discord-radio-bot/tree/main-old-node">here</a>.

Plays radio streams directly within your Discord server.
This bot has no commands; it's for playing radio streams only.
You can specify your own radio stream in the config.
<br>

## How to
### Requirements
You need the following:

1. Create a new Bot User:
   First, you need to create a new bot account. <br />
   Head over to the <a href="//discord.com/developers">Discord Developer Portal</a>, create a new bot instance, and get the bot token.
2. A working JRE setup:
   If you do not have one already, get it here:  <a href="//adoptopenjdk.net/releases.html">AdoptOpenJDK</a>

### Running the Bot
Download the latest <a href="//github.com/parzival-space/discord-radio-bot/releases">release</a>.

Then you have to create a file called ``application.properties`` in the same directory as the bot (the jar file you you just downloaded).
Fill it like so:
```properties
discord.token=<<your token>>
discord.channel=<<your channel id>>
stream.url=<<your url>>
```

And run it: ``java -jar radiobot.jar``
