package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import org.firstinspires.ftc.teamcode.mechanisms.AprilTagCam;
import org.firstinspires.ftc.teamcode.mechanisms.ImuDisp;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@Autonomous
public class AprilTagExample extends OpMode {
    AprilTagCam aprilTagCam = new AprilTagCam();

    @Override
    public void init(){
        aprilTagCam.init(hardwareMap,telemetry);

    }

    @Override
    public void loop() {
        aprilTagCam.update();
        List<AprilTagDetection> ids = aprilTagCam.getDetectedTags();
        for(AprilTagDetection id: ids) {
            /*telemetry.addData("id20 String", id20.toString());*/
            aprilTagCam.displayDetectionTelemetry(id);
        }
    }
}
