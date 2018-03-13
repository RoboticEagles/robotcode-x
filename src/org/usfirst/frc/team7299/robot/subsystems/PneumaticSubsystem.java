package org.usfirst.frc.team7299.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticSubsystem extends Subsystem {
	Compressor compressor = new Compressor();
	DoubleSolenoid intakeSolenoid = new DoubleSolenoid(0, 1);
	DoubleSolenoid rampSolenoid = new DoubleSolenoid(2, 3);
	DoubleSolenoid alignerSolenoid = new DoubleSolenoid(4, 5);
	
	@Override
	protected void initDefaultCommand() {
		compressor.stop();
		compressor.setClosedLoopControl(true);
		intakeSolenoid.set(Value.kForward);
		rampSolenoid.set(Value.kForward);
		alignerSolenoid.set(Value.kReverse);
	}
	
	public void setInflating(boolean y) {
		if(y) {
			compressor.start();
		} else {
			compressor.stop();
		}
	}
	
	public void setIntake(boolean y) {
		intakeSolenoid.set(y ? Value.kForward : Value.kReverse);
	}

	public void setRamp(boolean y) {
		rampSolenoid.set(y ? Value.kForward : Value.kReverse);
	}
	
	public void setAligner(boolean y) {
		alignerSolenoid.set(y ? Value.kForward : Value.kReverse);
	}
	
	public void alignerOff() {
		alignerSolenoid.set(Value.kOff);
	}
	
}

