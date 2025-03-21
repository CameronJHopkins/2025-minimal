// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.elevator_constants;

public class elevator_subsystem extends SubsystemBase {
  /** Creates a new elevator_subsystem. */

  private final SparkMax elevator_motor;
  private final PIDController pid_controller;

  private SparkClosedLoopController elevator_closed_loop_controller;
  
  public elevator_subsystem() {

    elevator_motor = new SparkMax(elevator_constants.elevator_motor, MotorType.kBrushless);
    pid_controller = new PIDController(elevator_constants.p, elevator_constants.i, elevator_constants.d);
    elevator_closed_loop_controller = elevator_motor.getClosedLoopController();
    pid_controller.setTolerance(0.05);

    SparkMaxConfig elevator_config = new SparkMaxConfig();
    elevator_config.idleMode(IdleMode.kBrake).inverted(false).closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).maxMotion.maxVelocity(2000).maxAcceleration(1000);
    elevator_motor.configure(elevator_config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void stop(){

    elevator_motor.disable();
    
  }

  public void move_to_low_intake(){

    stop();
    elevator_closed_loop_controller.setReference(elevator_constants.low_intake, ControlType.kPosition, ClosedLoopSlot.kSlot0);

  }

  public void move_to_high_intake(){

    stop();
    elevator_closed_loop_controller.setReference(elevator_constants.high_intake, ControlType.kPosition, ClosedLoopSlot.kSlot0);

  }

  public void move_to_box_thingy(){

    stop();
    elevator_closed_loop_controller.setReference(elevator_constants.box_thingy, ControlType.kPosition, ClosedLoopSlot.kSlot0);

  }

  public void move_to_net_thingy(){

    stop();
    elevator_closed_loop_controller.setReference(elevator_constants.net_thingy, ControlType.kPosition, ClosedLoopSlot.kSlot0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
