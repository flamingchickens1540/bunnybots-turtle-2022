package org.team1540.elmo.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.elmo.Constants;

public class DriveTrain extends SubsystemBase {
    private TalonSRX frontRight = new TalonSRX(Constants.RIGHT_FRONT_MOTOR_CAN_ID);
    private TalonSRX frontLeft = new TalonSRX(Constants.LEFT_FRONT_MOTOR_CAN_ID);
    private TalonSRX backRight = new TalonSRX(Constants.RIGHT_BACK_MOTOR_CAN_ID);
    private TalonSRX backLeft = new TalonSRX(Constants.LEFT_BACK_MOTOR_CAN_ID);
    private AHRS navx = new AHRS(Port.kMXP);

    public DriveTrain() {
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
        frontLeft.setInverted(true);
        backLeft.setInverted(true);
        frontRight.setInverted(false);
        backRight.setInverted(false);

        frontRight.setNeutralMode(NeutralMode.Brake);
        frontLeft.setNeutralMode(NeutralMode.Brake);
    }

    public void stop() {
        setPercent(0, 0);
    }

    public void setPercent(double leftPercent, double rightPercent) {
        frontLeft.set(ControlMode.PercentOutput, leftPercent);
        frontRight.set(ControlMode.PercentOutput, rightPercent);
    }

    @Override
    public void periodic() {
        super.periodic();
        SmartDashboard.putNumber(getName()+"/navx-angle", getAngle());
    }


    // sensors
    public double getAngle() {
        return navx.getAngle();
    }
    public void resetGyro() {
        navx.reset();
    }
}