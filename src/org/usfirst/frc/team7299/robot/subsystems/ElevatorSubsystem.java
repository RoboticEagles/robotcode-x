package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ElevatorSubsystem extends Subsystem {
	Victor elevatorL = new Victor(4);
	Victor elevatorR = new Victor(3);
	
	@Override
	protected void initDefaultCommand() {}
	
	public void set(double s) {
		elevatorR.set(s);
		elevatorL.set(-s);
	}
}
