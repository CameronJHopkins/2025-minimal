// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.big_ball_thingy_subsystem;
import frc.robot.subsystems.drive_subsystem;
import frc.robot.subsystems.elevator_subsystem;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static void drive_auto(drive_subsystem subsystem) {

    new commands.drive_straight(subsystem, 0);

  }

  public static void intake_auto(drive_subsystem drive_subsystem, elevator_subsystem elevator_subsystem, big_ball_thingy_subsystem big_ball_thingy_subsystem){

    new commands.intake_auto(drive_subsystem, elevator_subsystem, big_ball_thingy_subsystem);

  }

  private Autos() {

  }

  //TODO: set up autonomous selector
}
