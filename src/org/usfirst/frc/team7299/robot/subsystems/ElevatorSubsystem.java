package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ElevatorSubsystem extends Subsystem {
	Spark elevator = new Spark(6);
	
	@Override
	protected void initDefaultCommand() {}
	
	public void setSpeed(double s) {
		elevator.set(-s);
	}
}
