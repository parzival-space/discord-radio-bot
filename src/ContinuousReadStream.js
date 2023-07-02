import {Duplex} from 'stream';

export class ContinuousReadStream extends Duplex {
    constructor(optional) {
        super(optional);
        this.dataBuffer = Buffer.alloc(0);
        this.isEnded = false;
    }

    _write(chunk, encoding, callback) {
        this.dataBuffer = Buffer.concat([this.dataBuffer, chunk]);
        this.pushData();
        callback();
    }

    _read() {
        this.pushData();
    }

    pushData() {
        while (this.dataBuffer.length > 0) {
            const chunk = this.dataBuffer.slice(0, 4); // Adjust the chunk size as needed
            this.dataBuffer = this.dataBuffer.slice(4); // Adjust the slice position as needed
            this.push(chunk);
        }

        if (this.isEnded && this.dataBuffer.length === 0) {
            this.push(null); // Signal the end of the stream
        }
    }

    end() {
        this.isEnded = true;
        if (this.dataBuffer.length === 0) {
            this.push(null); // Signal the end of the stream if no more data in the buffer
        }
    }
}