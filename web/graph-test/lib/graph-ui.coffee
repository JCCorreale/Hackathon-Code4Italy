#TODO bind eventi automa 				(start, stop, transition)
#TODO evidenziare stato attivo 			(class: .state-current)
#TODO evidenziare stato di accettazione (class: .state-final)
#TODO evidenziare stato iniziale 		(class: .state-initial)

class GraphUtils
	@resolveId = (model) ->
		nodesIndex = {}
		for n in model.nodes
			nodesIndex[n.id] = n
		
		for l in model.links
			l.source = nodesIndex[l.source]
			l.target = nodesIndex[l.target]

		return
		

class SvgUtils
	@linkArc = (d) ->
		dx = d.target.x - d.source.x
		dy = d.target.y - d.source.y
		dr = Math.sqrt(dx * dx + dy * dy)
		return "M#{d.source.x}, #{d.source.y}A#{dr},#{dr} 0 0,1 #{d.target.x},#{d.target.y}"

	@transform = (d) -> "translate(#{d.x},#{d.y})"

	@defineArrow = (svg) ->
		return if @arrowDefined
		#TODO generalizzare
		svg.append("defs").append "marker"
		    .attr "id", "arrow"
		    .attr "viewBox", "0 -5 10 10"
		    .attr "refX", 20
		    .attr "refY", -1.5
		    .attr "markerWidth", 6
		    .attr "markerHeight", 6
		    .attr "orient", "auto"
		  .append "path"
		    .attr("d", "M0,-5L10,0L0,5");
		@arrowDefined = true
		return


class Drawer
	_doNothing = -> 

	constructor: (wrapper, injectFn, updateFn) ->
		if typeof wrapper is 'string'
			# setup single param overload
			wrapper = 
				wrapper: 	wrapper  ? 'g',
				injectFn: 	injectFn ? _doNothing,
				updateFn: 	updateFn ? _doNothing

		@wrapper = wrapper.wrapper

		@update  = wrapper.updateFn
		# @update = (selection, data) ->
		# 	wrapper.updateFn.call(@, selection)
		# 	return

		@injectInto = (target, data, layout) ->
			selection = target.append 'g'
			  .selectAll @wrapper
			  .data data
			  .enter()
			  .append @wrapper
			wrapper.injectFn.call(@, selection, target, layout)
			
			return selection

		return @

NodeDrawer = new Drawer(
	wrapper: 'g'
	updateFn: (selection) -> 
		# subselections update
		@nodesSelection.attr  'transform', SvgUtils.transform
		@labelsSelection.attr 'transform', SvgUtils.transform
		return
	injectFn: (selection, data, layout) -> 
		#node
		@nodesSelection = selection.append 'circle'
		  .attr 'r', 8
		  .attr 'class', 'node'
		  .call layout.drag # draggable

		#label
		@labelsSelection = selection.append 'text'
		   .attr 'x', 15
		   .attr 'y', '.30em'
		   .text (d) -> d.name
		   .attr 'class', 'node-label'
		return
)

#TODO generalizzabile (parametri: marker, tipo link)
LinkDrawer = new Drawer(
	wrapper: 'g'
	updateFn: () ->
		@linksSelection.attr 'd', SvgUtils.linkArc
		return
	injectFn: (selection) ->
		#link
		@linksSelection = selection.append 'path'
		  .attr 'marker-end', 'url(#arrow)'
		  .attr 'class', 'link'
		return
)


class GraphUi
	@defaults = 
		width:			 960,
		height:			 500,
		linkDistance:	 60,
		charge:			-300

	_tick = (evt) ->
		@nodeDrawer.update()
		@linkDrawer.update()
		return

	constructor: (target, model, nodeDrawer, linkDrawer, opt = {}) ->
		_this = @

		_width			 = opt.width 		? GraphUi.defaults.width
		_height			 = opt.height 		? GraphUi.defaults.height
		_linkDistance	 = opt.linkDistance ? GraphUi.defaults.linkDistance
		_charge			 = opt.charge 		? GraphUi.defaults.charge

		@nodeDrawer = nodeDrawer ? NodeDrawer
		@linkDrawer = linkDrawer ? LinkDrawer

		#TODO 
		_guiModel = 
			nodes: model.nodes,
			links: model.links

		_layout = d3.layout.force()
		  .nodes(_guiModel.nodes)
		  .links(_guiModel.links)
		  .size([_width, _height])
		  .linkDistance(_linkDistance)
		  .charge(_charge)
		  .on('tick', -> _tick.call _this, @)

		_layout.linkStrength(opt.linkStrength) 		if opt.linkStrength?
		_layout.friction(opt.friction) 				if opt.friction?
		_layout.chargeDistance(opt.chargeDistance) 	if opt.chargeDistance?
		_layout.gravity(opt.gravity) 				if opt.gravity?

		#TODO check if target isnt an svg element itself
		_svg = target.append('svg')
		  .attr 'width',  _width
		  .attr 'height', _height

		SvgUtils.defineArrow(_svg)
		
		@linksSelection = @linkDrawer.injectInto(_svg, _guiModel.links)
		@nodesSelection = @nodeDrawer.injectInto(_svg, _guiModel.nodes, _layout)


		if opt.windowResizedEvent?
			opt.windowResizedEvent.addHandler ->
				_width  = parseInt(target.style('width'),  10)
				_height = parseInt(target.style('height'), 10)

				_svg
				  .attr 'width',  _width
				  .attr 'height', _height

				_layout.size [_width, _height]

				return



		@getNode = (id) -> @nodesSelection.filter((d) -> return d if d.id is id)

		_layout.start()

		return
