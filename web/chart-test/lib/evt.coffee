# Evt class
Evt = () -> 
	_handlers = []

	@emit = (e) ->
		for handler in _handlers
			handler(e)
		return

	@addHandler = (handler) -> 
		_handlers.push(handler)
		return

	@removeHandler = (handler) ->
		i = _handlers.indexOf(handler)
		if i > -1
			_handlers.splice(i, 1)
		return

	return @

Evt.toPublicEvt = (evt) -> { addHandler: evt.addHandler, removeHandler: evt.removeHandler }
