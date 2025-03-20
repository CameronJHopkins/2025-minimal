package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drive_subsystem;

public class drive_straight extends Command {

    private final drive_subsystem drive;
    private final float distance;

    public drive_straight(drive_subsystem drive_subsystem, float distance){

        this.distance = distance;
        this.drive = drive_subsystem;
        addRequirements(drive);

    }

    @Override
    public void execute() {

        drive.drive_straight(distance);

    }

    @Override
    public boolean isFinished(){

        return false;

    }
}
