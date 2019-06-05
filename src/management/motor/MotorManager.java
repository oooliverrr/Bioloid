package management.motor;

import java.util.Arrays;

import brbrain.AX12Register;
import brbrain.AXRegister;
import brbrain.BRBrain;

public class MotorManager {

	private final static int[] one = new int[] { 1 };
	private final static AXRegister[] AX12_CW_ANGLE_LIMIT = new AXRegister[] { AX12Register.AX12_CW_ANGLE_LIMIT };
	private final static AXRegister[] AX12_CCW_ANGLE_LIMIT = new AXRegister[] { AX12Register.AX12_CCW_ANGLE_LIMIT };
	private final static AXRegister[] AX12_MOVING_SPEED = new AXRegister[] { AX12Register.AX12_MOVING_SPEED };
	private final static AXRegister[] AX12_PRESENT_POSITION = new AXRegister[] { AX12Register.AX12_PRESENT_POSITION };
	private final static AXRegister[] AX12_GOAL_POSITION = new AXRegister[] { AX12Register.AX12_GOAL_POSITION };

	public static void clearAngleConstraints(BRBrain b, int[] ids) {
		float[] zeros = new float[ids.length];
		setCWAngleConstraints(b, ids, zeros);
		setCCWAngleConstraints(b, ids, zeros);
	}

	public static void setCCWAngleConstraints(BRBrain b, int[] ids, float angle) {
		try {
			float[] angles = new float[ids.length];
			Arrays.fill(angles, angle);
			setCCWAngleConstraints(b, ids, angles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setCWAngleConstraints(BRBrain b, int[] ids, float angle) {
		try {
			float[] angles = new float[ids.length];
			Arrays.fill(angles, angle);
			setCWAngleConstraints(b, ids, angles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setCWAngleConstraints(BRBrain b, int[] ids, float[] angles) {
		try {
			b.setWriteFormat(ids, AX12_CW_ANGLE_LIMIT, one);
			b.write(angles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setCCWAngleConstraints(BRBrain b, int[] ids, float[] angles) {
		try {
			b.setWriteFormat(ids, AX12_CCW_ANGLE_LIMIT, one);
			b.write(angles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void stop(BRBrain b, int[] ids) {
		float[] zeros = new float[ids.length];
		setSpeed(b, ids, zeros);
	}

	/**
	 * 
	 * @param b
	 * @param ids
	 * @param speed
	 *            Positive: CCW, Negative: CW
	 */
	public static void setSpeed(BRBrain b, int[] ids, float speed) {
		try {
			float[] speeds = new float[ids.length];
			Arrays.fill(speeds, speed);
			setSpeed(b, ids, speeds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param b
	 * @param ids
	 * @param speeds
	 *            Positive: CCW, Negative: CW
	 */
	public static void setSpeed(BRBrain b, int[] ids, float[] speeds) {
		try {
			b.setWriteFormat(ids, AX12_MOVING_SPEED, one);
			b.write(speeds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setPosition(BRBrain b, int[] ids, float position) {
		try {
			float[] positions = new float[ids.length];
			Arrays.fill(positions, position);
			setPosition(b, ids, positions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setPosition(BRBrain b, int[] ids, float[] positions) {
		try {
			int numberOfMotors = ids.length;
			float speed = 40.0f;
			float threshold = 5.0f;

			boolean continuePositioning = true;
			while (continuePositioning) {
				continuePositioning = false;
				for (int i = 0; i < numberOfMotors; i++) {
					int[] singleID = new int[] { ids[i] };
					b.setReadFormat(singleID, AX12_PRESENT_POSITION, one);
					float currentPosition = b.readNatural()[0];
					float goalPosition = positions[i];
//					System.out.println("currentPosition = "+currentPosition+" / "+goalPosition);
					if (goalPosition - currentPosition > threshold) {
						setSpeed(b, singleID, speed);
						continuePositioning = true;
					} else if (currentPosition - goalPosition > threshold) {
						setSpeed(b, singleID, -speed);
						continuePositioning = true;
					} else {
						stop(b, singleID);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
