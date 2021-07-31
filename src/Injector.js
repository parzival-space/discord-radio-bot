let ffmpeg = require('ffmpeg-static');

// check if windows
if (process.platform == "win32") {
  console.log(`[Info]`, `Detected Windows. Injecting FFMPEG...`);

  process.env.PATH += `;${ffmpeg}`;
}

// check if linux
else if (process.platform == "linux") {
  console.log(`[Info]`, `Detected Linux. Injecting FFMPEG...`);

  process.env.PATH += `:${ffmpeg}`;
}

// check if mac (I cant verify this)
else if (process.platform == "darwin") {
  console.log(`[Info]`, `Detected Mac. Injecting FFMPEG...`);

  process.env.PATH += `:${ffmpeg}`;
}