package gamepad;

import java.util.Observable;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import util.storage.Pair;

public class Gamepad extends Thread {

	private Controller logitechRumblepad;
	private Component[] logitechRumblepadComponents;
	private InternalState internalState;

	public Gamepad() {

		internalState = new InternalState();

		for (Controller controller : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
			// System.out.println(controller.toString() + " ==> " + controller.getType());
			if (controller.getType().equals(Controller.Type.GAMEPAD)
					|| controller.getType().equals(Controller.Type.STICK)) {
				logitechRumblepad = controller;
				logitechRumblepadComponents = logitechRumblepad.getComponents();
				// System.out.println(
				// controller.toString() + " ==> " + controller.getType() + " is a " +
				// controller.getType() + "!");
				break;
			}
		}

		boolean printButtons = false;
		if (printButtons) {
			if (logitechRumblepad != null) {
				for (int i = 0; i < 100; i++) {
					try {
						System.out.println(
								"logitechRumblepadComponents[" + i + "] = " + logitechRumblepadComponents[i].getName());
					} catch (Exception e) {
						break;
					}
				}
				System.exit(0);
			}
		}
	}

	public InternalState getInternalState() {
		return internalState;
	}

	public void run() {
		if (logitechRumblepad != null) {
			while (true) {
				logitechRumblepad.poll();
				float yaxisR = logitechRumblepadComponents[0].getPollData();
				float xaxisR = logitechRumblepadComponents[1].getPollData();
				float yaxisL = logitechRumblepadComponents[2].getPollData();
				float xaxisL = logitechRumblepadComponents[3].getPollData();
				float MODE = logitechRumblepadComponents[4].getPollData(); // NOTHING(0.0), UP(0.25), DOWN(0.75),
																			// RIGHT(0.5), LEFT(1.0)
				float X = logitechRumblepadComponents[5].getPollData();
				float A = logitechRumblepadComponents[6].getPollData();
				float B = logitechRumblepadComponents[7].getPollData();
				float Y = logitechRumblepadComponents[8].getPollData();
				float LB = logitechRumblepadComponents[9].getPollData();
				float RB = logitechRumblepadComponents[10].getPollData();
				float LT = logitechRumblepadComponents[11].getPollData();
				float RT = logitechRumblepadComponents[12].getPollData();
				float BACK = logitechRumblepadComponents[13].getPollData();
				float START = logitechRumblepadComponents[14].getPollData();
				float axisLclick = logitechRumblepadComponents[15].getPollData();
				float axisRclick = logitechRumblepadComponents[16].getPollData();

				internalState.set_yaxisR(yaxisR);
				internalState.set_xaxisR(xaxisR);
				internalState.set_yaxisL(yaxisL);
				internalState.set_xaxisL(xaxisL);
				internalState.set_MODE(MODE);
				internalState.set_X(X);
				internalState.set_A(A);
				internalState.set_B(B);
				internalState.set_Y(Y);
				internalState.set_LB(LB);
				internalState.set_RB(RB);
				internalState.set_LT(LT);
				internalState.set_RT(RT);
				internalState.set_BACK(BACK);
				internalState.set_START(START);
				internalState.set_axisLclick(axisLclick);
				internalState.set_axisRclick(axisRclick);
			}
		}
	}

	public class InternalState extends Observable {

		private float yaxisR, xaxisR, yaxisL, xaxisL, MODE, X, A, B, Y, LB, RB, LT, RT, BACK, START, axisLclick,
				axisRclick;

		private InternalState() {
			yaxisR = 0.0f;
			xaxisR = 0.0f;
			yaxisL = 0.0f;
			xaxisL = 0.0f;
			MODE = 0.0f;
			X = 0.0f;
			A = 0.0f;
			B = 0.0f;
			Y = 0.0f;
			LB = 0.0f;
			RB = 0.0f;
			LT = 0.0f;
			RT = 0.0f;
			BACK = 0.0f;
			START = 0.0f;
			axisLclick = 0.0f;
			axisRclick = 0.0f;
		}

		private void set_yaxisR(float yaxisR) {
			if (Float.compare(this.yaxisR, yaxisR) != 0) {
				synchronized (this) {
					this.yaxisR = yaxisR;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("yaxisR", yaxisR));
			}
		}

		private void set_xaxisR(float xaxisR) {
			if (Float.compare(this.xaxisR, xaxisR) != 0) {
				synchronized (this) {
					this.xaxisR = xaxisR;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("xaxisR", xaxisR));
			}
		}

		private void set_yaxisL(float yaxisL) {
			if (Float.compare(this.yaxisL, yaxisL) != 0) {
				synchronized (this) {
					this.yaxisL = yaxisL;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("yaxisL", yaxisL));
			}
		}

		private void set_xaxisL(float xaxisL) {
			if (Float.compare(this.xaxisL, xaxisL) != 0) {
				synchronized (this) {
					this.xaxisL = xaxisL;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("xaxisL", xaxisL));
			}
		}

		private void set_MODE(float MODE) {
			if (Float.compare(this.MODE, MODE) != 0) {
				synchronized (this) {
					this.MODE = MODE;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("MODE", MODE));
			}
		}

		private void set_X(float X) {
			if (Float.compare(this.X, X) != 0) {
				synchronized (this) {
					this.X = X;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("X", X));
			}
		}

		private void set_A(float A) {
			if (Float.compare(this.A, A) != 0) {
				synchronized (this) {
					this.A = A;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("A", A));
			}
		}

		private void set_B(float B) {
			if (Float.compare(this.B, B) != 0) {
				synchronized (this) {
					this.B = B;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("B", B));
			}
		}

		private void set_Y(float Y) {
			if (Float.compare(this.Y, Y) != 0) {
				synchronized (this) {
					this.Y = Y;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("Y", Y));
			}
		}

		private void set_LB(float LB) {
			if (Float.compare(this.LB, LB) != 0) {
				synchronized (this) {
					this.LB = LB;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("LB", LB));
			}
		}

		private void set_RB(float RB) {
			if (Float.compare(this.RB, RB) != 0) {
				synchronized (this) {
					this.RB = RB;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("RB", RB));
			}
		}

		private void set_LT(float LT) {
			if (Float.compare(this.LT, LT) != 0) {
				synchronized (this) {
					this.LT = LT;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("LT", LT));
			}
		}

		private void set_RT(float RT) {
			if (Float.compare(this.RT, RT) != 0) {
				synchronized (this) {
					this.RT = RT;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("RT", RT));
			}
		}

		private void set_BACK(float BACK) {
			if (Float.compare(this.BACK, BACK) != 0) {
				synchronized (this) {
					this.BACK = BACK;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("BACK", BACK));
			}
		}

		private void set_START(float START) {
			if (Float.compare(this.START, START) != 0) {
				synchronized (this) {
					this.START = START;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("START", START));
			}
		}

		private void set_axisLclick(float axisLclick) {
			if (Float.compare(this.axisLclick, axisLclick) != 0) {
				synchronized (this) {
					this.axisLclick = axisLclick;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("axisLclick", axisLclick));
			}
		}

		private void set_axisRclick(float axisRclick) {
			if (Float.compare(this.axisRclick, axisRclick) != 0) {
				synchronized (this) {
					this.axisRclick = axisRclick;
				}
				setChanged();
				notifyObservers(new Pair<String, Float>("axisRclick", axisRclick));
			}
		}
	}
}
