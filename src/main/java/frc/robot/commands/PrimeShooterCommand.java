package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.PrimerSubsystem;

public class PrimeShooterCommand extends CommandBase {
  
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private PrimerSubsystem robotPrimer;

  public PrimeShooterCommand(PrimerSubsystem robotPrimer) {
        this.robotPrimer = robotPrimer;
        addRequirements(robotPrimer);
  }

  @Override
  public void initialize() {
    System.out.println("@ INITALIZING prime shooter command! @");
  }

  @Override
  public void execute() {
    new ParallelCommandGroup(
        new InstantCommand(() -> robotPrimer.runHopper()), 
        new InstantCommand(() -> robotPrimer.runShooterIntake())
    );
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