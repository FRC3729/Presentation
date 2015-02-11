package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends SampleRobot {

    Input _input;
    Drive _drive;
    Mechanisms _mech;
    Timer auto_timer;
    CameraServer server;
    Thread drive;
    Thread mech;

    protected void disabled()
    {
    	System.out.println("in Disabled");
    	_drive.stopmotors();
    	_mech.stopmotors();
    }

    protected void robotInit()
    {
        _input = Input.getInstance();
        _drive = Drive.getInstance();
        _mech = Mechanisms.getInstance();
        
        //Print banner
        System.out.println(" ______ ______ ______ ______\n|__    |      |__    |  __  |\n|__    |_     |    __|__    |\n|______| |____|______|______|\n");
        System.out.println("This robot complies with Asimov's Laws of Robotics:");
        System.out.println("\t~> 1. A robot may not injure a human being or,\n\t      through inaction, allow a human being to come to harm.");
        System.out.println("\t~> 2. A robot must obey the orders given to it by human beings,\n\t      except where such orders would conflict with the First Law.");
        System.out.println("\t~> 3. A robot must protect its own existence as long as\n\t      such protection does not conflict with the First or Second Laws.");
    }

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous(){
        System.out.println("In auto");
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl(){
        System.out.println("in OpControl");
        drive.start();
        mech.start();
        while (isEnabled())
        {
            //!Drive
//        	_drive.Hdrive(_input.getAxis(0,0), _input.getAxis(0,1), _input.getAxis(1,0));
            
            //!Mechanisms
//        	_mech.shoot();
//        	_mech.intake();
            
            //!Testing values
            if (Params.testing_mech) {_mech.test();}
            if (Params.testing_input) {_input.test(); Timer.delay(0.1);}
            if (Params.testing_drive) {_drive.test();}
        }
    }
    public void test(){
    	while (isTest() && isEnabled()) {
    		LiveWindow.run();
    		if (Params.testing_mech) {_mech.test(); Timer.delay(0.1);}
            if (Params.testing_input) {_input.test(); Timer.delay(0.1);}
            if (Params.testing_drive) {_drive.test(); Timer.delay(0.1);}
    	}
    }
}

    	