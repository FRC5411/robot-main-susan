// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;


public class RobotContainer {
  private DriveSubsystem m_robotDrive;
  // private HopperSubsystem m_robotHopper;
  private final CommandXboxController m_driverController;

  
  
  public RobotContainer() {
    m_driverController = new CommandXboxController(ControllerConstants.kXboxControllerPort);
    
    m_robotDrive = new DriveSubsystem();
    // m_robotHopper = new HopperSubsystem();

    m_robotDrive.setDefaultCommand(new ArcadeDriveCommand(
      () -> m_driverController.getLeftY(),
      () -> m_driverController.getRightX(),
      m_robotDrive,
      true
    ));

    configureBindings();
  }

  private void configureBindings() {
    // m_driverController.a().onTrue(new InstantCommand(() -> m_robotHopper.startHopper()));
    // m_driverController.a().onFalse(new InstantCommand(() -> m_robotHopper.stopHopper()));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
