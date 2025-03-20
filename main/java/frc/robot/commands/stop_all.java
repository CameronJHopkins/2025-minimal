package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

public class stop_all extends Command {

    private final elevator_subsystem elevator; 
    private final big_ball_thingy_subsystem big_ball_thingy;
    private final drive_subsystem drive;

    public stop_all(drive_subsystem drive_subsystem, elevator_subsystem elevator_subsystem, big_ball_thingy_subsystem big_ball_thingy_subsystem){

        this.big_ball_thingy = big_ball_thingy_subsystem;
        this.drive = drive_subsystem;
        this.elevator = elevator_subsystem;

        addRequirements(elevator, big_ball_thingy, drive);

    }

    @Override
    public void execute() {

        elevator.stop();
        big_ball_thingy.stop();
        drive.stop();

    }

    @Override
    public boolean isFinished(){

        return false;

    }
}