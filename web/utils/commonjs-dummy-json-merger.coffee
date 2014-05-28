fs = require 'fs'


merger = (attiInfoFileName, attiTermsFileName, outputPath) ->
	if typeof attiInfoFileName is 'string'
		source1 = require attiInfoFileName
	else
		source1 = attiInfoFileName

	if typeof attiTermsFileName is 'string'
		source2 = require attiTermsFileName
	else
		source2 = attiTermsFileName

	res = []
	for e1 in source1
		for e2 in source2
			if e1.atto is e2.iri
				e1.terms = e2.terms

	res = source1
	fs.writeFile(outputPath + "atti.json", JSON.stringify(res), ->)
	return

module.exports = merger
