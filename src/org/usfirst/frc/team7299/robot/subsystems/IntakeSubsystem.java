package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
	Spark inwardIntakeL = new Spark(4);
	Spark inwardIntakeR = new Spark(5);
	Spark upwardIntakeL = new Spark(6);
	Spark upwardIntakeR = new Spark(7);

    public void initDefaultCommand() {}
    
    public void setSpeed(double s) {
    		inwardIntakeL.set(-s);
    		inwardIntakeR.set(s);
    		upwardIntakeL.set(s);
    		upwardIntakeR.set(-s);
    }
    
    public void stop() {
		setSpeed(0);
    }
}

