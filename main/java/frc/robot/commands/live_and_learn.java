package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drive_subsystem;

public class live_and_learn extends Command {
    
    private final drive_subsystem drive;

    public live_and_learn(drive_subsystem drive_subsystem){

        this.drive = drive_subsystem;
        addRequirements(drive);

    }

    @Override
    public void execute() {

        drive.live_and_learn();

    }

    @Override
    public boolean isFinished(){

        return false;

    }
}