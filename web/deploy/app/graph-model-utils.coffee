GraphModelUtils = {}
GraphModelUtils.attoToGraph = (atto) ->
	model = {
		nodes: [{id: 0, name: "Atto", type: "atto"}]
		links: []
	}
	i = 0
	for term in atto.terms
		n = id: i++, name: term, type: "term"
		model.nodes.push n
		model.links.push source: model.nodes[0], target: n

	return model

GraphModelUtils.clusterToGraph = (cluster) ->
	model = {
		nodes: [{id: 0, name: "Tema", type: "atto"}]
		links: []
	}
	i = 0
	for term in cluster.terms
		n = id: i++, name: term, type: "term"
		model.nodes.push n
		model.links.push source: model.nodes[0], target: n

	return model
