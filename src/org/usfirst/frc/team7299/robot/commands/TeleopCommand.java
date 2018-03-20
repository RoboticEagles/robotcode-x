package org.usfirst.frc.team7299.robot.commands;

import org.usfirst.frc.team7299.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopCommand extends Command {
	//private boolean slowmode = false;
	//private boolean isols = false;
	//private boolean rsols = false;
	//private boolean asols = false;
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
    		boolean BY = j.getRawButton(4);
    		double X1 = j.getRawAxis(0);
    		double YI = 0.5 - j.getRawAxis(1)/2.0;
    		double POV = j.getPOV();
    		if(BY || j.getRawButtonReleased(3)) {
    			Robot.drivetrain.forceSetLeftSpeed(0);
    			Robot.drivetrain.forceSetRightSpeed(0);
    			Robot.drivetrain.targetSpeedL = 0;
    			Robot.drivetrain.targetSpeedR = 0;
    		} else {
    			double SL = (BX? Math.cbrt(X1) * 0.35
    					:( (BA? YI : (BB? -YI : 0.0) )
    					* Math.min(1.0, 1.0 + 2.0 * 0.35 * Math.cbrt(X1) * (BB? -1 : 1))) );
    			double SR = (BX? Math.cbrt(X1) * -0.35
    					:( (BA? YI : (BB? -YI : 0.0) )
    					* Math.min(1.0, 1.0 - 2.0 * 0.35 * Math.cbrt(X1) * (BB? -1 : 1))) );
        		Robot.drivetrain.setLeftSpeed(SL);
        		Robot.drivetrain.setRightSpeed(SR);	
    		}
    		
		if(POV == 90) {
			Robot.elevator.set(-1);
		} else if (POV == 270) {
			Robot.elevator.set(1);
		} else {
			Robot.elevator.set(0);
		}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}
    
    protected void interrupted() {}
}
