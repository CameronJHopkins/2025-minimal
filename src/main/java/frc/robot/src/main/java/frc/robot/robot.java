// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class robot extends TimedRobot {
  private Command autonomousCommand;

  private final robot_container robot_container;


  public robot() {

    robot_container = new robot_container();
  
  }

  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run();
  
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    autonomousCommand = robot_container.getAutonomousCommand();

    if (autonomousCommand != null) {

      autonomousCommand.schedule();
    
    }
  }

  @Override
  public void autonomousPeriodic() {}

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
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

}
