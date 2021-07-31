const { Parser } = require('icecast-parser');
const EventEmitter = require('events');
const { version } = require('../package.json');

module.exports = class IceParser extends EventEmitter {
  constructor(stream) {
    super();

    let showedEmptyWarning = false;

    // configure icecast parser
    let parser = new Parser({
      autoUpdate: true,
      emptyInterval: 5 * 60,
      errorInterval: 10 * 60,
      keepListen: true,
      metadataInterval: 5,
      notifyOnChangeOnly: true,
      url: stream,
      useragent: `discord-radio-bot/${version}`
    });

    // listen for metadata
    parser.on("metadata", metadata => {
      let title = metadata.get("StreamTitle");
      this.emit("title", title);
    });

    // listen for stream data
    parser.on("stream", stream => this.emit("stream", stream));

    // listen for no meta
    parser.on("empty", () => {
      if (!showedEmptyWarning) {
        this.emit("empty");
        showedEmptyWarning = true;
      }
    });

    // listen for error
    parser.on("error", error => this.emit("error", error));
  }
}