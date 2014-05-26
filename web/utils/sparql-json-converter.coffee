fs = require 'fs'

if process.argv.length > 2
	inputFilename = process.argv[2]
else
	inputFilename = 'data.json'

if process.argv.length > 3
	outputFilename = process.argv[3]
else
	outputFilename = 'data-out.json'

source = require inputFilename

res = []
for e in source.results.bindings
	n = {}
	for k, v of e
		n[k] = v.value
	res.push n

fs.writeFile(outputFilename, JSON.stringify(res))
