fs = require 'fs'

if process.argv.length > 2
	inputFilename = process.argv[2]
else
	inputFilename = './atti-terms.json'


source1 = require inputFilename

res = []
for e1, i in source1
	res.push id: i, atto: e1.iri
	fs.writeFile("atto-#{i}.json", JSON.stringify({atto: e1.iri, terms: e1.terms}))

fs.writeFile('atti-index.json', JSON.stringify(res))
