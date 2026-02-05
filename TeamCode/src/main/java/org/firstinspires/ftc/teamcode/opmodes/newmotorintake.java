package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp

public class newmotorintake extends BaseRobot {
    // TeleOp Code

    // INTAKE add new motor intake hardware variable (See examples in BaseRobot)
    protected DcMotor intake;
    // INTAKE
    // need to override init method and add the new motor intake hardware mapping
    // (See init in BaseRobot)
    @Override
    public void init() {
        super.init();
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setDirection(DcMotor.Direction.REVERSE);
    }
    /**
     * If TeleOp was selected or defaulted to, the following will be active upon
     * pressing "play".
     */
    public void loop() {
        // Calling our methods while the OpMode is running
        splitStickArcadeDrive();
        setFlywheelVelocity();
        manualCoreHexAndIntakeControl();
        telemetry.addData("Flywheel Velocity", ((DcMotorEx) flywheel).getVelocity());
        telemetry.addData("Flywheel Power", flywheel.getPower());
        telemetry.update();
    }

    /**
     * Controls for the drivetrain. The robot uses a split stick stlye arcade drive.
     * Forward and back is on the left stick. Turning is on the right stick.
     */
    private void splitStickArcadeDrive() {
        float X;
        float Y;

        X = gamepad1.right_stick_x;
        Y = -gamepad1.left_stick_y;
            leftDrive.setPower(Y - X);
        rightDrive.setPower(Y + X);
    }

    /**
     * Manual control for the Core Hex powered feeder and the agitator servo in the
     * hopper
     */
    private void manualCoreHexAndIntakeControl() {
        // Manual control for the Core Hex intake
        if (gamepad1.cross) {
            coreHex.setPower(0.5);
        } else if (gamepad1.triangle) {
            coreHex.setPower(-0.5);
        }
        // Manual control for the hopper's servo
        if (gamepad1.dpad_left) {
            ((DcMotorEx) intake).setVelocity(1000);
        } else if (gamepad1.dpad_right) {
            ((DcMotorEx) intake).setVelocity(1000);
        }
    }

    /**
     * This if/else statement contains the controls for the flywheel, both manual
     * and auto.
     * Circle and Square will spin up ONLY the flywheel to the target velocity set.
     * The bumpers will activate the flywheel, Core Hex feeder, and servo to cycle a
     * series of balls.
     */
    private void setFlywheelVelocity() {
        if (gamepad1.options) {
            flywheel.setPower(-0.5);
        } else if (gamepad1.left_bumper) {
            // INTAKE add function to set intake velocity
            FAR_POWER_AUTO();
        } else if (gamepad1.right_bumper) {
            // INTAKE add function to set intake velocity
            BANK_SHOT_AUTO();
        } else if (gamepad1.circle) {
            ((DcMotorEx) flywheel).setVelocity(bankVelocity);
        } else if (gamepad1.square) {
            ((DcMotorEx) flywheel).setVelocity(maxVelocity);
        } else {
            ((DcMotorEx) flywheel).setVelocity(0);
            coreHex.setPower(0);
            // The check below is in place to prevent stuttering with the servo. It checks
            // if the intake is under manual control!
            if (!gamepad1.dpad_right && !gamepad1.dpad_left) {
                intake.setPower(0);
            }
        }
    }

    // INTAKE add function to set intake velocity (to be placed in
    // "setFlyWheelVelocity" and manual intake functions

    // INTAKE add function to manually set the intake velocity (to be placed in main
    // loop)

}
