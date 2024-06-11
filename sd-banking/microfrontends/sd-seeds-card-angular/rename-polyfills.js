const fs = require('fs');
const path = require('path');

const distPath = path.join(__dirname, 'dist/sd-seeds-card-angular');
const files = fs.readdirSync(distPath);

files.forEach(file => {
  if (file.startsWith('polyfills')) {
    fs.renameSync(
      path.join(distPath, file),
      path.join(distPath, '0-' + file)
    );
  }
});

console.log('Polyfills file renamed successfully.');
