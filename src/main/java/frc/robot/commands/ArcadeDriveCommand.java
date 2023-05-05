package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDriveCommand extends CommandBase {
  
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private DoubleSupplier speed;
  private DoubleSupplier rotation;
  private DriveSubsystem robotDrive;
  private boolean isJoystick;

  public ArcadeDriveCommand(
    DoubleSupplier speed, DoubleSupplier rotation, 
    DriveSubsystem robotDrive, boolean isJoystick) {
        this.speed = speed;
        this.rotation = rotation;
        this.robotDrive = robotDrive;
        this.isJoystick = isJoystick;
        addRequirements(robotDrive);
  }

  @Override
  public void initialize() {
    System.out.println("🕹 INITALIZING arcade Command! 🕹");
  }

  @Override
  public void execute() {
    robotDrive.arcadeDrive(speed.getAsDouble(), rotation.getAsDouble(), isJoystick);
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("❌🕹 ENDING arcade Command! 🕹❌");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}