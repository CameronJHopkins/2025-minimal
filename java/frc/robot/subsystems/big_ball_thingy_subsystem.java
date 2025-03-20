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

import frc.robot.constants.*;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@SuppressWarnings("unused")
public class big_ball_thingy_subsystem extends SubsystemBase {

  private static SparkMax arm_motor;
  private static SparkMax claw_motor;
  private static PIDController arm_pid_controller;
  private static PIDController claw_pid_controller;
  private static SparkClosedLoopController arm_closed_loop_controller;



  /** Creates a new big_ball_thingy_subsystem. */
  public big_ball_thingy_subsystem() {

    arm_motor = new SparkMax(ball_thingy_constants.arm_motor, MotorType.kBrushless);
    claw_motor = new SparkMax(ball_thingy_constants.claw_motor, MotorType.kBrushless);
    arm_pid_controller = new PIDController(pid_constants.p, pid_constants.i, pid_constants.d);
    claw_pid_controller = new PIDController(pid_constants.p, pid_constants.i, pid_constants.d);
    arm_closed_loop_controller = arm_motor.getClosedLoopController();

    SparkMaxConfig arm_config = new SparkMaxConfig();
    SparkMaxConfig claw_config = new SparkMaxConfig();

    arm_config.idleMode(IdleMode.kBrake).inverted(false).closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).maxMotion.maxAcceleration(1000).maxVelocity(2000);
    claw_config.idleMode(IdleMode.kBrake).inverted(false).closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).maxMotion.maxAcceleration(1000).maxVelocity(2000);

    arm_motor.configure(arm_config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    claw_motor.configure(claw_config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    
  }

  public void stop(){

    arm_motor.disable();
    claw_motor.disable();

  } 

  public void intake(){

    stop();
    claw_motor.set(ball_thingy_constants.intake_speed);

  }

  public void score_in_hole_thing(){

    stop();
    claw_motor.set(ball_thingy_constants.hole_speed);
    System.out.println("score in hole thing");

  }

  public void score_in_net_thing(){

    stop();
    claw_motor.set(ball_thingy_constants.net_speed);
    System.out.println("score in net thing");

  }

  public void move_arm_to_high_intake(){

    System.out.println("move arm to high intake");

    stop();
    arm_closed_loop_controller.setReference(ball_thingy_constants.high_intake, ControlType.kPosition, ClosedLoopSlot.kSlot0);

  }

  public void move_arm_to_low_intake(){

    System.out.println("move arm to low intake");

    stop();
    arm_closed_loop_controller.setReference(ball_thingy_constants.low_intake, ControlType.kPosition, ClosedLoopSlot.kSlot0);

  }
  
  public void move_arm_to_hole_thing(){

    System.out.println("move arm to hole thing");

    stop();
    arm_closed_loop_controller.setReference(ball_thingy_constants.hole_thing, ControlType.kPosition, ClosedLoopSlot.kSlot0);

  }

  public void move_arm_to_net_thing(){

    System.out.println("move arm to net thing");

    stop();
    arm_closed_loop_controller.setReference(ball_thingy_constants.net_thing, ControlType.kPosition, ClosedLoopSlot.kSlot0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
