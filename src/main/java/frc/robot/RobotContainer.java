
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.ClimberSubsystem;

public class RobotContainer {
    public final static CommandXboxController driveController = new CommandXboxController(2);
    //public final static CommandXboxController operatorController = new CommandXboxController(3);
    //public final static XboxController operatorHID = operatorController.getHID();
    public final static XboxController driveHID = driveController.getHID();

    ClimberSubsystem  climber = new ClimberSubsystem();
}