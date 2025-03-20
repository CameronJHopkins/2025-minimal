package frc.robot.subsystems;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.constants.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class drive_subsystem extends SubsystemBase {

  SparkMax front_left;
  SparkMax back_left;
  SparkMax front_right;
  SparkMax back_right;

  SparkClosedLoopController back_left_closed_loop_controller;
  SparkClosedLoopController front_left_closed_loop_controller;
  SparkClosedLoopController front_right_closed_loop_controller;
  SparkClosedLoopController back_right_closed_loop_controller;

  DifferentialDrive drive;
  
  XboxController driver_controller;

  public drive_subsystem() {

    front_left = new SparkMax(drive_constants.front_left, MotorType.kBrushless);
    front_right = new SparkMax(drive_constants.front_right, MotorType.kBrushless);
    back_left = new SparkMax(drive_constants.back_left, MotorType.kBrushless);
    back_right = new SparkMax(drive_constants.back_right, MotorType.kBrushless);

    back_left_closed_loop_controller = back_left.getClosedLoopController();
    back_right_closed_loop_controller = back_right.getClosedLoopController();
    front_left_closed_loop_controller = front_left.getClosedLoopController();
    front_right_closed_loop_controller = front_right.getClosedLoopController();

    SparkMaxConfig front_left_config = new SparkMaxConfig(); 
    SparkMaxConfig front_right_config = new SparkMaxConfig(); 
    SparkMaxConfig back_left_config = new SparkMaxConfig(); 
    SparkMaxConfig back_right_config = new SparkMaxConfig(); 

    front_left_config.idleMode(IdleMode.kCoast).inverted(true).closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).pid(pid_constants.p, pid_constants.i, pid_constants.d).maxMotion.maxAcceleration(1000).maxVelocity(500);
    back_left_config.idleMode(IdleMode.kCoast).follow(front_left, false).closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).pid(pid_constants.p, pid_constants.i, pid_constants.d).maxMotion.maxAcceleration(1000).maxVelocity(500);
    front_right_config.idleMode(IdleMode.kCoast).inverted(false).closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).pid(pid_constants.p, pid_constants.i, pid_constants.d).maxMotion.maxAcceleration(1000).maxVelocity(500);
    back_right_config.idleMode(IdleMode.kCoast).follow(front_right, false).closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).pid(pid_constants.p, pid_constants.i, pid_constants.d).maxMotion.maxAcceleration(1000).maxVelocity(500);

    front_left.configure(front_right_config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    back_left.configure(back_left_config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    front_right.configure(front_right_config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    back_right.configure(back_right_config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    drive = new DifferentialDrive(front_left::set, front_right::set);

    driver_controller = new XboxController(0);
    
  }
  
  public void start_drive(){

    double unmodified_x = driver_controller.getRightX();
    double unmodified_y = driver_controller.getLeftX();

    double modified_x = unmodified_x / 3.5;
    double modified_y = unmodified_y / 3.5;

    if (driver_controller.getRightBumperButton()) {

      System.out.print("Shift Is Pressed\n");
      System.out.println("Modified x-axis value: " + modified_x);
      System.out.println("Modified y-axis value: " + modified_y);
      drive.arcadeDrive(modified_x, -modified_y, true);

    } else {

      drive.arcadeDrive(unmodified_x, -unmodified_y, true);
   
    }
  }
  

  public void drive_straight(float distance){

    front_left_closed_loop_controller.setReference(distance, ControlType.kPosition, ClosedLoopSlot.kSlot0, 30);
    front_right_closed_loop_controller.setReference(distance, ControlType.kPosition, ClosedLoopSlot.kSlot0, 30);

  }

  public void stop(){

    front_left_closed_loop_controller.setReference(0, ControlType.kVelocity);
    front_right_closed_loop_controller.setReference(0, ControlType.kVelocity);

  }

  public void live_and_learn(){

    front_left_closed_loop_controller.setReference(1000000, ControlType.kVelocity);
    front_right_closed_loop_controller.setReference(1000000, ControlType.kVelocity);

  }

  public void turn(float degrees){

    front_left_closed_loop_controller.setReference(degrees * (auto_constants.left_motor_turn_360 / 360), ControlType.kPosition);
    front_right_closed_loop_controller.setReference(degrees * (auto_constants.right_motor_turn_360 / 360), ControlType.kPosition);

  } 

  @Override
  public void periodic() {

    start_drive();

  }

}
