package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

public class get_ball_from_low_level extends Command {

    private final elevator_subsystem elevator;
    private final big_ball_thingy_subsystem bigBallThingy;

    public get_ball_from_low_level(elevator_subsystem elevator, big_ball_thingy_subsystem bigBallThingy) {
        this.elevator = elevator;
        this.bigBallThingy = bigBallThingy;
        addRequirements(elevator, bigBallThingy);
    }

    @Override
    public void execute() {
        
        elevator.move_to_low_intake();
        bigBallThingy.move_arm_to_low_intake();
        bigBallThingy.intake();
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
