// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.utilities.Util.logf;

public class TestShooterSubsystem extends SubsystemBase {
  /** Creates a new ReplaceMeSubs`ystem. */
  public TestShooterSubsystem() {
    logf("Start of Test Shooter System\n");
  }

  public void stop() {
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Make sure that you declare this subsystem in RobotContainer.java
  }
}