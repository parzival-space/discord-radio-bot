import http from 'http';
import https from 'https';
import {IcecastMetadataReader} from "icecast-metadata-js";
import {EventEmitter} from 'events';
import {ContinuousReadStream} from "./ContinuousReadStream.js";
import config from '../config.json' assert { "type": "json" };
import packageJson from '../package.json' assert { "type": "json" };

export class IceParser extends EventEmitter {
  #supportsMetadata = {
    icy: true,
    ogg: true
  }

  constructor(stream) {
    super();
    const audioStream = new ContinuousReadStream({ highWaterMark: config.stream.bufferSize });

    const request = this.#autodetectProtocol(stream, response => {
      // parse metadata
      const reader = new IcecastMetadataReader({
        icyMetaInt: parseInt(response.headers["Icy-MetaInt"]),
        metadataTypes: Object.keys(this.#supportsMetadata),
        icyCharacterEncoding: config.stream.characterEncoding,

        onMetadata: metadata => this.emit("title", metadata.metadata.StreamTitle),
        onMetadataFailed: (format) => {
          this.#supportsMetadata[format] = false;

          // check if at least one metadata format is available
          if (Object.values(this.#supportsMetadata).filter(v => v === true).length === 0) this.emit("empty");
        },
        onStream: streamChunk => {
          audioStream.push(streamChunk.stream);
          this.emit("stream", audioStream);
        }
      });

      response.on('end', () => this.emit("end", "Connection closed."));

      response.on('data', data => {
        for (const chunk of reader.iterator(data)) {
          // do nothing, stream is getting handled
        }
      })
    })

    request.on('error', error => this.emit("error", error));

    request.setHeader('Icy-MetaData', '1');
    request.setHeader('User-Agent', `${packageJson.name} by ${packageJson.author.name}`);
    request.end()
  }

  #autodetectProtocol(url, responseCallback) {
    const requester = url.startsWith("https://") ? https : http;

    // connect
    return requester.request(url, responseCallback);
  }
}