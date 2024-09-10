package frc.robot.commands;

import static frc.robot.utilities.Util.logf;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.GyroSubsystem;

public class GyroCommand extends Command {
  GyroSubsystem gyro;
  int count = 0;

  public GyroCommand(GyroSubsystem gyro) {
    logf("Create Gyro Command\n");
    this.gyro = gyro;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    logf("Init Gyro Command\n");
    count = 0;
    gyro.zeroGyroscope();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    count++;
    double angle = gyro.m_navx.getAngle();
    boolean cal = gyro.m_navx.isMagnetometerCalibrated();
    double rotation = gyro.getGyroscopeRotation().getDegrees();
    double fused = gyro.m_navx.getFusedHeading();
    double yaw = gyro.m_navx.getYaw();

    logf("gyro command finishing angle:%.2f calibrated:%b rotation:%.2f fused:%.2f yaw:%.2f\n",
        angle, cal, rotation, fused, yaw);
    return count > 10;
  }
}
