package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.constants.auto_constants;
import frc.robot.subsystems.big_ball_thingy_subsystem;
import frc.robot.subsystems.drive_subsystem;
import frc.robot.subsystems.elevator_subsystem;

/** Add your docs here. 
 * 
 * Docs?  What are docs?
*/

public class commands {

    public static class get_ball_from_low_level extends Command {

        private final elevator_subsystem elevator;
        private final big_ball_thingy_subsystem bigBallThingy;
    
        public get_ball_from_low_level(elevator_subsystem elevator, big_ball_thingy_subsystem bigBallThingy) {

            System.out.println("get ball from low level command");

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

    public static class put_ball_in_hole_thingy extends Command {

        private final elevator_subsystem elevator; 
        private final big_ball_thingy_subsystem big_ball_thingy;

        public put_ball_in_hole_thingy(elevator_subsystem elevator_subsystem, big_ball_thingy_subsystem big_ball_thingy_subsystem){

            this.big_ball_thingy = big_ball_thingy_subsystem;
            System.out.println("put ball in hole thingy command");

            this.elevator = elevator_subsystem;

            addRequirements(elevator, big_ball_thingy);

        }

        @Override
        public void execute() {

            elevator.move_to_box_thingy();
            big_ball_thingy.move_arm_to_hole_thing();
            big_ball_thingy.score_in_hole_thing();

        }

        @Override
        public boolean isFinished(){

            return false;

        }
    }
    
    public static class get_ball_from_high_level extends Command {

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
            System.out.println("get ball from high level command");

            big_ball_thingy.move_arm_to_high_intake();
            big_ball_thingy.intake();

        }

        @Override
        public boolean isFinished(){

            return false;

        }
    }

    public static class put_ball_in_net_thingy extends Command {

        private final elevator_subsystem elevator; 
        private final big_ball_thingy_subsystem big_ball_thingy;

        public put_ball_in_net_thingy(elevator_subsystem elevator_subsystem, big_ball_thingy_subsystem big_ball_thingy_subsystem){

            this.big_ball_thingy = big_ball_thingy_subsystem;
            this.elevator = elevator_subsystem;

            addRequirements(elevator, big_ball_thingy);

        }

        @Override
        public void execute() {

            System.out.println("put ball in net thingy command");

            elevator.move_to_net_thingy();
            big_ball_thingy.move_arm_to_net_thing();
            big_ball_thingy.score_in_net_thing();

        }

        @Override
        public boolean isFinished(){

            return false;

        }
    }

    public static class stop_all extends Command {

        private final elevator_subsystem elevator; 
        private final big_ball_thingy_subsystem big_ball_thingy;
        private final drive_subsystem drive;

        public stop_all(drive_subsystem drive_subsystem, elevator_subsystem elevator_subsystem, big_ball_thingy_subsystem big_ball_thingy_subsystem){

            this.big_ball_thingy = big_ball_thingy_subsystem;
            System.out.println("stop all command");

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

    public static class live_and_learn extends Command {

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

    public static class drive_straight extends Command {

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

    public static class intake_auto extends Command {

        private final drive_subsystem drive;
        private final elevator_subsystem elevator;
        private final big_ball_thingy_subsystem big_ball_thingy;

        public intake_auto(drive_subsystem drive_subsystem, elevator_subsystem elevator_subsystem, big_ball_thingy_subsystem big_ball_thingy_subsystem){

            this.elevator = elevator_subsystem;
            this.big_ball_thingy = big_ball_thingy_subsystem;
            this.drive = drive_subsystem;
            addRequirements(drive, elevator, big_ball_thingy);

        }

        @Override
        public void execute() {

            new SequentialCommandGroup(new drive_straight(drive, auto_constants.drive_distance), new get_ball_from_low_level(elevator, big_ball_thingy));
            
        }

        @Override
        public boolean isFinished(){

            return false;

        }
    }
    
}
