package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DrivetrainSubsystem extends Subsystem {
	Spark drivetrainFL = new Spark(0);
	Spark drivetrainFR = new Spark(1);
	Spark drivetrainBL = new Spark(2);
	Spark drivetrainBR = new Spark(3);

    public void initDefaultCommand() {}

    public void setSpeed(double s) {
    		setLeftSpeed(s);
    		setRightSpeed(s);
    }

    public void setLeftSpeed(double s) {
    		drivetrainFL.set(s);
    		drivetrainBL.set(s);
    }
    
    public void setRightSpeed(double s) {
		drivetrainFR.set(s);
		drivetrainBR.set(s);
    }
    
    public void stop() {
    		setSpeed(0);
    }
}
