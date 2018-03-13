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
    		double pL = ((y - x) / Math.sqrt(2)) * speedMultiplier * 0.65;
    		double pR = ((y + x) / Math.sqrt(2)) * speedMultiplier * 0.65;
    		if(speedL < pL) speedL += accel;
    		else if(speedL > pL) speedL -= accel;
    		if(speedR < pR) speedR += accel;
    		else if(speedR > pR) speedR -= accel;
    		Robot.intake.setSpeed((j.getRawButton(5) ? -1 : j.getRawAxis(2)) * 0.5);
    		Robot.conveyor.setSpeed((j.getRawButton(6) ? -1 : j.getRawAxis(3)));
    		if(j.getPOV() == 90) {
    			Robot.climber.setSpeed(1);
    		} else if(j.getPOV() == 270) {
    			Robot.climber.setSpeed(-1);
    		} else {
    			Robot.climber.setSpeed(0);
    		}
        	Robot.drivetrain.setLeftSpeed(speedL);
        	Robot.drivetrain.setRightSpeed(speedR);
        	
        if(j.getRawButtonReleased(1)) {
        		isols = !isols;
        		Robot.pneumatics.setIntake(isols);
        	}
        	
        	if(j.getRawButtonReleased(2)) {
        		rsols = !rsols;
        		Robot.pneumatics.setRamp(rsols);
        	}

        	/*if(j.getRawButtonPressed(3)) {
        		Robot.pneumatics.setAligner(true);
    		}*/
        	
        	if(j.getRawButtonReleased(3)) {
        		asols = !asols;
        		Robot.pneumatics.setAligner(asols);
    		}
        	
        	if(j.getRawButtonReleased(4)) {
        		inflating = !inflating;
        		Robot.pneumatics.setInflating(inflating);
        	}
        	
        	if(j.getRawButtonReleased(8)) {
        		Robot.pneumatics.alignerOff();
        	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}
    
    protected void interrupted() {}
}
