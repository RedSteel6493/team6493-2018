package org.usfirst.frc.team6493.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team6493.robot.subsystems.*;

public class Robot extends IterativeRobot {

	Joystick joy = new Joystick(RobotMap.DRIVER);
	Joystick cont = new Joystick(RobotMap.OPERATOR);

	DriveTrain drive;
	Elevator lift;
	Arms intake;

	boolean moveUp = false;
	boolean moveDown = false;
	boolean armToggle = true;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		drive = new DriveTrain();
		lift = new Elevator();
		intake = new Arms();
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		if(cont.getRawButton(RobotMap.TOGGLE_ARM) && armToggle) {
			if(intake.armsOpen) {
				intake.close();
			}else {
				intake.open();
			}
			armToggle = false;
		}else if(cont.getRawButton(RobotMap.TOGGLE_ARM)) {
			armToggle = true;
		}
		
		if(cont.getRawButton(RobotMap.ELEVATOR_UP)){
			moveUp = true;
		}else{
			moveUp = false;
		}

		if(cont.getRawButton(RobotMap.ELEVATOR_DOWN)){
			moveDown = true;
		}else{
			moveDown = false;
		}

		drive.drive(joy.getX(),joy.getY());
		intake.setSpeed(cont.getRawAxis(RobotMap.ARM_LEFT),cont.getRawAxis(RobotMap.ARM_RIGHT));
		lift.up(moveUp);
		lift.down(moveDown);
	}
}