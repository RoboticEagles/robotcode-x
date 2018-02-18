package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {
	Spark climber = new Spark(7);
	
	@Override
	protected void initDefaultCommand() {}
	
	public void setSpeed(double s) {
		climber.set(s);
	}
}
