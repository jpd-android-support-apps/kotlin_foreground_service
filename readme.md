This implements a simple foreground service

It is launched periodically from a timer task on the main activity, and can be launched from the button on that page as well

The background thread updates values that are propogated back to the via a flow, which updates the label on that activity as well.

The intent is that you write the code that does what you need on the service, it runs in the background, updating state as needed.  The main activity starts the service periodically to update as needed.

Let me know what else you'd like this to do.

John DeMastri
demastri@msoe.edu