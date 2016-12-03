
package org.usfirst.frc.team5518.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.TalonSRX; 
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive drivetrain; //	instance of the RobotDrive class
	TalonSRX left, right; //	instances of TalonSRX speed controllers
	Joystick driveJoystick; //	instance of drive joystick
	
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    boolean lightToggle = true;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	/**
    	 * the PWM port on the roboRIO where the left TalonSRX is connected
    	 */
    	final int LEFT_PORT_NUMBER = 0;
    	
    	/**
    	 * the PWM port on the roboRIO where the right TalonSRX is connected
    	 */
    	final int RIGHT_PORT_NUMBER = 1;
    	
    	/**
    	 * the port on the drive station where the Joystick is connected
    	 */
    	final int JOYSTICK_PORT_NUMBER = 0;
    	
    	left = new TalonSRX(LEFT_PORT_NUMBER);
    	right = new TalonSRX(RIGHT_PORT_NUMBER);
    	
    	drivetrain = new RobotDrive(left, right);
    	
    	driveJoystick = new Joystick(JOYSTICK_PORT_NUMBER);
    	
    	JoystickButton A = new JoystickButton(driveJoystick, 1);
    	
    	A.whenPressed(new LightCommand());
    	
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drivetrain.arcadeDrive(driveJoystick);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
    class LightCommand extends Command {
    	DigitalOutput light = new DigitalOutput(0);  // This should the the 0 pin on the RoboRio
    	protected  void execute() {
    		if (lightToggle)
    			lightToggle = false;
    		else 
    			lightToggle = true;
    		
    		light.set(lightToggle);
    	}
		@Override
		protected void initialize() {
			// TODO Auto-generated method stub
			
		}
		@Override
		protected boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		protected void end() {
			// TODO Auto-generated method stub
			
		}
		@Override
		protected void interrupted() {
			// TODO Auto-generated method stub
			
		}
    	
    	
    }
}
