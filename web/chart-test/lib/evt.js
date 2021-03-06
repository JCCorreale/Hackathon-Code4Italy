// Generated by CoffeeScript 1.7.1
var Evt;

Evt = function() {
  var _handlers;
  _handlers = [];
  this.emit = function(e) {
    var handler, _i, _len;
    for (_i = 0, _len = _handlers.length; _i < _len; _i++) {
      handler = _handlers[_i];
      handler(e);
    }
  };
  this.addHandler = function(handler) {
    _handlers.push(handler);
  };
  this.removeHandler = function(handler) {
    var i;
    i = _handlers.indexOf(handler);
    if (i > -1) {
      _handlers.splice(i, 1);
    }
  };
  return this;
};

Evt.toPublicEvt = function(evt) {
  return {
    addHandler: evt.addHandler,
    removeHandler: evt.removeHandler
  };
};
