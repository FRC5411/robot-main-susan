package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeMotor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IntakeSubsystem extends SubsystemBase {
    private final DoubleSolenoid intakePneumatic1; 
    private final DoubleSolenoid intakePneumatic2; 
    private TalonSRX intakeMotor;

    public IntakeSubsystem() {
        // intakePneumatic1 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 3, 1); // D
        // intakePneumatic2 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 2, 0); // U

        intakePneumatic1 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 1, 3); // D
        intakePneumatic2 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 0, 2); // U


        intakeMotor = new TalonSRX(IntakeMotor.kIntakeMotorPort);
    }

    public void startIntake(){
        intakeMotor.set(ControlMode.PercentOutput, IntakeMotor.kIntakeSpeed);
    }

    public void stopIntake(){
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }
    
    public void newExtend(){
        intakePneumatic1.set(kForward);
        intakePneumatic2.set(kForward);
        startIntake();
    }
    
    public void newRetract(){
        intakePneumatic1.set(kReverse);
        intakePneumatic2.set(kReverse);
        stopIntake();
    }

    public void closePneumatics(){
        intakePneumatic1.set(kOff);
        intakePneumatic2.set(kOff);
    }
}
