fs = require 'fs'

if process.argv.length > 2
	inputFilename1 = process.argv[2]
else
	inputFilename1 = './atti-info.json'


if process.argv.length > 3
	inputFilename2 = process.argv[3]
else
	inputFilename2 = './atti-terms.json'

if process.argv.length > 4
	outputFilename = process.argv[4]
else
	outputFilename = './atti.json'


source1 = require inputFilename1
source2 = require inputFilename2

res = []
for e1 in source1
	for e2 in source2
		if e1.atto is e2.iri
			e1.terms = e2.terms

res = source1

fs.writeFile(outputFilename, JSON.stringify(res))
