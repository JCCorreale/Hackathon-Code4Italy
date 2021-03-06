// Generated by CoffeeScript 1.7.1
var e, fs, inputFilename, k, n, outputFilename, res, source, v, _i, _len, _ref;

fs = require('fs');

if (process.argv.length > 2) {
  inputFilename = process.argv[2];
} else {
  inputFilename = 'data.json';
}

if (process.argv.length > 3) {
  outputFilename = process.argv[3];
} else {
  outputFilename = 'data-out.json';
}

source = require(inputFilename);

res = [];

_ref = source.results.bindings;
for (_i = 0, _len = _ref.length; _i < _len; _i++) {
  e = _ref[_i];
  n = {};
  for (k in e) {
    v = e[k];
    n[k] = v.value;
  }
  res.push(n);
}

fs.writeFile(outputFilename, JSON.stringify(res));
