package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class autopilot extends Command {

    private double speed;
    private drive drive;

    public autopilot(drive drive, double speed) {
    
        this.speed = speed;
        this.drive = drive;

    }
    
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        drive.autopilot(speed);
        System.out.println("DRIVING STRAIGHT");

    }

    public void end(boolean interrupted) {

        CommandScheduler.getInstance().cancelAll();
        frc.robot.drive.brake();

    }

    @Override
    public boolean isFinished() {

        return false;

    }
}