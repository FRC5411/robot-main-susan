package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PrimerMotors;

public class PrimerSubsystem extends SubsystemBase {
    private final TalonSRX hopperMotor;
    private final TalonSRX intakeCargoMotor;
    private boolean shouldRun;

    public PrimerSubsystem() {
        hopperMotor = new TalonSRX(PrimerMotors.kHopperPort);
        intakeCargoMotor = new TalonSRX(PrimerMotors.kShooterIntakePort);

        shouldRun = true;
        configMotors();

        System.out.print("INITAL STATUS OF SHOULDRUN:");
        System.out.println(shouldRun);

    }

    public Command togglePrimer() {
        System.out.print("SHOULD RUN STATUS:");
        System.out.println(shouldRun);
        if(shouldRun){
            shouldRun = false;
            System.out.println("STARTING MOTORS");
            return new ParallelCommandGroup(
                new InstantCommand(() -> runHopper()),
                new InstantCommand(() -> runShooterIntake())
            );
        } else {
            shouldRun = true;
            System.out.println("STOPPING MOTORS");
            return stopAll();
        }
    }

    public Command stopAll(){
        return new ParallelCommandGroup(
                new InstantCommand(() -> hopperMotor.set(ControlMode.PercentOutput, 0)),
                new InstantCommand(() -> intakeCargoMotor.set(ControlMode.PercentOutput, 0))
            );
    }


    public void runHopper() {
        hopperMotor.set(ControlMode.PercentOutput, PrimerMotors.kHopperSpeed);
    }

    public void runShooterIntake() {
        intakeCargoMotor.set(ControlMode.PercentOutput, PrimerMotors.kShooterIntakeSpeed);
    }

    public void configMotors() {
        TalonSRX[] allMotors = { hopperMotor, intakeCargoMotor };
        for (TalonSRX motor : allMotors) {
            motor.configFactoryDefault();
            motor.setNeutralMode(NeutralMode.Coast);
            motor.enableCurrentLimit(true);
        }
    }
}
