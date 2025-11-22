package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "IMU Initialization Example")
public class ImuDisp extends LinearOpMode {

    private IMU imu;

    @Override
    public void runOpMode() {

        // Retrieve the IMU from the hardware map
        imu = hardwareMap.get(IMU.class, "imu");

        // Create and configure IMU parameters
        IMU.Parameters parameters = new IMU.Parameters(
                new IMU.AngleUnit(AngleUnit.DEGREES),
                IMU.AccelUnit.METERS_PERSEC_PERSEC,
                IMU.TempUnit.CELSIUS
        );

        imu.initialize(parameters);

        telemetry.addLine("IMU Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            telemetry.addData("Yaw", orientation.getYaw(AngleUnit.DEGREES));
            telemetry.addData("Pitch", orientation.getPitch(AngleUnit.DEGREES));
            telemetry.addData("Roll", orientation.getRoll(AngleUnit.DEGREES));
            telemetry.update();
        }
    }
}
