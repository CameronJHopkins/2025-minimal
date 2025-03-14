// This is a command to stop all motors
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class stop extends Command {


    public stop() {}
    
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        frc.robot.arm.stop();

    }

    @Override
    public void end(boolean interrupted) {

        CommandScheduler.getInstance().cancelAll();

    }
}

