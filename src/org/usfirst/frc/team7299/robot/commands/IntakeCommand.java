package org.usfirst.frc.team7299.robot.commands;

import org.usfirst.frc.team7299.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {

    public IntakeCommand() {
        requires(Robot.intake);
        setTimeout(0.5);
    }

    protected void initialize() {}

    protected void execute() {}

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {}
    
    protected void interrupted() {}
}
