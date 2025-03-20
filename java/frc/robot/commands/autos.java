package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.commands.drive_straight;
import frc.robot.commands.commands.stop_all;
import frc.robot.constants.auto_constants;
import frc.robot.subsystems.big_ball_thingy_subsystem;
import frc.robot.subsystems.drive_subsystem;
import frc.robot.subsystems.elevator_subsystem;

public final class autos {

  private static SendableChooser<SequentialCommandGroup> Autopicker = new SendableChooser<>();

  public static SequentialCommandGroup nothing_auto(drive_subsystem drive, elevator_subsystem elevator, big_ball_thingy_subsystem big_ball_thingy){

    return new SequentialCommandGroup(new stop_all(drive, elevator, big_ball_thingy));

  }

  public static SequentialCommandGroup drive_auto(drive_subsystem subsystem) {

    return new SequentialCommandGroup(new drive_straight(subsystem, auto_constants.drive_distance));

  }

  public static SequentialCommandGroup intake_auto(drive_subsystem drive_subsystem, elevator_subsystem elevator_subsystem, big_ball_thingy_subsystem big_ball_thingy_subsystem){

    return new SequentialCommandGroup(new commands.drive_straight(drive_subsystem, auto_constants.drive_distance), new commands.get_ball_from_low_level(elevator_subsystem, big_ball_thingy_subsystem));

  }

  public static SequentialCommandGroup autonomous_command(drive_subsystem drive, elevator_subsystem elevator, big_ball_thingy_subsystem big_ball_thingy){

    return intake_auto(drive, elevator, big_ball_thingy);

  }

  public static void Shuffleboard(){

        ShuffleboardTab mainTab = Shuffleboard.getTab("Main");
  
        mainTab.add("AutoMode", get_auto_chooser()).withSize(5, 3).withPosition(0, 0);
        mainTab.addCamera("Skooble POV", "USB Camera 0", "null").withSize(6, 6).withPosition(6, 0);
	
    }


    public static void AutoModeSelector(SendableChooser<SequentialCommandGroup> autoChooser, drive_subsystem drive, elevator_subsystem elevator, big_ball_thingy_subsystem big_ball_thingy) {
           
        Autopicker.setDefaultOption("No Auto", nothing_auto( drive, elevator, big_ball_thingy));
        Autopicker.addOption("Go straight", drive_auto(drive));
        Autopicker.addOption("Drive and Intake", intake_auto(drive, elevator, big_ball_thingy));

    }
    

    public static SendableChooser<SequentialCommandGroup> get_auto_chooser( ) {

        return Autopicker;

    }


}