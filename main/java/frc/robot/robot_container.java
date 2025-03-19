// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.autos;
import frc.robot.commands.commands;
import frc.robot.subsystems.big_ball_thingy_subsystem;
import frc.robot.subsystems.drive_subsystem;
import frc.robot.subsystems.elevator_subsystem;

public class robot_container {

  private final drive_subsystem drive = new drive_subsystem();
  private final elevator_subsystem elevator = new elevator_subsystem();
  private final big_ball_thingy_subsystem ball_thing_subsystem = new big_ball_thingy_subsystem();

  private final CommandXboxController driver_controller = new CommandXboxController(0);
  private final CommandXboxController operator_controller = new CommandXboxController(1);

  public robot_container() {

    configureBindings();
    autos.AutoModeSelector(autos.get_auto_chooser(), drive, elevator, ball_thing_subsystem);

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

  public static SequentialCommandGroup get_autonomous_command() {

    return autos.get_auto_chooser().getSelected();

  }

}
