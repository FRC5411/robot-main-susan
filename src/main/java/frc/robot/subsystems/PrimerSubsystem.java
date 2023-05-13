package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PrimerMotors;

public class PrimerSubsystem extends SubsystemBase {
    private final WPI_TalonSRX hopperMotor;
    private final WPI_TalonSRX intakeCargoMotor;
    public boolean shouldRun;

    public PrimerSubsystem() {
        hopperMotor = new WPI_TalonSRX(PrimerMotors.kHopperPort);
        intakeCargoMotor = new WPI_TalonSRX(PrimerMotors.kShooterIntakePort);

        shouldRun = false;

        configMotors();

        intakeCargoMotor.setInverted(true);
    }

    public void stopAll(){
        hopperMotor.set(0);
        intakeCargoMotor.set(0);
    }


    public void runHopper() {
        hopperMotor.set(PrimerMotors.kHopperSpeed);
    }

    public void runShooterIntake() {
        intakeCargoMotor.set(PrimerMotors.kShooterIntakeSpeed);
    }

    public void stopHopper(){
        hopperMotor.set(0);
    }

    public void stopCargoIntake(){
        intakeCargoMotor.set(0);
    }

    public void configMotors() {
        WPI_TalonSRX[] allMotors = { hopperMotor, intakeCargoMotor };
        for (WPI_TalonSRX motor : allMotors) {
            motor.configFactoryDefault();
            motor.setNeutralMode(NeutralMode.Coast);
        }
    }
}
