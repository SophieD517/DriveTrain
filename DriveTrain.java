import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AnalogGyro;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.Encoder;


public class DriveTrain {
	public static CANSparkMax rightFront = new CANSparkMax(RobotMap.rightFrontPort, MotorType.kBrushless);
	public static CANSparkMax leftFront= new CANSparkMax(RobotMap.leftFrontPort, MotorType.kBrushless);
	public static CANSparkMax rightMiddle= new CANSparkMax(RobotMap.rightMiddlePort, MotorType.kBrushless);
	public static CANSparkMax leftMiddle = new CANSparkMax(RobotMap.leftMiddlePort, MotorType.kBrushless);
	public static CANSparkMax rightBack= new CANSparkMax(RobotMap.rightBackPort, MotorType.kBrushless);
	public static CANSparkMax leftBack= new CANSparkMax(RobotMap.leftBackPort, MotorType.kBrushless);
	public static CANEncoder rightEncoder = rightFront.getEncoder();
	public static CANEncoder leftEncoder = leftFront.getEncoder();
	public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);
	public static Joystick joy = new Joystick(RobotMap.joyPort);

      public DriveTrain() {}

	public static void driveTeleop() {
		rightFront.set(joy.getRawAxis(5));
		rightMiddle.set(joy.getRawAxis(5));
		rightBack.set(joy.getRawAxis(5));
		leftFront.set(joy.getRawAxis(0));
		leftMiddle.set(joy.getRawAxis(0));
		leftBack.set(joy.getRawAxis(0));
	}	

	public static void turn(int angle) {
		gyro.reset();
		if (angle > 180) {
			angle = angle-360;
		}
		if (angle > 0) {
			while (gyro.getAngle() != angle) {\
				rightFront.set(-0.25);
				rightMiddle.set(-0.25);
				rightBack.set(-0.25);
				leftFront.set(0.25);
				leftMiddle.set(0.25);
				leftBack.set(0.25);
			} 
		} 
		else if (angle < 0) {
			while (gyro.getAngle() != angle) {\
				rightFront.set(-0.25);
				rightMiddle.set(-0.25);
				rightBack.set(-0.25);
				leftFront.set(0.25);
				leftMiddle.set(0.25);
				leftBack.set(0.25);
			}
		}
	}

	public static void driveAutonLeft(double speed, double dist1, double dist2) {	
		while (rightEncoder.getPosition() < dist1) {
			rightFront.set(speed);
			rightMiddle.set(speed);
			rightBack.set(speed);
			leftFront.set(speed);
			leftMiddle.set(speed);
			leftBack.set(speed);
		}
		turn(90);
		rightEncoder.set(0.0);
		while (rightEncoder.getPosition < dist2) {
			rightFront.set(speed);
			rightMiddle.set(speed);
			rightBack.set(speed);
			leftFront.set(speed);
			leftMiddle.set(speed);
			leftBack.set(speed);
		}	
	}	

	public static void driveAutonMiddle(double speed, double dist1, double dist2) {	
		while (rightEncoder.getPosition() < dist1) {
			rightFront.set(speed);
			rightMiddle.set(speed);
			rightBack.set(speed);
			leftFront.set(speed);
			leftMiddle.set(speed);
			leftBack.set(speed);
		}
	}
	public static void driveAutonRight(double speed, double dist1, double dist2) {	
		while (rightEncoder.getPosition() < dist1) {
			rightFront.set(speed);
			rightMiddle.set(speed);
			rightBack.set(speed);
			leftFront.set(speed);
			leftMiddle.set(speed);
			leftBack.set(speed);
		}
		turn(270);
		rightEncoder.set(0.0);
		while (rightEncoder.getPosition < dist2) {
			rightFront.set(speed);
			rightMiddle.set(speed);
			rightBack.set(speed);
			leftFront.set(speed);
			leftMiddle.set(speed);
			leftBack.set(speed);
		}	
	}	
}

public DriveTeleop extends Command {
	public DriveTeleop(){
		protected boolean isFinished(){}

}
	protected void initialize(){}
	protected void execute(){
		driveTeleop();
		if (timer == 0) {
			isFinished = true;
		}	
	}
	protected boolean isFinished(){}
	protected void end(){}
	protected void interrupted(){}
}

public TurnDegrees extends Command {
	public TurnDegrees(double a){
		angle=a;
		protected boolean isFinished(){}
	}
	protected void initialize(){}
	protected void execute(){
		turn(angle);
		isFinished = true;
	}
	protected void end(){}
	protected void interrupted(){}
}

public driveAutonLeft extends Command {
	public driveAutonLeft(double s, double d1, double d2){
		speed = s;
		dist1 = d1;
		dist2 = d2;
		protected boolean isFinished = false;
	}
	protected void initialize(){}
	protected void execute(){
		driveAutonLeft(speed, dist1, dist2);
		if (timer == 0) {
			isFinished = true;
		}
	}
	protected void end(){}
	protected void interrupted(){}
}

public driveAutonMiddle extends Command {
	public driveAutonMiddle(double s, double d1, double d2){
		speed = s;
		dist1 = d1;
		dist2 = d2;
		protected boolean isFinished = false;
	}
	protected void initialize(){}
	protected void execute(){
		driveAutonLeft(speed, dist1, dist2);;
		if (timer == 0) {
			isFinished = true;
		}
	}
	protected void end(){}
	protected void interrupted(){}
}

public driveAutonRight extends Command {
	public driveAutonRight(double s, double d1, double d2){
		speed = s;
		dist1 = d1;
		dist2 = d2;
		protected boolean isFinished = false;
	}
	protected void initialize(){}
	protected void execute(){
		driveAutonLeft(speed, dist1, dist2);
		if (timer == 0) {
			isFinished = true;
		}
	}
	protected void end(){}
	protected void interrupted(){}
}
