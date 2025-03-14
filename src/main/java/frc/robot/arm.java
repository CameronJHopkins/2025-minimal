package frc.robot;


import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.arm_constants;
import frc.robot.constants.elevator_constants;

public class arm extends SubsystemBase{
    
    static SparkMax arm = new SparkMax(arm_constants.arm_motor, MotorType.kBrushless);
    static SparkMax claw = new SparkMax(arm_constants.claw, MotorType.kBrushless);
    static SparkMax elevator = new SparkMax(elevator_constants.elevator_motor, MotorType.kBrushless);

    public arm() {

    }

    public static void prepare_drive(){

        SparkMaxConfig arm_config = new SparkMaxConfig();
        SparkMaxConfig elevator_config = new SparkMaxConfig();
        SparkMaxConfig claw_config = new SparkMaxConfig();

        arm_config.idleMode(IdleMode.kBrake).inverted(false);
        elevator_config.idleMode(IdleMode.kBrake).inverted(false);
        claw_config.idleMode(IdleMode.kBrake).inverted(false);
         
        arm.configure(arm_config, null, null);
        claw.configure(claw_config, null, null);
        elevator.configure(elevator_config, null, null);

    }

    public static void elevate(double speed){

        elevator.set(speed);
      
    }

    public static void stop(){

        elevator.set(0);
        arm.set(0);
        claw.set(0);

    }

    public static void move_arm(double speed){

        arm.set(speed);

    }

    public static void claw(double speed){

        claw.set(speed);
    }

}
