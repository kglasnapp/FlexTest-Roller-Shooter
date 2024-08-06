package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.TiltSubsystem;

public class Robot extends TimedRobot {
  public XboxController cont = new XboxController(2);
  //public IntakeSubsystem intake = new IntakeSubsystem(this);
  public static long count = 0;
  AnalogInput analog = new AnalogInput(0);
  LedSubsystem leds = new LedSubsystem();

  @Override
  public void robotInit() {
    leds.setAllianceLeds();
    TiltSubsystem tilt = new TiltSubsystem(this);
  }

  @Override
  public void robotPeriodic() {
    count++;
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
  }

}