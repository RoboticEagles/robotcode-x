package org.usfirst.frc.team7299.robot.commands;

import org.usfirst.frc.team7299.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopCommand extends Command {
	public final double accel = 0.02;
	public boolean slowmode = false;
	public boolean isols = false;
	public boolean rsols = false;
	public boolean asols = false;
	public double speedL = 0;
	public double speedR = 0;
	public boolean inflating = false;

    public TeleopCommand() {
        //requires(Robot.conveyor);
        requires(Robot.drivetrain);
        requires(Robot.elevator);
        //requires(Robot.intake);
    }

    protected void initialize() {}

    protected void execute() {
    		Joystick j = Robot.oi.getJoystick();
    		boolean BA = j.getRawButton(1);
    		boolean BB = j.getRawButton(2);
    		boolean BX = j.getRawButton(3);
    		double X1 = j.getRawAxis(0);
    		double POV = j.getPOV();
    		double SL = (BX? Math.pow(X1, 1/3) * -.35
    				:((BA? 1 : (BB? -1 : 0))
    				* Math.min(1, 1 - 2 * Math.pow(X1, 1/3) * .35)));
    		double SR = (BX? Math.pow(X1, 1/3) * .35
    				:((BA? 1 : (BB? -1 : 0))
				* Math.min(1, 1 + 2 * Math.pow(X1, 1/3) * .35)));
		if(speedR < SR) speedR = Math.round(speedR * 100) / 100 + accel;
		else if(speedR > SR) speedR = Math.round(speedR * 100) / 100 - accel;
		if(speedL < SL) speedL = Math.round(speedL * 100) / 100 + accel;
		else if(speedL > SL) speedL = Math.round(speedL * 100) / 100 - accel;
		
		if(POV == 90) {
			Robot.elevator.set(1);
		} else if (POV == 270) {
			Robot.elevator.set(-1);
		}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}
    
    protected void interrupted() {}
}
