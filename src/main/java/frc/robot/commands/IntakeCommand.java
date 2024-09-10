package frc.robot.commands;
// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.TestIntakeSubsystem;

import static frc.robot.utilities.Util.logf;

public class IntakeCommand extends Command {
  /** Creates a new ReplaceMeCommand. */
  public IntakeCommand(TestIntakeSubsystem testintake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(testintake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    logf("Init Gyro Command\n");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    logf("Intake command interupted:%b\n", interrupted);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Robot.count % 20 == 10) {
      logf("Running Intake Command count:%d\n", Robot.count);
    }
    return false;
  }
}