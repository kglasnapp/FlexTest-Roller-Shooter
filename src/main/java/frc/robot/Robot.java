package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.TiltSubsystem;
import static frc.robot.utilities.Util.logf;

public class Robot extends TimedRobot {
  public XboxController cont = new XboxController(2);
  
public RobotContainer robotContainer = new RobotContainer();
Command cmd;
  

  //public IntakeSubsystem intake = new IntakeSubsystem(this);
  public static long count = 0;
  AnalogInput analog = new AnalogInput(0);
  LedSubsystem leds = new LedSubsystem();
  Encoder enc = new Encoder(0,1);

  @Override
  public void robotInit() {
    leds.setAllianceLeds();

     //robotContainer.climber.homeClimber();
    TiltSubsystem tilt = new TiltSubsystem(this);
  }

  @Override
  public void robotPeriodic() {
    count++;
    int encCount = enc.get();
    SmartDashboard.putNumber("Encoder", encCount);
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void disabledInit() {
    if (cmd != null) {
      logf("Executing disabled init %s\n", cmd.getName());
      cmd.cancel();
    }
    //hasBeenHomed = false;
   // robotContainer.climber.disableRobot();

  }


}