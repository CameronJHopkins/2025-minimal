package frc.robot;

public final class constants {

  public static class pid_constants {

    public static final float p = 0.0005f;
    public static final float i = 0f;
    public static final float d = 0f;

  }
  public static class elevator_constants {
    
    public static final int elevator_motor = 17;

    public static final double low_intake = 0;
    public static final double high_intake = 0;
    public static final double box_thingy = 0;
    public static final double net_thingy = 0;

  }

  public static class ball_thingy_constants {

    public static final int arm_motor = 20;
    public static final int claw_motor = 19;

    public static final double intake_speed = 0;
    public static final double hole_speed = 0;
    public static final double net_speed = 0;
    public static final double high_intake = 0;
    public static final double net_thing = 0;
    public static final double hole_thing = 0;
    public static final double low_intake = 0;

  }

  public static class drive_constants {

    public static final int front_left = 1;
	  public static final int front_right = 12;
    public static final int back_left = 2;
    public static final int back_right = 11;
    
  }
  
  public static class auto_constants {
    
    public static final float drive_distance = 60f;
    public static final int right_motor_turn_360 = 0;
    public static final int left_motor_turn_360 = 0;
    
  }
}
