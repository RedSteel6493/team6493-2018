package org.usfirst.frc.team6493.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.usfirst.frc.team6493.robot.RobotMap;

public class DriveTrain {
    /* talons for arcade drive */
    private static WPI_TalonSRX frontLeft;
    private static WPI_TalonSRX frontRight;

    /* extra victors */
    private static WPI_VictorSPX leftFollow;
    private static WPI_VictorSPX rightFollow;

    /* Forward, turn, and multiplier values */
    private double forward = 0;
    private double turn = 0;
    private double mult = 0;

    /* Drivetrain */
    DifferentialDrive drive;

    public DriveTrain(){
        frontLeft = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR_CHANNEL);
        frontRight = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR_CHANNEL);

        leftFollow = new WPI_VictorSPX(RobotMap.LEFT_REAR_MOTOR_CHANNEL);
        rightFollow = new WPI_VictorSPX(RobotMap.RIGHT_REAR_MOTOR_CHANNEL);

        leftFollow.follow(frontLeft);
        rightFollow.follow(frontRight);

        /* drive robot forward and make sure all
         * motors spin the correct way.
         * Toggle booleans accordingly.... */
        frontLeft.setInverted(false);
        leftFollow.setInverted(false);


        frontRight.setInverted(true);
        rightFollow.setInverted(true);
        
        drive = new DifferentialDrive(frontLeft, frontRight);

        forward = 0;
        turn = 0;
        mult = 1;
    }

    public void drive(double forward, double turn, double mult){
    	this.mult = mult;
        this.forward = (mult) * forward;
        this.turn = (-mult) * turn;

        if(Math.abs(this.turn) < RobotMap.DRIVE_DEADBAND){
            this.turn = 0;
        }

        if(Math.abs(this.forward) < RobotMap.DRIVE_DEADBAND){
            this.forward = 0;
        }

        drive.arcadeDrive(this.forward, this.turn);
    }

    public void drive(double forward, double turn){
        this.forward = (mult) * forward;
        this.turn = (-mult) * turn;

        if(Math.abs(this.turn) < RobotMap.DRIVE_DEADBAND){
            this.turn = 0;
        }

        if(Math.abs(this.forward) < RobotMap.DRIVE_DEADBAND){
            this.forward = 0;
        }

        drive.arcadeDrive(this.forward, this.turn);
    }
}
