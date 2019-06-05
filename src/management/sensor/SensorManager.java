package management.sensor;

import java.util.Arrays;

import brbrain.AXRegister;
import brbrain.AXS1Register;
import brbrain.BRBrain;

public class SensorManager {

	private final static int[] three = new int[] { 3 };
	private final static int[] IR = new int[] { 100 };
	private final static AXRegister[] AXS1_LEFT_IR_SENSOR_DATA = new AXRegister[] {
			AXS1Register.AXS1_LEFT_IR_SENSOR_DATA };
	private final static AXRegister[] AXS1_LEFT_LUMINOSITY = new AXRegister[] {
			AXS1Register.AXS1_LEFT_LUMINOSITY };

	public static float[] getIR(BRBrain b) {
		try {
			b.setReadFormat(IR, AXS1_LEFT_IR_SENSOR_DATA, three);
			float[] IRdata = Arrays.copyOf(b.readNatural(), 3); // 3 items starting from LEFT_SENSOR: that is:  LEFT + CENTER + RIGHT
			return IRdata;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new float[3];
	}

	public static float[] getLuminosity(BRBrain b) {
		try {
			b.setReadFormat(IR, AXS1_LEFT_LUMINOSITY, three);
			float[] LUMINOSITYdata = Arrays.copyOf(b.readNatural(), 3); // 3 items starting from LEFT_LUMINOSITY: that is:  LEFT + CENTER + RIGHT
			return LUMINOSITYdata;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new float[3];
	}
}
