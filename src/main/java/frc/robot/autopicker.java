package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class autopicker {
	
    static drive drive = new drive();
    static arm arm = new arm();

    private static SendableChooser<SequentialCommandGroup> Autopicker = new SendableChooser<>();
    
    static RobotContainer Container = new RobotContainer(arm, drive);
           
    public static void Shuffleboard(){

        ShuffleboardTab mainTab = Shuffleboard.getTab("Main");
  
        mainTab.add("AutoMode", autopicker.getAutoChooser()).withSize(2, 1).withPosition(0, 0);
        mainTab.addCamera("Skooble POV", "USB Camera 0", "null").withSize(4, 4).withPosition(4, 0);
	
    }


    public static void AutoModeSelector(SendableChooser<SequentialCommandGroup> autoChooser, RobotContainer Container, arm arm, drive drive) {
           
        Autopicker.setDefaultOption("picktheauto", auto(Container, drive));
        Autopicker.addOption("Go straight", drivestraight(Container, drive));
        
    }
    

    public static SendableChooser<SequentialCommandGroup> getAutoChooser( ) {

        return Autopicker;

    }

    private static SequentialCommandGroup auto(RobotContainer Container, drive drivesubsystem) {

        return new auto(Container, drivesubsystem);

    }

    private static SequentialCommandGroup drivestraight(RobotContainer Container, drive driveSubsystem) {

        return new drivestraight(Container, driveSubsystem);

    }

}