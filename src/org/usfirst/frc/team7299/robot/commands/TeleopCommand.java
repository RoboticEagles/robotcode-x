package org.usfirst.frc.team7299.robot.commands;

import org.usfirst.frc.team7299.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopCommand extends Command {
	public boolean slowmode = false;
	public double speedL = 0;
	public double speedR = 0;
	public final double accel = 0.03;

    public TeleopCommand() {
        requires(Robot.conveyor);
        requires(Robot.drivetrain);
        requires(Robot.intake);
    }

    protected void initialize() {}

    protected void execute() {
    		Joystick j = Robot.oi.getJoystick();
    		double x = j.getRawAxis(1);
    		double y = j.getRawAxis(0);
    		if(j.getRawButtonReleased(8)) slowmode = !slowmode;
    		double speedMultiplier = slowmode ? 0.5 : 1;
    		double pL = ((y - x) / Math.sqrt(2)) * speedMultiplier * 0.75;
    		double pR = ((y + x) / Math.sqrt(2)) * speedMultiplier;
    		if(speedL < pL) speedL += accel;
    		else if(speedL > pL) speedL -= accel;
    		if(speedR < pR) speedR += accel;
    		else if(speedR > pR) speedR -= accel;
    		Robot.drivetrain.setLeftSpeed(speedL);
    		Robot.drivetrain.setRightSpeed(speedR);
    		Robot.intake.setSpeed((j.getRawButton(5) ? -1 : j.getRawAxis(2)) * speedMultiplier);
    		Robot.conveyor.setSpeed((j.getRawButton(6) ? -1 : j.getRawAxis(3)) * speedMultiplier);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}
    
    protected void interrupted() {}
}
