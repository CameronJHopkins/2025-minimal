package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

public final class init {
  
  private init() {}

  public static void main(String... args) {
  
    RobotBase.startRobot(robot::new);
  
  }

}