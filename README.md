# Discord Radio Bot

Plays radio streams directly inside your Discord server.<br />
This bot has no commands, its for playing radio streams only.<br />
You can specify your own radio stream in the config.<br>
<br>
<b>
  Important: The provided url MUST be a link to a DIRECT MEDIA STREAM. This means https://radioXYZ.fm is not a valid url!<br>
  Stream urls normaly look like https://play.radioXYZ.fm/source.mp3
</b>

<br />

<img src="https://i.imgur.com/lzF9Fr0.png" alt="demo">

<br />

## Installation

If you want to use this bot on your own server, you have to perform the following steps:

#### 1. Create A New Bot User

First you need to create a new bot account. <br />
Head over to the <a href="//discord.com/developers">Discord Developer Portal</a>, create a new bot instance and get the bot token.

### 2. Clone this repository
```bash
git clone https://github.com/malte-linke/discord-radio-bot.git
cd discord-radio-bot
```

### 3. Install the required dependencies
```bash
npm install yarn -g # if you don't have yarn installed
yarn install
```

### 4. Configure The Bot

You will find a configuration file (``config.json``) in the root of the repository.<br />
In there, you will have to make some changes:

```json
{
  "radio": {
    "stream": "YOUR_RADIO_URL",
    "volume": 10
  },

  "discord": {
    "token": "YOUR_BOT_TOKEN",
    "channel": "YOUR_CHANNEL_ID"
  }
}
```

<details>
  <summary>Example Configuration</summary>

  ```json
  {
    "radio": {
      "stream": "https://play.radioXYZ.fm/source.mp3",
      "volume": 10
    },
    "discord": {
      "token": "NjY2MTI0MTU5NTI0Mjc0MjEx.XhvmoQ.aAeFrhScOeeAyk5j9atkZtxTG0I",
      "channel": "370198377088155648"
    }
  }
  ```
</details>

### 5. Optional: Install The App As System-Service

If you want to start the bot on system startup automatically, you can use the following command.<br />
To learn more, head over to <a href="//github.com/zapty/forever-service">forever-service</a>.

```bash
yarn add forever forever-service -g
forever-service install discord-radio-bot --script src/Main.js
```

This will install a system-service, with the name 'discord-radio-bot', that will run the bot.<br />
You can remove the service using ``forever-service delete discord-radio-bot``.

<br />

## Librarys Used

I used the following librarys in this project:

- <a href="//github.com/abalabahaha/eris">Eris</a> by <a href="//github.com/abalabahaha">abalabahaha</a>
- <a href="//github.com/ghaiklor/icecast-parser">icecast-parser</a> by <a href="//github.com/ghaiklor">ghaiklor</a>
- <a href="//github.com/malte-linke/ffmpeg-inject">ffmpeg-inject</a> by <a href="//github.com/malte-link">me</a>


