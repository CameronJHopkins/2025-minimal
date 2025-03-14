package frc.robot;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

    private final CommandXboxController controller = new CommandXboxController(0);
    private final Trigger bigoneontheleft = controller.leftTrigger(0.131);
    private final Trigger abutton = controller.a();
    private final Trigger bigoneontheright = controller.rightTrigger(0.131);
    // private final Trigger xbutton = controller.x();
    private final Trigger bbutton = controller.b();
    private final Trigger Up = controller.povUp();
    // private final Trigger ybutton = controller.y();
    // private final Trigger Left = controller.povLeft();
    private final Trigger Down = controller.povDown();
    private final Trigger smalloneontheleft = controller.leftBumper();
    // private final Trigger Right = controller.povRight();
    // private final Trigger smalloneontheright = controller.rightBumper();

    static drive drive = new drive();
    static arm arm = new arm();

    static RobotContainer Container = new RobotContainer(arm, drive);

    
    public RobotContainer(arm arm, drive drive) {
      
        configureBindings();
        autopicker.AutoModeSelector(autopicker.getAutoChooser(), this, arm, drive);


    }

  private void configureBindings() {

      smalloneontheleft.onTrue(new brake().alongWith(new stop()));
      bigoneontheleft.whileTrue(new elevate(-0.2).withTimeout(2).andThen(new stop()));
      bigoneontheright.whileTrue(new elevate(0.2).withTimeout(2).andThen(new stop()));
      Down.onTrue(new move_arm(-0.2).withTimeout(2).andThen(new stop()));
      Up.onTrue(new move_arm(0.2).withTimeout(2).andThen(new stop()));
      abutton.onTrue(new claw(0.2).withTimeout(2).andThen(new stop()));
      bbutton.onTrue(new claw(-0.2).withTimeout(2).andThen(new stop()));

  }

  public static SequentialCommandGroup autonomous_command(){

        return autopicker.getAutoChooser().getSelected();
        
  }

}
