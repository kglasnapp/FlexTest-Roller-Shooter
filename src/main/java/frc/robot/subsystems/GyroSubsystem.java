// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import static frc.robot.utilities.Util.logf;

import com.kauailabs.navx.frc.AHRS;

public class GyroSubsystem extends SubsystemBase {

  public final AHRS m_navx = new AHRS(); // NavX connected over MXP

  public GyroSubsystem() {
    logf("Start of Gyro Sustem\n");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Make sure that you declare this subsystem in RobotContainer.java
    double angle = m_navx.getAngle();
    SmartDashboard.putNumber("Angle", angle);
    getGyroscopeRotation();
  }

  /**
   * Sets the gyroscope angle to zero. This can be used to set the direction the
   * robot is currently facing to the
   * 'forwards' direction.
   */
  public void zeroGyroscope() {
    // FIXed Uncomment if you are using a NavX
    // TODO: calibrate magneto navx
    if (m_navx.isMagnetometerCalibrated()) {
      // We will only get valid fused headings if the magnetometer is calibrated
      // System.out.println("returning the angle FUSE ZERO from the robot:
      // "+m_navx.getAngle());
      // TODO need to adjust the gyro angle
      zeroNavx = m_navx.getFusedHeading();
    } else {
      zeroNavx = -90;
    }
    m_navx.zeroYaw();
    m_navx.setAngleAdjustment(90); // Will adjust angle for getAngle. Does not impact getYaw
    logf("zero Gyro Navs:%.2f fused headig:%.2f zeroNavx:%.2f\n", zeroNavx, m_navx.getFusedHeading(), zeroNavx);
  }

  double zeroNavx = 0.0;

  public Rotation2d getGyroscopeRotation() {
    // FIXed Remove if you are using a Pigeon
    // return Rotation2d.fromDegrees(m_pigeon.getFusedHeading());

    // FIXed Uncomment if you are using a NavX
    if (m_navx.isMagnetometerCalibrated()) {
      // // We will only get valid fused headings if the magnetometer is calibrated
      // System.out.println("returning the angle FUSE ZERO from the robot:
      // "+m_navx.getAngle());

      Rotation2d r = Rotation2d.fromDegrees(-m_navx.getFusedHeading() + zeroNavx);
      SmartDashboard.putNumber("Rot Cal", r.getDegrees());
      return r;
    }
    //
    // We have to invert the angle of the NavX so that rotating the robot
    // counter-clockwise makes the angle increase.
    // return Rotation2d.fromDegrees(360.0 - m_navx.getYaw());

    Rotation2d r = Rotation2d.fromDegrees(-m_navx.getYaw());
    SmartDashboard.putNumber("Rot NC", r.getDegrees());
    if (Robot.count % 500 == 0) {
      logf("Rotation 2D:%s\n", r.toString());
    }
    return r;
  }

  public double getGyroscopeRotationRate() {
    return -m_navx.getRate();
  }
}