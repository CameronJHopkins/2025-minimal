package frc.robot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class auto extends SequentialCommandGroup {
	

    public auto (RobotContainer robot, drive drive){

        System.out.println("Braking...");

        addCommands(new brake());

    }

    
}