// This is a command to stop all motors
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class brake extends Command {

            
    public brake() {}
    
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        frc.robot.drive.brake();

    }

    @Override
    public void end(boolean interrupted) {

        CommandScheduler.getInstance().cancelAll();

    }
}

