<h1>Requirements</h1>

Just some steps you need to take before you can install the bot.

<h2>Create A New Bot User</h2>

First you need to create a new bot account. <br />
Head over to the <a href="//discord.com/developers">Discord Developer Portal</a>, create a new bot instance and get the bot token.

<h2>Clone this repository</h2>

```bash
git clone https://github.com/malte-linke/discord-radio-bot.git
cd discord-radio-bot
```

<h2>Install the required dependencies</h2>

```bash
# if you don't have yarn installed
npm install yarn -g

# install the dependencies
yarn install
```

<h2>Configure The Bot</h2>

You will find a configuration file (`config.json`) in the root of the repository.<br />
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

<h1>Install</h1>

Now you can continue to install the bot in whatever way you like.

- Using <a href="docker.md">Docker</a> <b>(Recomended)</b>
- Using <a href="service.md">System Service</a> <b>(Linux only)</b>
