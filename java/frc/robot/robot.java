package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.autos;
import frc.robot.commands.commands;
import frc.robot.subsystems.big_ball_thingy_subsystem;
import frc.robot.subsystems.drive_subsystem;
import frc.robot.subsystems.elevator_subsystem;

public class robot extends TimedRobot {

  private Command autonomousCommand;
  private final drive_subsystem drive;
  private final elevator_subsystem elevator;
  private final big_ball_thingy_subsystem big_ball_thingy;

  public robot() {

    drive = new drive_subsystem();
    elevator = new elevator_subsystem();
    big_ball_thingy = new big_ball_thingy_subsystem();
  
  }

  @Override
  public void robotInit() {

    autos.Shuffleboard();
    autos.AutoModeSelector(autos.get_auto_chooser(), drive, elevator, big_ball_thingy);
    autos.get_auto_chooser();

  }

  @Override
  public void robotPeriodic() {

    drive.start_drive();
    CommandScheduler.getInstance().run();
  
  }

  @Override
  public void disabledInit() {

    CommandScheduler.getInstance().cancelAll();

  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    autonomousCommand = robot_container.get_autonomous_command();

    if (autonomousCommand != null) {

      autonomousCommand.schedule();
    
    }
  }

  @Override
  public void autonomousPeriodic() {

    new commands.get_ball_from_low_level(elevator, big_ball_thingy);

  }

  @Override
  public void teleopInit() {

    if (autonomousCommand != null) {

      autonomousCommand.cancel();
    
    }
  
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {

    CommandScheduler.getInstance().cancelAll();

  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {

    autos.get_auto_chooser();

  }

  @Override
  public void simulationPeriodic() {}

}
