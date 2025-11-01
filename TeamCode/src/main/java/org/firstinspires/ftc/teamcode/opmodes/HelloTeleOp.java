package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Hello TeleOp", group = "Examples")
public class HelloTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("LX", gamepad1.left_stick_x);
            telemetry.addData("LY", gamepad1.left_stick_y);
            telemetry.addData("RX", gamepad1.right_stick_x);
            telemetry.update();
            idle();
        }
    }
}
