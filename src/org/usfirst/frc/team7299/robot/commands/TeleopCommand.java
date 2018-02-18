package org.usfirst.frc.team7299.robot.commands;

import org.usfirst.frc.team7299.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopCommand extends Command {
	public final double accel = 0.03;
	public boolean slowmode = false;
	public boolean sols = false;
	public double speedL = 0;
	public double speedR = 0;
	public boolean inflating = false;

    public TeleopCommand() {
        requires(Robot.conveyor);
        requires(Robot.drivetrain);
        requires(Robot.intake);
    }

    protected void initialize() {}

    protected void execute() {
    		Joystick j = Robot.oi.getJoystick();
    		double x = j.getRawAxis(0);
    		double y = j.getRawAxis(1);
    		if(j.getRawButtonReleased(8)) slowmode = !slowmode;
    		double speedMultiplier = slowmode ? 0.5 : 1;
    		double pL = ((y - x) / Math.sqrt(2)) * speedMultiplier * 0.75;
    		double pR = ((y + x) / Math.sqrt(2)) * speedMultiplier * 0.75;
    		if(speedL < pL) speedL += accel;
    		else if(speedL > pL) speedL -= accel;
    		if(speedR < pR) speedR += accel;
    		else if(speedR > pR) speedR -= accel;
    		Robot.intake.setSpeed((j.getRawButton(5) ? -1 : j.getRawAxis(2)) * speedMultiplier * 0.5);
    		Robot.conveyor.setSpeed((j.getRawButton(6) ? -1 : j.getRawAxis(3)) * speedMultiplier);
    		if(j.getPOV() == 90) {
    			Robot.climber.setSpeed(1);
    		} else if(j.getPOV() == 270) {
    			Robot.climber.setSpeed(-1);
    		} else {
    			Robot.climber.setSpeed(0);
    		}
        	Robot.drivetrain.setLeftSpeed(speedL);
        	Robot.drivetrain.setRightSpeed(speedR);
        	if(j.getRawButton(1)) {
        		Robot.elevator.setSpeed(0.5);
        	} else {
        		Robot.elevator.setSpeed(0);
        	}
        	
        if(j.getRawButtonReleased(2)) {
        		sols = !sols;
        		Robot.pneumatics.setSolenoids(sols);
        	}
        	
        	if(j.getRawButtonReleased(3)) {
        		Robot.pneumatics.freeSolenoids();
        	}
        	
        	if(j.getRawButtonReleased(4)) {
        		inflating = !inflating;
        		Robot.pneumatics.setInflating(inflating);
        	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}
    
    protected void interrupted() {}
}
