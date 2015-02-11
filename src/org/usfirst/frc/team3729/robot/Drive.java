package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Talon;

public class Drive extends Thread {
	Input _input;
	Robot _bot;
	
    private static Drive INSTANCE = null;
    
	private Talon leftMotor0;
    private Talon leftMotor1;
    private Talon rightMotor0;
    private Talon rightMotor1;
    private Talon centerMotor0;
    private Talon centerMotor1;
    
    private Drive() {
        leftMotor0 = new Talon(Params.port_l0);
        leftMotor1 = new Talon(Params.port_l1);
        rightMotor0 = new Talon(Params.port_r0);
        rightMotor1 = new Talon(Params.port_r1);
        centerMotor0 = new Talon(Params.port_c0);
        centerMotor1 = new Talon(Params.port_c1);
        
        _input = new Input();
    }
    
    public static Drive getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Drive();
        
        return INSTANCE;
    }
    public void run() {
    	Hdrive(_input.getAxis(0,0), _input.getAxis(0,1), _input.getAxis(1,0));
    }
    
    //Drive values for testing
    public void test() {
    	System.out.println("left : " + leftMotor0.get() + ", " + leftMotor1.get());
    	System.out.println("Right : " + rightMotor0.get() + ", " + rightMotor1.get());
    	System.out.println("Center : " + centerMotor0.get() + ", " + centerMotor1.get());
    	try {
			Thread.sleep(100);
		} catch (Exception e){
			System.out.println(e);
		}
    }
    
    //Drive Modes
    //!Tank Drive
    public void tank(double left, double right) {
        leftMotor0.set(left);
        leftMotor1.set(left);
        rightMotor0.set(right);
        rightMotor1.set(right);
    }    
    //!H Drive
    public void Hdrive(double x, double y, double z) {
        centerMotor0.set(z);
        centerMotor1.set(z);
        
    	double left = y-x;
        double right = y+x;
        left = Params.clamp(left, -.95, .95);
        right = Params.clamp(right, -.95, .95);        
        leftMotor0.set(-left);
        leftMotor1.set(-left);
        rightMotor0.set(right);
        rightMotor1.set(right);
    }
    //!Quad Drive
    public void Quad(double x, double y, double z){
    	this.Hdrive(z, y, x);
    }
    //!Stopped
    public void stopmotors() {
        leftMotor0.set(0.0);
        leftMotor1.set(0.0);
        rightMotor0.set(0.0);
        rightMotor1.set(0.0);
        centerMotor0.set(0.0);
        centerMotor1.set(0.0);
    }

}
