<h1>Install using Docker (Recomended)</h1>

This Guide assumes you have a working Docker installation.<br>
Please make sure you followed the <a href="requirements.md">requirements</a>.

Running this bot using Docker is quite easy.
A working docker-compose file is included in the repository.

<h3>Using docker-compose</h3>

To start the bot, simply run:

```bash
docker-compose up
```

And to stop the bot:

```bash
docker-compose down
```

<h3>Building the docker image manually</h3>

This is actually not necessary, but if you want to build the docker image yourself, you can do so by running:

```bash
docker build -t discord-radio-bot .
```

And to start the bot:

```bash
docker run -d --name my-cool-radio-bot discord-radio-bot
```
