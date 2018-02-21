package org.usfirst.frc.team7299.robot.commands;

import org.usfirst.frc.team7299.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import com.kauailabs.navx.frc.AHRS;

public class AutoCommand1 extends Command {

    public AutoCommand1() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	AHRS ahrs;
    	ahrs = new AHRS(I2C.Port.kMXP);
    	String position = "left";
    	String gameData;
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	while (position.equals("left")) {
    		if ('L' == 'L') {
    		  Robot.drivetrain.setLeftSpeed(1.0);
    		  Robot.drivetrain.setRightSpeed(0.5);
    		  if( ahrs.getDisplacementX() == 168) {
    			  Robot.drivetrain.setSpeed(0);
    		  }
    		}
    		else {
    			
    		}
    	}
    	
    	while(position.equals("right")) {
    		if (gameData.charAt(0) == 'L') {
    			
    		}
    		else {
    			
    		}
    		
    	}
    	while(position.equals("center")) {
    		if (gameData.charAt(0) == 'L') {
    			
    		}
    		else {
    			
    		}
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
