package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

public class get_ball_from_high_level extends Command {

    private final elevator_subsystem elevator; 
    private final big_ball_thingy_subsystem big_ball_thingy;

    public get_ball_from_high_level(elevator_subsystem elevator_subsystem, big_ball_thingy_subsystem big_ball_thingy_subsystem){

        this.big_ball_thingy = big_ball_thingy_subsystem;
        this.elevator = elevator_subsystem;

        addRequirements(elevator, big_ball_thingy);

    }

    @Override
    public void execute() {

        elevator.move_to_high_intake();
        big_ball_thingy.move_arm_to_high_intake();
        big_ball_thingy.intake();

    }

    @Override
    public boolean isFinished(){

        return false;

    }
}