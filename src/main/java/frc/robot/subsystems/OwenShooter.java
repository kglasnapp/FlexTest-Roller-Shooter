package frc.robot.subsystems;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import frc.robot.Robot;

public class OwenShooter {
    boolean twoMotors = true;
    boolean velocityControl = false;
    CANSparkBase motorR;
    CANSparkBase motorL;
    private RelativeEncoder encR;
    private RelativeEncoder encL;
    private Robot robot;

    public OwenShooter(Robot robot) {
        this.robot = robot;
        motorL = setupMotor(21);
        if (twoMotors) {
            motorR = setupMotor(23);
            encR = motorL.getEncoder();
        }
        // canCoder = new CANcoder(9, "RIO");
        encL = motorL.getEncoder();
    }

    CANSparkBase setupMotor(int id) {
        CANSparkBase motor = new CANSparkFlex(id, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        motor.setIdleMode(IdleMode.kBrake);
        motor.setSmartCurrentLimit((int) 10);
        return motor;
    }

    public void teleopPeriodicOwen() {
        // neo.periodic();
        Robot.count++;
        int pov = robot.cont.getPOV();
        double value = 0.0;
        double revs = 0;

        if (pov == 0) {
            value = 0.1;
            revs = 2000;
        } else if (pov == 90) {
            value = 0.25;
            revs = 3000;
        } else if (pov == 180) {
            value = 0.5;
            revs = 4000;
        } else if (pov == 270) {
            value = 0.7;
            revs = 5000;
        }
        controlMotor(motorL, "L", encL, revs, value);
        if (twoMotors) {
            controlMotor(motorR, "R", encR, revs, value);
        }
    }

    void controlMotor(CANSparkBase motor, String side, RelativeEncoder enc, double revs, double value) {
        if (velocityControl) {
            motor.getPIDController().setReference(-revs, CANSparkBase.ControlType.kVelocity);
        } else {
            motor.set(-value);
        }
        double velocity = enc.getVelocity();
        if (Robot.count % 20 == 0 && velocity > 0) {
            System.out.println("Vel " + side + ":" + velocity);
        }
    }
}
