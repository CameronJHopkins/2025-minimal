// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.constants.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.elevator_subsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.commands;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class robot_container {
  // The robot's subsystems and commands are defined here...
  private final drive_subsystem drive = new drive_subsystem();
  private final elevator_subsystem elevator = new elevator_subsystem();
  private final big_ball_thingy_subsystem ball_thing_subsystem = new big_ball_thingy_subsystem();


  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driver_controller = new CommandXboxController(0);
  private final CommandXboxController operator_controller = new CommandXboxController(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public robot_container() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {

    Trigger left_bumper = new Trigger(operator_controller.leftBumper());
    Trigger right_bumper = new Trigger(operator_controller.rightBumper());
    Trigger left_trigger = new Trigger(operator_controller.leftTrigger());
    Trigger right_trigger = new Trigger(operator_controller.rightTrigger());

    Trigger driver_a = new Trigger(driver_controller.a());
    
    left_bumper.whileTrue(new commands.get_ball_from_low_level(elevator, ball_thing_subsystem));
    right_bumper.whileTrue(new commands.put_ball_in_hole_thingy(elevator, ball_thing_subsystem));
    left_trigger.whileTrue(new commands.get_ball_from_high_level(elevator, ball_thing_subsystem));
    right_trigger.whileTrue(new commands.put_ball_in_net_thingy(elevator, ball_thing_subsystem));

    driver_a.whileTrue(new commands.live_and_learn(drive));

  }

public Command getAutonomousCommand() {

  return null;
  // TODO Auto-generated method stub
}

}
