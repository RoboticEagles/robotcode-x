package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticSubsystem extends Subsystem {
	Compressor compressor = new Compressor();
	DoubleSolenoid solenoidL = new DoubleSolenoid(0, 1);
	DoubleSolenoid solenoidR = new DoubleSolenoid(2, 3);
	
	@Override
	protected void initDefaultCommand() {
		compressor.setClosedLoopControl(true);
		compressor.stop();
		solenoidL.set(Value.kForward);
		solenoidR.set(Value.kForward);
	}
	
	public void setInflating(boolean y) {
		if(y) {
			compressor.start();
		} else {
			compressor.stop();
		}
	}
	
	public void setSolenoids(boolean y) {
		solenoidL.set(y ? Value.kForward : Value.kReverse);
		solenoidR.set(y ? Value.kForward : Value.kReverse);
	}
	
	public void freeSolenoids() {
		solenoidL.set(Value.kOff);
		solenoidR.set(Value.kOff);
	}
}

