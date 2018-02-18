/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7299.robot;

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

	public static DrivetrainSubsystem drivetrain;
	public static IntakeSubsystem intake;
	public static ConveyorSubsystem conveyor;
	public static ElevatorSubsystem elevator;
	public static ClimberSubsystem climber;
	public static PneumaticSubsystem pneumatics;
	public static OI oi;

	@Override
	public void robotInit() {
		drivetrain = new DrivetrainSubsystem();
		intake = new IntakeSubsystem();
		conveyor = new ConveyorSubsystem();
		elevator = new ElevatorSubsystem();
		climber = new ClimberSubsystem();
		pneumatics = new PneumaticSubsystem();
		oi = new OI();
		teleopCmd = new TeleopCommand();
		//chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", m_chooser);
	}

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {}
	
	@Override
	public void autonomousInit() {
		//autoCmd.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		//autoCmd.cancel();
		teleopCmd.start();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void testPeriodic() {}
}
