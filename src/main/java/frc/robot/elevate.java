// This is a command to stop all motors
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class elevate extends Command {

    private double speed;

    public elevate(double speed) {
    
        this.speed = speed;

    }
    
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        frc.robot.arm.elevate(speed);

    }

    @Override
    public void end(boolean interrupted) {

        CommandScheduler.getInstance().cancelAll();
        frc.robot.arm.stop();

    }

    @Override
    public boolean isFinished() {

        return false;

    }
}

