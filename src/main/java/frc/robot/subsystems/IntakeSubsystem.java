// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
// import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

// public class IntakeSubsystem extends SubsystemBase {
//     private final DoubleSolenoid intakePneumatic; 

//     public IntakeSubsystem() {
//         intakePneumatic = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
//     }
    
//     public Command armIn(){
//         return new InstantCommand(()->intakePneumatic.set(kForward));
//     }
    
//     public Command armOut(){
//         return new InstantCommand(()->intakePneumatic.set(kReverse));
//     }

//     public Command closePneumatics(){
//         return new InstantCommand(()->intakePneumatic.close());
//     }

//     @Override
//     public void periodic() {
//     }

//     @Override
//     public void simulationPeriodic() {
//     }
// }
