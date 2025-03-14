package frc.robot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class drivestraight extends SequentialCommandGroup {

    public drivestraight (RobotContainer robot, drive drive){

        System.out.println("Drive Straight");

        addCommands(new autopilot(drive, 0.2).withTimeout(5).andThen(new brake()));

    }

}