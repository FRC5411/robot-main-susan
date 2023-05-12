package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PrimerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class PrimeShooterCommand extends CommandBase {

  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  private PrimerSubsystem robotPrimer;
  private ShooterSubsystem m_robotShooter;

  public PrimeShooterCommand(PrimerSubsystem robotPrimer, ShooterSubsystem m_robotShooter) {
    this.robotPrimer = robotPrimer;
    this.m_robotShooter = m_robotShooter;
    addRequirements(robotPrimer);
  }

  @Override
  public void initialize() {
    System.out.println("@ INITALIZING prime shooter command! @");
  }

  @Override
  public void execute() { 
    if (robotPrimer.shouldRun) {
      robotPrimer.runHopper();
      robotPrimer.runShooterIntake();
      m_robotShooter.shootAtSpeed(0);
    } else {
      m_robotShooter.stopShooters();
      robotPrimer.stopAll();
    }
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("@ ENDING prime shooter command! @");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}