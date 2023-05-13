package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.GlobalVars.DriveConfig;
// import frc.robot.Constants;
import frc.robot.Constants.ShooterMotors;

public class ShooterSubsystem extends SubsystemBase {

    public TalonFX m_leftShooter;
    public TalonFX m_rightShooter;
    public WPI_TalonSRX m_hoodMotor;

    public ShooterSubsystem() {
        m_leftShooter = new TalonFX(ShooterMotors.kShooterLeftPort);
        m_rightShooter = new TalonFX(ShooterMotors.kShooterRightPort);
        m_hoodMotor = new WPI_TalonSRX(ShooterMotors.kHoodPort);

        configMotors();

        m_leftShooter.follow(m_rightShooter);
        
    }

    public void shootNow() {
        m_rightShooter.set(ControlMode.PercentOutput, DriveConfig.gDriverSpeed);
    }

    public void haltHood(){
        m_hoodMotor.set(0);
    }

    public void extendHood(){
        m_hoodMotor.set(ShooterMotors.kHoodSpeed);
    }

    public void retractHood(){
        m_hoodMotor.set(-ShooterMotors.kHoodSpeed * 0.5);
    }

    // public void shootAtSpeed(double vMeters) {
    //     m_rightShooter.set(ControlMode.Velocity, MPSToTicks(vMeters));
    // }

    public void stopShooters(){
        m_rightShooter.set(ControlMode.PercentOutput, 0);
    }

    public void configMotors() {
        TalonFX[] allMotors = { m_leftShooter, m_rightShooter };
        for (TalonFX motor : allMotors) {
            motor.configFactoryDefault();
            motor.setNeutralMode(NeutralMode.Coast);
            // motor.configStatorCurrentLimit(ShooterMotors.kShooterCurrentLimitAmps);
            // motor.config_kP(0, 0.012865);//2.6363
            // motor.config_kF(0, 0.1421);
        }

        m_rightShooter.setInverted(true);
        m_hoodMotor.setInverted(true);
    }

    // public double MPSToTicks(double velocity) {
    //     return (velocity / Constants.ShooterMotors.kFlyWheelCirc) * 2048 / 10;
    // }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Speed", 1000 - m_rightShooter.getSelectedSensorVelocity());
    }
}