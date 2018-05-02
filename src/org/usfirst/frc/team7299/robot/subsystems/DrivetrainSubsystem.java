package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DrivetrainSubsystem extends Subsystem {
	Spark drivetrainFL = new Spark(0);
	Spark drivetrainFR = new Spark(1);
	Spark drivetrainBL = new Spark(2);
	Spark drivetrainBR = new Spark(3);
	public double targetSpeedL = 0;
	public double targetSpeedR = 0;
	private double currentSpeedL = 0;
	private double currentSpeedR = 0;

    public void initDefaultCommand() {}

    public void forceSetLeftSpeed(double s) {
    		drivetrainFL.set(s * .95);
    		drivetrainBL.set(s * .95);
    		currentSpeedL = s;
    }
    
    public void forceSetRightSpeed(double s) {
		drivetrainFR.set(-s);
		drivetrainBR.set(-s);
		currentSpeedR = s;
    }

    public double getLeftSpeed() {
    		return currentSpeedL;
    }
    
    public double getRightSpeed() {
		return currentSpeedR;
    }

    public void setSpeed(double s) {
    		setLeftSpeed(s);
    		setRightSpeed(s);
    }

    public void setLeftSpeed(double s) {
		if(Math.abs(s) > 1) s = Math.signum(s);
    		targetSpeedL = s;
    }
    
    public void setRightSpeed(double s) {
		if(Math.abs(s) > 1) s = Math.signum(s);
		targetSpeedR = s;
    }
    
    public void fixSpeed() {
    		targetSpeedL = currentSpeedL;
    		targetSpeedR = currentSpeedR;
}
}
