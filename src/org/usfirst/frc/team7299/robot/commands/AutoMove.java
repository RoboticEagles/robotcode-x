package org.usfirst.frc.team7299.robot.commands;

import org.usfirst.frc.team7299.robot.Robot;
import org.usfirst.frc.team7299.util.BezierMovement;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class AutoMove extends Command {
    	private double time = 0;
    	private double expandedTime = 1;
    	private BezierMovement mvt;
	//private AHRS ahrs;
	private double maxSpeed = 1;

    public AutoMove() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
	    //ahrs = new AHRS(I2C.Port.kMXP);
	    	String msg = DriverStation.getInstance().getGameSpecificMessage();
	    //ASSUMING LEFT
	    	if(msg.codePointAt(0) == 'R') {
	    		double[] a = {0,50};
	    		double[] b = {-60,120};
	    		double[] c = {-60,200};
	    		mvt = new BezierMovement(a,b,c);
	    	} else if(msg.codePointAt(0) == 'L') {
	    		double[] a = {0, 30};
	    		double[] b = {(Robot.width / 2) + 82, 101 - Robot.length - 30};
	    		double[] c = {(Robot.width / 2) + 82, 101 - Robot.length};
	    		mvt = new BezierMovement(a,b,c);
	    	} else {
	    		double[] a = {0, 50};
	    		double[] b = {-60, 120};
	    		double[] c = {-60, 200};
	    		mvt = new BezierMovement(a,b,c);
	    	}
	    	maxSpeed = Preferences.getInstance().getDouble("maxSpeed", 1);
	    expandedTime = mvt.getMaxWheelSpeed() / maxSpeed;
    	}

    protected void execute() {
    		time += 0.2;
    		if(time >= expandedTime) {
    			Robot.drivetrain.setSpeed(0);
    		}
    		double[] m = mvt.drivetrainDelta(time / expandedTime);
    		Robot.drivetrain.setLeftSpeed(m[0] / expandedTime / maxSpeed);
    		Robot.drivetrain.setRightSpeed(m[1] / expandedTime / maxSpeed);
    }

    protected boolean isFinished() {
        return (time >= expandedTime) && (Robot.drivetrain.getLeftSpeed() == 0) && (Robot.drivetrain.getRightSpeed() == 0);
    }

    protected void end() {}

    protected void interrupted() {}
}
