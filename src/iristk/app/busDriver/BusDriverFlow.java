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
			// Line: 12
			try {
				EXECUTION: {
					int count = getCount(1642534850) + 1;
					incrCount(1642534850);
					// Line: 13
					log(concat("Count =", count));
					// Line: 15
					if (count == 1) {
						iristk.flow.DialogFlow.say state0 = new iristk.flow.DialogFlow.say();
						state0.setText("Howdy, welcome to the robot bus!");
						if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 15, 35)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					iristk.flow.DialogFlow.prompt state1 = new iristk.flow.DialogFlow.prompt();
					state1.setText("How can I help?");
					if (!flowThread.callState(state1, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 12, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 19
					flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 19, 23)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 12, 18));
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
			// Line: 25
			try {
				count = getCount(1334729950) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:order")) {
						incrCount(1334729950);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 26
							order.adjoin(asRecord(event.get("sem:order")));
							// Line: 29
							CheckOrder state2 = new CheckOrder();
							flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 29, 36)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 25, 66));
			}
			// Line: 31
			try {
				count = getCount(1973336893) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:nextStop")) {
						incrCount(1973336893);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state3 = new iristk.flow.DialogFlow.say();
							state3.setText(concat("The next stop is", getNextStop().call()));
							if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 31, 69)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 31, 69));
			}
			// Line: 34
			try {
				count = getCount(1212899836) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:stopsAt")) {
						incrCount(1212899836);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 35
							if (asString(event.get("sem:stopsAt")) == "yes" && asDouble(event.get("conf")) > 0.7) {
								iristk.flow.DialogFlow.say state4 = new iristk.flow.DialogFlow.say();
								state4.setText("Yes");
								if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 35, 102)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 38
							} else {
								iristk.flow.DialogFlow.say state5 = new iristk.flow.DialogFlow.say();
								state5.setText("No");
								if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 35, 102)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 34, 62));
			}
			// Line: 42
			try {
				count = getCount(1285044316) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:numbers")) {
						incrCount(1285044316);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 47
							order.putIfNotNull("tickets", event.get("sem:numbers"));
							// Line: 48
							CheckOrder state6 = new CheckOrder();
							flowThread.gotoState(state6, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 48, 30)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 42, 68));
			}
			// Line: 50
			try {
				count = getCount(1588970020) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:exit")) {
						incrCount(1588970020);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 51
							Exit state7 = new Exit();
							flowThread.gotoState(state7, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 51, 30)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 50, 65));
			}
			// Line: 53
			try {
				count = getCount(1940447180) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1940447180);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state8 = new iristk.flow.DialogFlow.say();
						state8.setText("Sorry, I didn't get that. You can ask about tickets or stops.");
						if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 53, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 55
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 55, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 53, 42));
			}
			// Line: 57
			try {
				count = getCount(1066376662) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(1066376662);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state9 = new iristk.flow.DialogFlow.say();
						state9.setText("Sorry, I didn't hear anything");
						if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 57, 44)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 59
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 59, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 57, 44));
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
			// Line: 64
			try {
				EXECUTION: {
					int count = getCount(1490180672) + 1;
					incrCount(1490180672);
					// Line: 65
					log(order.toStringIndent());
					// Line: 66
					if (!order.has("destination")) {
						// Line: 67
						RequestDestination state10 = new RequestDestination();
						flowThread.gotoState(state10, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 67, 39)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 69
					if (asInteger(order.get("tickets")) == 0) {
						// Line: 70
						RequestQuantity state11 = new RequestQuantity();
						flowThread.gotoState(state11, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 70, 36)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 72
					Pay state12 = new Pay();
					flowThread.gotoState(state12, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 72, 23)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 64, 12));
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
			// Line: 77
			try {
				EXECUTION: {
					int count = getCount(425918570) + 1;
					incrCount(425918570);
					iristk.flow.DialogFlow.prompt state13 = new iristk.flow.DialogFlow.prompt();
					state13.setText("Where do you want to go?");
					if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 77, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 79
					CheckOrder state14 = new CheckOrder();
					flowThread.gotoState(state14, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 79, 30)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 77, 12));
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
			// Line: 84
			try {
				EXECUTION: {
					int count = getCount(204349222) + 1;
					incrCount(204349222);
					iristk.flow.DialogFlow.prompt state15 = new iristk.flow.DialogFlow.prompt();
					state15.setText("How many tickets do you want?");
					if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 84, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 86
					CheckOrder state16 = new CheckOrder();
					flowThread.gotoState(state16, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 86, 30)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 84, 12));
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
			// Line: 91
			try {
				EXECUTION: {
					int count = getCount(2110121908) + 1;
					incrCount(2110121908);
					iristk.flow.DialogFlow.say state17 = new iristk.flow.DialogFlow.say();
					state17.setText(concat("Please pay in the counter over there. It will be", asInteger(order.get("tickets")) * 50, "kronor for", asInteger(order.get("tickets")), "tickets to", asString(order.get("destination"))));
					if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 91, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.say state18 = new iristk.flow.DialogFlow.say();
					state18.setText("Enjoy the ride!");
					if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 91, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 99
					order = new Record();
					// Line: 102
					Start state19 = new Start();
					flowThread.gotoState(state19, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 102, 25)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 91, 18));
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
			// Line: 107
			try {
				EXECUTION: {
					int count = getCount(1865127310) + 1;
					incrCount(1865127310);
					iristk.flow.DialogFlow.say state20 = new iristk.flow.DialogFlow.say();
					state20.setText("Byebye");
					if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 107, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 109
					System.exit(0);
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\IrisTK\\app\\busDriver\\src\\iristk\\app\\busDriver\\BusDriverFlow.xml"), 107, 18));
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
