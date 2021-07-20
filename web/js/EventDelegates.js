function EventDelegates() {
    this.events = {};

    this.on = function (eventName, fn) {
        if (eventName in this.events) {
            var evt = this.events.eventName || [];
            evt.push(fn);
        } else {
            var a = [];
            a.push(fn);
            this.events[eventName] = a;
        }
    }

    this.off = function (eventName, fName) {
        if (eventName in this.events) {
            var evt = this.events.eventName;
            evt.splice(evt.indexOf(fName), 1)
        }
    }

    this.emit = function (eventName) {
        var evt = this.events[eventName] || [];
        for (var i = 0; i < evt.length; i++) {
            var fn = evt[i];
            fn instanceof Function && fn.apply(this, [].slice.call(arguments, 1));
        }
    }
}