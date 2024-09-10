package frc.robot.subsystems;

import static frc.robot.utilities.Util.logf;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

/**
 * REV Smart Motion Guide
 * 
 * The SPARK MAX includes a control mode, REV Smart Motion which is used to
 * control the position of the motor, and includes a max velocity and max
 * acceleration parameter to ensure the motor moves in a smooth and predictable
 * way. This is done by generating a motion profile on the fly in SPARK MAX and
 * controlling the velocity of the motor to follow this profile.
 * 
 * Since REV Smart Motion uses the velocity to track a profile, there are only
 * two steps required to configure this mode:
 * 1) Tune a velocity PID loop for the mechanism
 * 2) Configure the smart motion parameters
 * 
 * Tuning the Velocity PID Loop
 * 
 * The most important part of tuning any closed loop control such as the
 * velocity
 * PID, is to graph the inputs and outputs to understand exactly what is
 * happening.
 * For tuning the Velocity PID loop, at a minimum we recommend graphing:
 *
 * 1) The velocity of the mechanism (‘Process variable’)
 * 2) The commanded velocity value (‘Setpoint’)
 * 3) The applied output
 *
 * This example will use ShuffleBoard to graph the above parameters. Make sure
 * to
 * load the shuffleboard.json file in the root of this directory to get the full
 * effect of the GUI layout.
 */
public class IntakeSubsystem extends SubsystemBase {
    private IntakeMotor motor;  
    private PID_MAX pid = new PID_MAX();
    private final static int OVER_CURRENT = 30;
    private int INTAKE_MOTOR_ID = 16;


    public IntakeSubsystem() {
        this.motor = new IntakeMotor(INTAKE_MOTOR_ID, false);
    }

    class IntakeMotor {
        CANSparkFlex motor;
        SparkPIDController pidController;
        RelativeEncoder encoder;
        int id;

        IntakeMotor(int id, boolean inverted) {
            this.id = id;
            motor = new CANSparkFlex(id, MotorType.kBrushless);
            motor.restoreFactoryDefaults();
            motor.setInverted(inverted);
            setBrakeMode(false);

            motor.setSmartCurrentLimit((int) OVER_CURRENT);
            encoder = motor.getEncoder();

            pidController = motor.getPIDController();
            pid.PIDCoefficientsShoot(pidController);
            pid.PIDToMax();

            
            setShooterVelocity(0);
        }

        public void setShooterVelocity(double value) {
            if (value >= 0) {
                logf("Set shooter velocity:%.3f\n", value);
            }
            pidController.setReference(value * 6500, CANSparkBase.ControlType.kSmartVelocity);

        }

        double getVelocity() {
            return motor.getEncoder().getVelocity();
        }

        double getPosition() {
            return motor.getEncoder().getPosition();
        }

        void setSpeed(double power) {
            motor.set(power);
        }

        void setBrakeMode(boolean mode) {
            motor.setIdleMode(mode ? IdleMode.kBrake : IdleMode.kCoast);
            logf("For id:%d Brake mode:%s\n", id, motor.getIdleMode());
        }
    }

    int lastPOV = -1;

    @Override
    public void periodic() {
        if (Robot.count % 5 == 0) {
                   SmartDashboard.putNumber("Intake Speed", motor.getVelocity());
                   SmartDashboard.putNumber("Intake Pos", motor.getPosition());
        }
       
        int pov = RobotContainer.driveHID.getPOV();
    
        double value = 0;
        if ((lastPOV != pov) && (pov >= 0)) {
            if (pov == 0) {
                value = 0.0;
            }
            if (pov == 90) {

                value = 0.25;
            }
            if (pov == 180) {
                value = 0.65;
            }
            if (pov == 270) {
                value = 0.9;
            }
           motor.setSpeed(value);
          

            lastPOV = pov;
        }else if(lastPOV != pov){
            lastPOV = pov;
        }

        // if (robot.cont.getAButton()) {
        //     setAllShooterPower(.65);
        // }
        // if (robot.cont.getBButton()) {
        //     setAllShooterPower(0.7);
        // }
        // if (robot.cont.getYButton()) {
        //     setAllShooterPower(0.67);
        // }
        // if (robot.cont.getXButton()) {
        //     upperMotor.setSpeed(0);
        //     lowerMotor.setSpeed(0);
        // }
    }
}
