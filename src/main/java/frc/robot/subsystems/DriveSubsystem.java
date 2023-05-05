package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.DriveMotors;

public class DriveSubsystem extends SubsystemBase {

    private final WPI_TalonFX m_leftLeader;
    private final WPI_TalonFX m_rightLeader;
    private final WPI_TalonFX m_leftFollower;
    private final WPI_TalonFX m_rightFollower;
    private final DifferentialDrive robotDrive;
    

    public DriveSubsystem() {
        m_leftLeader = new WPI_TalonFX(DriveMotors.kLeftLeaderPort);
        m_rightLeader = new WPI_TalonFX(DriveMotors.kRightLeaderPort);
        m_leftFollower = new WPI_TalonFX(DriveMotors.kLeftFollowerPort);
        m_rightFollower = new WPI_TalonFX(DriveMotors.kRightFollowerPort);
        
        configMotors();

        m_leftFollower.follow(m_leftLeader);
        m_rightFollower.follow(m_rightLeader);

        robotDrive = new DifferentialDrive(m_leftLeader, m_rightLeader);

        // m_leftFollower.getSelectedSensorPosition();
    }

    public void configMotors(){
        WPI_TalonFX[] allMotors =  {m_leftLeader, m_rightLeader, m_leftFollower, m_rightFollower};
        for(WPI_TalonFX motor : allMotors){
            motor.configFactoryDefault();
            motor.setNeutralMode(NeutralMode.Brake);
            motor.configStatorCurrentLimit(DriveMotors.kDriveCurrentLimitAmps);
        }

        m_rightLeader.setInverted(true);
        m_rightFollower.setInverted(true);
    }
    
    public void arcadeDrive(double origSpeed, double origRotation, boolean isJoystick ) {
        // Good practice 👍
        double speed = origSpeed;
        double rotation = origRotation;

        // Deadzone
        if(isJoystick){
            if (Math.abs(speed) < ControllerConstants.kDeadZone) speed = 0.0;
            if (Math.abs(rotation) < ControllerConstants.kDeadZone) rotation = 0.0;
        }
        
        robotDrive.arcadeDrive(speed, rotation);
        robotDrive.feed();
      }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }
}
