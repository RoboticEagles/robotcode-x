package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ElevatorSubsystem extends Subsystem {
	PWMVictorSPX elevatorL = new PWMVictorSPX(4);
	PWMVictorSPX elevatorR = new PWMVictorSPX(5);
	
	@Override
	protected void initDefaultCommand() {}
	
	public void set(double s) {
		elevatorR.set(s);
		elevatorL.set(-s);
	}
}
