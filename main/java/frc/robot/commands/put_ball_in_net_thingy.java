package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

public class put_ball_in_net_thingy extends Command {

    private final elevator_subsystem elevator; 
    private final big_ball_thingy_subsystem big_ball_thingy;

    public put_ball_in_net_thingy(elevator_subsystem elevator_subsystem, big_ball_thingy_subsystem big_ball_thingy_subsystem){

        this.big_ball_thingy = big_ball_thingy_subsystem;
        this.elevator = elevator_subsystem;

        addRequirements(elevator, big_ball_thingy);

    }

    @Override
    public void execute() {

        elevator.move_to_net_thingy();
        big_ball_thingy.move_arm_to_net_thing();
        big_ball_thingy.score_in_net_thing();

    }

    @Override
    public boolean isFinished(){

        return false;

    }
}