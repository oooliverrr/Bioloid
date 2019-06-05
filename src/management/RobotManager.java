package management;

import java.util.Observable;
import java.util.Observer;

import brbrain.BRBrain;
import gamepad.Gamepad;

public class RobotManager implements Observer {

	protected BRBrain b = null;

	public RobotManager() {
	}

	public RobotManager connectToBioloid(String port) {
		try {
			b = new BRBrain(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public RobotManager initializeGamepad() {
		Gamepad gamepad = new Gamepad();
		gamepad.getInternalState().addObserver(this);
		gamepad.run();
		return this;
	}

	public RobotManager listPorts() {
		BRBrain.listPorts();
		return this;
	}

	public int pingCM5() {
		if (b != null) {
			try {
				int ping = b.pingCM5();
				return ping;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public RobotManager close() {
		if (b != null) {
			try {
				b.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}

	@Override
	public void update(Observable observable, Object object) {
		System.out.println("observable = " + observable + " => object = " + object);
	}
}
