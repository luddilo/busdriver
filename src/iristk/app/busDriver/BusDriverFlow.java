package iristk.app.busDriver;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;
import java.util.concurrent.Callable;

public class BusDriverFlow extends iristk.flow.Flow {

	private Callable nextStop;
	private Record order;

	private void initVariables() {
		order = asRecord(new Record());
	}

	public Record getOrder() {
		return this.order;
	}

	public void setOrder(Record value) {
		this.order = value;
	}

	public Callable getNextStop() {
		return this.nextStop;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("order")) return this.order;
		if (name.equals("nextStop")) return this.nextStop;
		return null;
	}


	public BusDriverFlow(Callable nextStop) {
		this.nextStop = nextStop;
		initVariables();
	}

	@Override
	public State getInitialState() {return new Start();}


	public class Start extends Dialog implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 11
			try {
				EXECUTION: {
					int count = getCount(1642534850) + 1;
					incrCount(1642534850);
					iristk.flow.DialogFlow.say state0 = new iristk.flow.DialogFlow.say();
					state0.setText("Howdy, welcome to the robot bus!");
					if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 11, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.listen state1 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state1, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 11, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 14
					flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 14, 23)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 11, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Dialog extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 20
			try {
				count = getCount(399573350) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:order")) {
						incrCount(399573350);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 21
							order.adjoin(asRecord(event.get("sem:order")));
							// Line: 24
							CheckOrder state2 = new CheckOrder();
							flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 24, 36)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 20, 66));
			}
			// Line: 26
			try {
				count = getCount(1334729950) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:nextStop")) {
						incrCount(1334729950);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state3 = new iristk.flow.DialogFlow.say();
							state3.setText(concat("The next stop is", getNextStop().call()));
							if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 26, 69)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 26, 69));
			}
			// Line: 29
			try {
				count = getCount(1347137144) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:stopsAt")) {
						incrCount(1347137144);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 30
							if (asString(event.get("sem:stopsAt")) == "yes" && asDouble(event.get("conf")) > 0.7) {
								iristk.flow.DialogFlow.say state4 = new iristk.flow.DialogFlow.say();
								state4.setText("Yes");
								if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 30, 102)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 33
							} else {
								iristk.flow.DialogFlow.say state5 = new iristk.flow.DialogFlow.say();
								state5.setText("No");
								if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 30, 102)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 29, 62));
			}
			// Line: 37
			try {
				count = getCount(1212899836) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:numbers")) {
						incrCount(1212899836);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 42
							order.putIfNotNull("tickets", event.get("sem:numbers"));
							// Line: 43
							CheckOrder state6 = new CheckOrder();
							flowThread.gotoState(state6, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 43, 30)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 37, 68));
			}
			// Line: 45
			try {
				count = getCount(1285044316) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:exit")) {
						incrCount(1285044316);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 46
							Exit state7 = new Exit();
							flowThread.gotoState(state7, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 46, 30)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 45, 65));
			}
			// Line: 48
			try {
				count = getCount(1811075214) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1811075214);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state8 = new iristk.flow.DialogFlow.say();
						state8.setText("Sorry, I didn't get that. You can ask about tickets or stops.");
						if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 48, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 50
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 50, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 48, 42));
			}
			// Line: 52
			try {
				count = getCount(1407343478) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(1407343478);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state9 = new iristk.flow.DialogFlow.say();
						state9.setText("Sorry, I didn't hear anything");
						if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 52, 44)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 54
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 54, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 52, 44));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class CheckOrder extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 59
			try {
				EXECUTION: {
					int count = getCount(2121744517) + 1;
					incrCount(2121744517);
					// Line: 60
					log(order.toStringIndent());
					// Line: 61
					if (!order.has("destination")) {
						// Line: 62
						RequestDestination state10 = new RequestDestination();
						flowThread.gotoState(state10, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 62, 39)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 64
					if (!order.has("tickets") OR asInteger(order.get("tickets")) == 0) {
						// Line: 65
						RequestQuantity state11 = new RequestQuantity();
						flowThread.gotoState(state11, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 65, 36)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 67
					Pay state12 = new Pay();
					flowThread.gotoState(state12, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 67, 23)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 59, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class RequestDestination extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 72
			try {
				EXECUTION: {
					int count = getCount(517938326) + 1;
					incrCount(517938326);
					iristk.flow.DialogFlow.prompt state13 = new iristk.flow.DialogFlow.prompt();
					state13.setText("Where do you want to go?");
					if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 72, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 74
					CheckOrder state14 = new CheckOrder();
					flowThread.gotoState(state14, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 74, 30)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 72, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class RequestQuantity extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 79
			try {
				EXECUTION: {
					int count = getCount(425918570) + 1;
					incrCount(425918570);
					iristk.flow.DialogFlow.prompt state15 = new iristk.flow.DialogFlow.prompt();
					state15.setText("How many tickets do you want?");
					if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 79, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 81
					CheckOrder state16 = new CheckOrder();
					flowThread.gotoState(state16, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 81, 30)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 79, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Pay extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 86
			try {
				EXECUTION: {
					int count = getCount(204349222) + 1;
					incrCount(204349222);
					iristk.flow.DialogFlow.say state17 = new iristk.flow.DialogFlow.say();
					state17.setText(concat("Please pay in the counter over there. It will be", asInteger(order.get("tickets")) * 50, "kronor for", asInteger(order.get("tickets")), "tickets to", asString(order.get("destination"))));
					if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 86, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.say state18 = new iristk.flow.DialogFlow.say();
					state18.setText("Enjoy the ride!");
					if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 86, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 94
					order = new Record();
					// Line: 97
					Start state19 = new Start();
					flowThread.gotoState(state19, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 97, 25)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 86, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Exit extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 102
			try {
				EXECUTION: {
					int count = getCount(32374789) + 1;
					incrCount(32374789);
					iristk.flow.DialogFlow.say state20 = new iristk.flow.DialogFlow.say();
					state20.setText("Byebye");
					if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 102, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 104
					System.exit(0);
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 102, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
