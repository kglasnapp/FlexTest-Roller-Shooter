package frc.robot.commands;
// Copyright (c) FIRST and other WPILib contributors.

import static frc.robot.utilities.Util.logf;


// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TestIndexerSubsystem;
import frc.robot.subsystems.TestIntakeSubsystem;
import frc.robot.subsystems.TestShooterSubsystem;

public class StopAllCommand extends Command {
    TestIndexerSubsystem indexer;
    TestShooterSubsystem shooter;
    TestIntakeSubsystem intake;

    public StopAllCommand(TestShooterSubsystem shooter, TestIndexerSubsystem indexer, TestIntakeSubsystem intake) {
        this.indexer = indexer;
        this.shooter = shooter;
        this.intake = intake;
        addRequirements(intake);
        addRequirements(indexer);
        addRequirements(shooter);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        logf("Stop All Init\n");
    }

    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        logf("Stop All end interrupted:%b\n", interrupted);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        shooter.stop();
        indexer.stop();
        intake.stop();
        logf("stop all finished\n");
        return true;
    }
}