package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Hardware;
import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;

public class UltrasonicDrive extends Command {

	private Ultrasonic ultrasonic;
	private double distance;
	private final double SPEED = 0.4, BUFFER = 20;
	
	public UltrasonicDrive(double distance) {
		this.distance = distance;
		
		ultrasonic = Hardware.INSTANCE.frontUltrasonic;
	}
	
	@Override
	protected void initialize() {
		Robot.drive.setMode(false);
	}

	@Override
	protected void execute() {
		if(ultrasonic.getRangeInches() > distance) {
			Robot.drive.set(SPEED, SPEED);
		}
		else if(ultrasonic.getRangeInches() < distance) {
			Robot.drive.set(-SPEED, -SPEED);
		}
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(distance - ultrasonic.getRangeInches()) < BUFFER;
	}

	@Override
	protected void end() {
		Robot.drive.set(0, 0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.drive.set(0, 0);
	}

}