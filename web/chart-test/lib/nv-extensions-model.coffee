#dependencies: [nv, evt]

(() ->
	nv.utils.model = {}
	nv.utils.model.UpdatableSerie = (readonlySerie) ->
		_serie = JSON.parse(JSON.stringify(readonlySerie))
		_valuesChangedEvt = new Evt()
		_keyChangedEvt = new Evt()

		@valuesChanged = Evt.toPublicEvt(_valuesChangedEvt)
		@keyChanged = Evt.toPublicEvt(_keyChangedEvt)

		#TODO @addValues = (points) =>

		@addValue = (point) =>
			_serie.values.push point
			_valuesChangedEvt.emit(@)
			return

		@removeValue = (point) =>
			i = _serie.values.indexOf point
			if i > -1
				_serie.splice(i, 1)
				_valuesChangedEvt.emit(@)
			return

		@getValues = () -> _serie.values # readonly?

		@setKey = (key) =>
			if key isnt _serie.key
				_serie.key = key
				_keyChangedEvt.emit(@)
			return

		@getKey = () -> _serie.key

		@values = _serie.values
		@key = _serie.key

		return @
	UpdatableSerie = nv.utils.model.UpdatableSerie

	nv.utils.model.UpdatableAxisLabels = (readonlyAxisLabels) ->
		__this = []

		for l in readonlyAxisLabels
			__this.push l		

		_changedEvt = new Evt()
		__this.changed = Evt.toPublicEvt(_changedEvt)

		__this.addValue = (label) =>
			__this.push label
			_changedEvt.emit(@)
			return

		__this.removeValue = (label) =>
			i = __this.indexOf point
			if i > -1
				__this.splice(i, 1)
				_valuesChangedEvt.emit(@)
			return

		return __this
	UpdatableAxisLabels = nv.utils.model.UpdatableAxisLabels

	nv.utils.model.UpdatableModel = (readonlyModel) ->
		_changedEvt = new Evt()
		
		_model = series: [], xAxisLabels: [], yAxisLabels: []

		_triggerChangedEvt = () =>
			_changedEvt(@)
			return

		_addSerie = (s) ->
			if not(s instanceof UpdatableSerie)
				ms = new UpdatableSerie(s)
				ms.valuesChanged.addHandler _triggerChangedEvt
				ms.keyChanged.addHandler _triggerChangedEvt
			_model.series.push ms
			return


		for s in readonlyModel.series
			_addSerie(s)

		#TODO rivedere discorso label
		if readonlyModel.xAxisLabels?
			ml = new UpdatableAxisLabels(readonlyModel.xAxisLabels)
			ml.changed.addHandler _triggerChangedEvt
			_model.xAxisLabels = ml
		if readonlyModel.yAxisLabels?
			ml = new UpdatableAxisLabels(readonlyModel.yAxisLabels)
			ml.changed.addHandler _triggerChangedEvt
			_model.yAxisLabels = ml
		

		@changed = Evt.toPublicEvt(_changedEvt)

		@addSerie = (serie) =>
			_addSerie(serie)
			_changedEvt.emit(@)
			return

		@removeSerie = (serie) =>
			for s, i in _model.series
				if s.key is serie.key
					_model.series.splice i, 1
					_changedEvt.emit(@)
					break
			


			# i = _model.series.indexOf serie
			# if i > -1
			# 	_model.series.splice(i, 1)
			# 	_changedEvt.emit(@)
			return
		
		@series = _model.series
		@xAxisLabels = _model.xAxisLabels
		@yAxisLabels = _model.yAxisLabels

		return @
	UpdatableModel = nv.utils.model.UpdatableModel

)()
