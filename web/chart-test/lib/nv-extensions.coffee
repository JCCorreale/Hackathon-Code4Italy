# dependencies: [nv, Evt]

(() ->
	evt = new Evt()
	nv.utils.events = {}
	nv.utils.events.windowResize = { addHandler: evt.addHandler, removeHandler: evt.removeHandler }
	nv.utils.windowResize(evt.emit)
)()
