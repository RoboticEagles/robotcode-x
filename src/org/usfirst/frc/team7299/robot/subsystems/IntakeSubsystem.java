package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
	PWMVictorSPX inwardIntakeL = new PWMVictorSPX(6);
	PWMVictorSPX inwardIntakeR = new PWMVictorSPX(7);

    public void initDefaultCommand() {}
    
    public void setSpeed(double s) {
    		inwardIntakeL.set(-s);
    		inwardIntakeR.set(s);
    }
    
    public void rotate(double s) {
		inwardIntakeL.set(s);
		inwardIntakeR.set(s);
    }
    
    public void stop() {
		setSpeed(0);
    }
}

