/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7299.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team7299.robot.commands.TeleopCommand;
import org.usfirst.frc.team7299.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team7299.robot.subsystems.ConveyorSubsystem;
import org.usfirst.frc.team7299.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team7299.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team7299.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team7299.robot.subsystems.PneumaticSubsystem;

public class Robot extends TimedRobot {
	//SendableChooser<Command> m_chooser = new SendableChooser<>();
	//Command autoCmd;
	Command teleopCmd;
	
	private double accel = 0.01;

	public static DrivetrainSubsystem drivetrain;
	//public static IntakeSubsystem intake;
	//public static ConveyorSubsystem conveyor;
	public static ElevatorSubsystem elevator;
	//public static ClimberSubsystem climber;
	//public static PneumaticSubsystem pneumatics;
	public static OI oi;
	public static final double width = 10.0;
	public static final double length = 10.0;

	@Override
	public void robotInit() {
		Preferences.getInstance().putDouble("maxSpeed", 0.01);
		
		drivetrain = new DrivetrainSubsystem();
		//intake = new IntakeSubsystem();
		//conveyor = new ConveyorSubsystem();
		elevator = new ElevatorSubsystem();
		//climber = new ClimberSubsystem();
		//pneumatics = new PneumaticSubsystem();
		oi = new OI();
		teleopCmd = new TeleopCommand();
		//chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", m_chooser);
	}
	
	private void handleAccel() {
		double SL = drivetrain.targetSpeedL;
		double SR = drivetrain.targetSpeedR;
		double speedL = drivetrain.getLeftSpeed();
		double speedR = drivetrain.getRightSpeed();
		switch((int) Math.signum(Math.round(speedR*100) - Math.round(SR*100))) {
			case 0:
				break;
			case 1:
				speedR = Math.round(speedR * 100) / 100.0 - accel;
				break;
			case -1:
				speedR = Math.round(speedR * 100) / 100.0 + accel;
				break;
		}
		
		switch((int) Math.signum(Math.round(speedL*100) - Math.round(SL*100))) {
			case 0:
				break;
			case 1:
				speedL = Math.round(speedL * 100) / 100.0 - accel;
				break;
			case -1:
				speedL = Math.round(speedL * 100) / 100.0 + accel;
				break;
		}
		
		Robot.drivetrain.forceSetLeftSpeed(speedL);
		Robot.drivetrain.forceSetRightSpeed(speedR);
	}

	@Override
	public void disabledInit() {
		drivetrain.setSpeed(0);
	}
	
	@Override
	public void autonomousInit() {
		//autoCmd.start();
	}

	@Override
	public void teleopInit() {
		//autoCmd.cancel();
		teleopCmd.start();
	}
	
	@Override
	public void autonomousPeriodic() {
		handleAccel();
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledPeriodic() {
		handleAccel();
	}


	@Override
	public void teleopPeriodic() {
		handleAccel();
		Scheduler.getInstance().run();
	}
	
	@Override
	public void testPeriodic() {
		handleAccel();
	}
}
