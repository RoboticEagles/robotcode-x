package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DrivetrainSubsystem extends Subsystem {
	Victor drivetrainFL = new Victor(6);
	Victor drivetrainFR = new Victor(8);
	Victor drivetrainBL = new Victor(5);
	Victor drivetrainBR = new Victor(7);

    public void initDefaultCommand() {}

    public void setSpeed(double s) {
    		setLeftSpeed(s);
    		setRightSpeed(s);
    }

    public void setLeftSpeed(double s) {
    		drivetrainFL.set(-s);
    		drivetrainBL.set(-s);
    }
    
    public void setRightSpeed(double s) {
		drivetrainFR.set(s);
		drivetrainBR.set(s);
    }
    
    public void stop() {
    		setSpeed(0);
    }
}
