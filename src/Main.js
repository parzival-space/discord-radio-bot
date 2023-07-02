import { Client } from 'eris';
import { IceParser } from './IceParser.js';
import config from '../config.json' assert { "type": "json" };

import './Logo.js';
import 'ffmpeg-inject';

const client = new Client(config.discord.token);
client.connect().then();

// register err event listener
client.on("error", err => console.error(`[Error]`, `Unexpected error: ${err}`));

// register ready event listener
client.once("ready", () => {
  console.log(`[Info]`, `Client connection to Discord established.`);

  // target server
  let server = client.guilds.values().next().value;
  console.log(`[Info]`, `Server: ${server.name} (${server.id})`);

  // target channel
  let channel = server.channels.get(config.discord.channel);
  console.log(`[Info]`, `Channel: ${channel.name} (${channel.id})`);

  // connect to channel
  channel.join().then(connection => {
    // start ice parser
    let radio = new IceParser(config.radio.stream);
    console.log(`[Info]`, `Awaiting data...`);

    // apply title data
    radio.on('title', title => {
      console.log(`[Info]`, `Now playing: ${title}`);
      client.editStatus("online", { name: title, type: 2 })
    });

    // play stream data
    let i = 0;
    radio.once('stream', stream => {

      // start stream and set volume
      connection.play(stream, { inlineVolume: true });
      connection.setVolume(config.radio.volume / 100);
    });

    // warn user if stream does not support icecast
    radio.on('empty', () => {
      console.log(`[Warning]`, `Stream is not providing metadata. This is not really a problem as the playback will not fail.`);

      client.editStatus("online", { name: 'Music', type: 2 })
      connection.setVolume(config.radio.volume / 100);
    });

    // display errors
    radio.on('error', err => console.error(`[Error]`, `Failed to read stream: ${err}`));
  });
});