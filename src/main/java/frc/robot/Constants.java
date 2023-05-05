// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

public final class Constants {
  public static class DriveConstants {
  }

  public static class ControllerConstants{
    public final static double kDeadZone = 0.1; 
    public final static int kXboxControllerPort = 9;
  }

  public static class DriveMotors {

  public static final StatorCurrentLimitConfiguration kDriveCurrentLimitAmps = 
    new StatorCurrentLimitConfiguration(
      true, 
      55, // Amps
      55, // Amps 
      100 // MS
    );

    public final static String kCANBus = "canivore"; 
    
    public final static int kLeftLeaderPort = 11;
    public final static int kRightLeaderPort = 10;
    public final static int kLeftFollowerPort = 13;
    public final static int kRightFollowerPort = 12;
  }

  public static class HopperMotor{
    public final static int kHopperPort = 5;
    public final static double kHopperSpeed = 0.25;
  }

  public static class IntakeMotor{
    public final static int kIntakeMotorPort = 5;
  }

  public static class ShooterMotors {
    public final static int kIntakeCargoPort = 5;
    public final static int kRotateShooterPort = 5;
    public final static int kShooterTopPort = 5;
    public final static int kShooterBottomPort = 5;
  }
}
