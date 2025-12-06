package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BaseRobot extends OpMode {

    protected DcMotor flywheel;
    protected DcMotor coreHex;
    protected DcMotor leftDrive;
    protected CRServo servo;
    protected DcMotor rightDrive;


    public static final int bankVelocity = 1300;
    public static final int farVelocity = 1900;
    public static final int maxVelocity = 2200;

    @Override
    public void init(){
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        coreHex = hardwareMap.get(DcMotor.class, "coreHex");
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        servo = hardwareMap.get(CRServo.class, "servo");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");

        // Establishing the direction and mode for the motors
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel.setDirection(DcMotor.Direction.REVERSE);
        coreHex.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        //Ensures the servo is active and ready
        servo.setPower(0);
    }
    @Override
    public void loop(){
        idle();
    }
    //Automatic Flywheel controls used in Auto and TeleOp

    /**
     * The bank shot or near velocity is intended for launching balls touching or a few inches from the goal.
     * When running this function, the flywheel will spin up and the Core Hex will wait before balls can be fed.
     * The servo will spin until the bumper is released.
     */
    public void BANK_SHOT_AUTO() {
        ((DcMotorEx) flywheel).setVelocity(bankVelocity);
        servo.setPower(1);
        if (((DcMotorEx) flywheel).getVelocity() >= bankVelocity - 100) {
            coreHex.setPower(1);
        } else {
            coreHex.setPower(0);
        }
    }

    /**
     * The far power velocity is intended for launching balls a few feet from the goal. It may require adjusting the deflector.
     * When running this function, the flywheel will spin up and the Core Hex will wait before balls can be fed.
     * The servo will spin until the bumper is released.
     */
    public void FAR_POWER_AUTO() {
        ((DcMotorEx) flywheel).setVelocity(farVelocity);
        servo.setPower(1);
        if (((DcMotorEx) flywheel).getVelocity() >= farVelocity - 100) {
            coreHex.setPower(1);
        } else {
            coreHex.setPower(0);
        }
    }

    protected void idle(){
        Thread.yield();
    }

}
