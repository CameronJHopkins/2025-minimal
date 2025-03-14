package frc.robot;


import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.drive_constants;

public class drive extends SubsystemBase{
    
    static SparkMax front_left = new SparkMax(drive_constants.front_left, MotorType.kBrushless);
    static SparkMax front_right = new SparkMax(drive_constants.front_right, MotorType.kBrushless);
    static SparkMax back_left = new SparkMax(drive_constants.back_left, MotorType.kBrushless);
    static SparkMax back_right = new SparkMax(drive_constants.back_right, MotorType.kBrushless);
    static DifferentialDrive robot_drive;
    static XboxController controller = new XboxController(0);

    public drive() {

        prepare_drive();
        robot_drive = new DifferentialDrive(front_left::set, front_right::set);
        differential_start();

    }

    public static void prepare_drive(){

        SparkMaxConfig back_left_config = new SparkMaxConfig();
        SparkMaxConfig front_left_config = new SparkMaxConfig();
        SparkMaxConfig back_right_config = new SparkMaxConfig();
        SparkMaxConfig front_right_config = new SparkMaxConfig();

        back_left_config.follow(drive_constants.front_left, false).idleMode(IdleMode.kCoast);
        back_right_config.follow(drive_constants.front_right, false).idleMode(IdleMode.kCoast);
        front_left_config.idleMode(IdleMode.kCoast).inverted(false);
        front_right_config.idleMode(IdleMode.kCoast).inverted(false);
         
        back_left.configure(back_left_config, null, null);
        back_right.configure(back_right_config, null, null);
        front_left.configure(front_left_config, null, null);
        front_right.configure(front_right_config, null, null);

    }

    public static void differential_start(){

        SendableRegistry.addChild(robot_drive, front_right);
        SendableRegistry.addChild(robot_drive, front_left);

    }

    public static void start_drive(){

        robot_drive.tankDrive(-controller.getRawAxis(1), controller.getRawAxis(5), true);

    }

    public static void brake(){
        
        front_left.set(0);
        front_right.set(0);
        CommandScheduler.getInstance().cancelAll();
    
    }

    public void autopilot(double speed){

        robot_drive.arcadeDrive(speed, 0, true);

    }

    public void autopilotback(){

        front_left.set(0.2);
        front_right.set(0.2);

    }

    public void autopilotleft(){

        front_left.set(0.3);
        front_right.set(-0.3);

    }
        
    public void autopilotright(){

        front_left.set(-0.3);
        front_right.set(0.3);

    }

    public void TurboBoost(){

        front_left.set(-10);
        front_right.set(-10);

    }

}
