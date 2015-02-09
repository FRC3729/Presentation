package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Mechanisms {
	
	private Talon shooter;
	private Relay intake;
	private DigitalInput limit_intake;
	Input _input;
	
	private double shooter_speed;
	private boolean shooter_state;
	
	private static Mechanisms INSTANCE = null;
	
	private Mechanisms() {
		shooter = new Talon(Params.port_shooter);
		intake = new Relay(Params.port_intake);
		limit_intake = new DigitalInput(Params.port_intakelimit);
		_input = new Input();
		
		shooter_state = false;
		shooter_speed = 0.55;
	}
	
	public static Mechanisms getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Mechanisms();
        
        return INSTANCE;
    }
	public void test() {
		System.out.println("Shooter power: " + shooter_state);
		System.out.println("Shooter speed: " + shooter_speed);
		System.out.println("Intake limit: " + limit_intake.get());
	}
	
	public void shoot() {
		if (_input.getButton(1, 4)) {
			shooter_state = true;
		} else if (_input.getButton(1, 5)) {
			shooter_state = false;
		} 
		if (_input.getButton(1, 3) && shooter_speed <= 1.0) {
			shooter_speed = shooter_speed + .01;
		} else if (_input.getButton(1, 2) && shooter_speed >= -1.0) {
			shooter_speed = shooter_speed - .01;
		}
		if (shooter_state) {
			setspeed(shooter_speed);
		} else {
			setspeed(0.0);
		}
	}
	private void setspeed(double speed) {
		shooter.set(speed);
	}
	
	public void intake() {
		if (_input.getButton(1, 1)) {
			intake.set(Relay.Value.kForward);
		} else if (limit_intake.get()) {
			intake.set(Relay.Value.kReverse);
		} else {
			intake.set(Relay.Value.kOff);
		}
	}
	
	public void stop() {
		shooter.set(0.0);
		intake.set(Relay.Value.kOff);
	}
}

