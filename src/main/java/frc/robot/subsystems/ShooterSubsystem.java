package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.functions.MotionParameters;
import frc.robot.functions.PIDGains;

// Will refactor once it works, please don't harrass my entire bloodline for how messy it is, I know its bad, its 3:11AM. Deal with it. -Taha
public class ShooterSubsystem extends SubsystemBase {
    double currentVelocity;
    private final double MAX_VELOCITY  = -21666;

    private TalonSRX shooterLead = new TalonSRX(6);
    private TalonSRX shooterFollow = new TalonSRX(7);
    private PIDGains shooterGains = new PIDGains(0, 0.05, 0.001, 0.7, 0.0504, 150);
    private MotionParameters shooterMotionParameters = new MotionParameters(0, 0, shooterGains);

    public ShooterSubsystem() {
        shooterLead.setInverted(true);
        shooterFollow.setInverted(false);

        shooterFollow.follow(shooterLead);

        shooterLead.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        shooterLead.configClosedloopRamp(0.25);
        
        configMotionParameters(shooterMotionParameters, shooterLead);
    }

    public double getVelocity() {
        return this.shooterLead.getSelectedSensorVelocity() / MAX_VELOCITY;

    }

    public double getPosition() {
        return this.shooterLead.getSelectedSensorPosition();
    }

    public double getCurrentDraw() {
        return this.shooterLead.getSupplyCurrent();
    }

    public double getVoltageOutput() {
        return this.shooterLead.getMotorOutputVoltage();
    }

    public void setPos(ControlMode controlMode, double setpoint) {
        shooterLead.set(controlMode, setpoint);
    }

    public void setPercentVelocity(double percentVelocity) {
        this.setPos(ControlMode.Velocity, this.MAX_VELOCITY * percentVelocity);
    }

    public void configMotionParameters(MotionParameters shooterParam, TalonSRX selectedMotor) {
        selectedMotor.configMotionAcceleration(shooterParam.getAcceleration());
        selectedMotor.configMotionCruiseVelocity(shooterParam.getCruiseVelocity());
        setPIDFGains(shooterParam.getGains(), selectedMotor);
    };

    public ErrorCode setPIDFGains(PIDGains gains, TalonSRX selectedMotor) {
        ErrorCode errorCode = ErrorCode.OK;
        ErrorCode[] errors = {
            selectedMotor.config_kP(gains.slot, gains.P), 
            selectedMotor.config_kI(gains.slot, gains.I), 
            selectedMotor.config_kD(gains.slot, gains.D), 
            selectedMotor.config_kF(gains.slot, gains.F), 
            selectedMotor.config_IntegralZone(gains.slot, gains.iZone)
        };

        for (ErrorCode error : errors) {
            if (error != ErrorCode.OK) return error;
        }
        
        return errorCode;
    }
}