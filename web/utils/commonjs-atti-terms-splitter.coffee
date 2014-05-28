fs = require 'fs'


splitter = (input, outputPath = './') ->

	if typeof input is 'string'
		source = require input
	else
		source = input

	res = []
	for e1, i in source
		res.push id: i, atto: e1.iri
		fs.writeFile(outputPath + "atto-#{i}.json", JSON.stringify({atto: e1.iri, terms: e1.terms}), ->)

	fs.writeFile(outputPath + 'atti-index.json', JSON.stringify(res), ->)
	return

module.exports = splitter
