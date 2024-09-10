
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.commands.GyroCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.StopAllCommand;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.subsystems.TestIndexerSubsystem;
import frc.robot.subsystems.TestIntakeSubsystem;
import frc.robot.subsystems.TestShooterSubsystem;

public class RobotContainer {
    public final static CommandXboxController driveController = new CommandXboxController(2);
    // public final static CommandXboxController operatorController = new
    // CommandXboxController(3);
    // public final static XboxController operatorHID = operatorController.getHID();
    public final static XboxController driveHID = driveController.getHID();
    public final static TestIndexerSubsystem testIndexer = new TestIndexerSubsystem();
    public final static TestIntakeSubsystem testIntake = new TestIntakeSubsystem();
    public final static TestShooterSubsystem testShooter = new TestShooterSubsystem();
    public final static GyroSubsystem gyro = new GyroSubsystem();
    // ClimberSubsystem climber = new ClimberSubsystem();
    // driverController.a().onTrue(new StopAllCommand(shooterSubsystem,
    // indexerSubsystem, intakeSubsystem));
public RobotContainer() {
    configureDriverController(driveController);
}

    public void configureDriverController(CommandXboxController driverController) {
        driverController.a().onTrue(new StopAllCommand(testShooter, testIndexer, testIntake));
        driverController.x().onTrue(new IntakeCommand(testIntake));
        driverController.y().onTrue(new ShootCommand(testShooter));
        driverController.back().onTrue(new GyroCommand(gyro));
    }
}