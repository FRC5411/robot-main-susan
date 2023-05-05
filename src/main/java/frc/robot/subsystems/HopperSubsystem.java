package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HopperMotor;

public class HopperSubsystem extends SubsystemBase{
    private final TalonSRX hopperMotor;
    private boolean shouldRunHopper;
    
    public HopperSubsystem(){
        hopperMotor = new TalonSRX(HopperMotor.kHopperPort);
        shouldRunHopper = false;
    }

    public void startHopper(){
        shouldRunHopper = true;
    }

    public void stopHopper(){
        shouldRunHopper = false;
    }

    @Override
    public void periodic() {
        if(shouldRunHopper)
            hopperMotor.set(ControlMode.PercentOutput, HopperMotor.kHopperSpeed);
    }

    
}
