package org.usfirst.frc.team3729.robot;

public class Params {
	//Port allocations
    //!Talons -PWM-
    public static final int port_l0 = 0;
    public static final int port_r0 = 1;
    public static final int port_l1 = 2;
    public static final int port_r1 = 3;
    public static final int port_c0 = 4;
    public static final int port_c1 = 5;
    public static final int port_shooter = 6;
    //!Relays -Relay-
    public static final int port_intake = 0;
    //!Limit Switches -Digital I/O-
    public static final int port_intakelimit = 0;
    
    //Adjustments
    //! Increment at which we ramp output from the axes
    public static final double ramp_increment = 0.05;   
    //!Exponential values
    public static final double expo = 0.3;
    //!Max/Min robot speeds
    public static final double MAX_SPEED = 0.85;
    public static final double MIN_SPEED = -0.85;
    
    //!Testing information
    public static final boolean testing_drive = false;
    public static final boolean testing_input = false;
    public static final boolean testing_mech = true;
    
    //Useful Methods
    //!Slow the change of a value
    public static double ramp2_0(double desired, double current, double increment) {
    	double output = current + (desired * increment);
    	return output;
    }
    //!Exponential Driving
    public static double expo(double x, double a) {
        return (a * (x * x * x) + (1 - a) * x);
    }
    //!Clamp values
    public static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        }
        return value;
    }
    
}

