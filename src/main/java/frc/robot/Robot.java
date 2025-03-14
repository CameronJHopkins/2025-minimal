package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
public class Robot extends TimedRobot {

    private SequentialCommandGroup autonomouscommand;
    private static frc.robot.RobotContainer RobotContainer;

  
  @Override
    public void robotInit() {
    
        System.out.println("Good Morning");
        drive Drive = new drive();
        arm Arm = new arm();
        new RobotContainer(Arm, Drive);
        drive.prepare_drive();
        drive.differential_start();
        autopicker.Shuffleboard();
        autopicker.AutoModeSelector(autopicker.getAutoChooser(), RobotContainer, Arm, Drive);
        CameraServer.startAutomaticCapture();
        autopicker.getAutoChooser();
        new brake();
        CommandScheduler.getInstance().run();

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
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    
    autonomouscommand = frc.robot.RobotContainer.autonomous_command();

    if (autonomouscommand != null) {

      autonomouscommand.schedule();

    }

    else {

      System.out.println("Auto Is Null");

    }
      
    
  }

  @Override
  public void autonomousPeriodic() {

      
    drive.start_drive();
    CommandScheduler.getInstance().run();

  }

  @Override
  public void autonomousExit() {

      if (autonomouscommand != null){

          autonomouscommand.cancel();

      }

      else {

          System.out.println("Auto is null");
      }
  }

  @Override
  public void teleopInit() {

    
  }
  
  @Override
  public void teleopPeriodic() {
    
    drive.start_drive();   
    CommandScheduler.getInstance().run();

  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
