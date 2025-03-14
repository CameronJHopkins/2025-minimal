package frc.robot;

public final class constants {
    
    public static class drive_constants {

		public static final int front_left = 1;
		public static final int front_right = 12;
		public static final int back_left = 2;
		public static final int back_right = 11;

		public static float max_distance_per_tick = 9.64f;
		public static float rotation_to_ticks = 0.1167f;
	
	}

	public static class elevator_constants {
	
		public static final int elevator_motor = 20;
        public static float elevator_height = 133;

	}	

	public static class arm_constants {


		public static final int arm_motor = 19;
        public static final int claw = 17;

	}

	public static class pid_constants {

		public static final float p = 0.5f;
		public static final float i = 0.01f;
		public static final float d = 0.05f;

	}

	public static class auto_constants {

        public static final int driver_timer = 3;
        public static final double drive_speed = 0.2;

		
	}

}
