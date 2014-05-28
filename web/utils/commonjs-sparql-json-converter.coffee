fs = require 'fs'

converter = (input, outputFilename) ->
	if typeof input is 'string'
		source = require input
	else
		source = input

	res = []
	for e in source.results.bindings
		n = {}
		for k, v of e
			n[k] = v.value
		res.push n, 

	fs.writeFile(outputFilename, JSON.stringify(res), ->)
	return

module.exports = converter
