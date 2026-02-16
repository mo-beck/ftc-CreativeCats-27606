package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class ccautolongred extends BaseRobot {

    private ElapsedTime autoLaunchTimer = new ElapsedTime(15);
    private ElapsedTime autoDriveTimer = new ElapsedTime(5);

    private double WHEELS_INCHES_TO_TICKS = (28 * 5 * 3) / (3 * Math.PI);


    @Override
    protected void idle() {
        super.idle();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        //gototargetTag(RED?BLUE);
        doAuto();
    }
//Autonomous Code
//For autonomous, the robot will launch the pre-loaded 3 balls then back away from the goal, turn, and back up off the launch line.

    /**
     * For autonomous, the robot is using a timer and encoders on the drivetrain to move away from the target.
     * This method contains the math to be used with the inputted distance for the encoders, resets the elapsed timer, and
     * provides a check for it to run so long as the motors are busy and the timer has not run out.
     */
    private void autoDrive(double speed, int leftDistanceInch, int rightDistanceInch, int timeout_ms) {
        autoDriveTimer.reset();
        leftDrive.setTargetPosition((int) (leftDrive.getCurrentPosition() + leftDistanceInch * WHEELS_INCHES_TO_TICKS));
        rightDrive.setTargetPosition((int) (rightDrive.getCurrentPosition() + rightDistanceInch * WHEELS_INCHES_TO_TICKS));
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDrive.setPower(Math.abs(speed));
        rightDrive.setPower(Math.abs(speed));
        while ( (leftDrive.isBusy() || rightDrive.isBusy()) && autoDriveTimer.milliseconds() < timeout_ms) {
            idle();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Blue Alliance Autonomous
     * The robot will fire the pre-loaded balls until the 10 second timer ends.
     * Then it will back away from the goal and off the launch line.
     */
    private void doAuto() {
        telemetry.addData("RUNNING OPMODE", "Auto");
        telemetry.update();
        // Fire balls

        ((DcMotorEx) flywheel).setVelocity(0);
        coreHex.setPower(0);
        servo.setPower(0);
        // drive
        autoDrive(0.5, 40, 40, 5000);
        // Turn
        autoDrive(0.5, 7, -8, 5000);
        // drive
        autoDrive(0.5, 90, 90, 5000);
        autoLaunchTimer.reset();
        while (autoLaunchTimer.milliseconds() < 10000) {
            BANK_SHOT_AUTO();
            telemetry.addData("Launcher Countdown", autoLaunchTimer.seconds());
            telemetry.update();
        }
        // autoDrive(1, -50, -50, 5000);
    }
}
