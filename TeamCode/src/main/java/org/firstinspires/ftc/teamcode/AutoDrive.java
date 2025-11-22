package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name ="AutoDrive",group = "Autonomous")
public class AutoDrive extends LinearOpMode {
    DcMotor rightDriveFront = null;
    DcMotor rightDriveBack = null;
    DcMotor leftDriveBack = null;
    DcMotor leftDriveFront = null;

    ElapsedTime runtime= new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        rightDriveBack=hardwareMap.get(DcMotor.class, "rightDriveBack");
        rightDriveFront=hardwareMap.get(DcMotor.class,"rightDriveFront");
        leftDriveBack=hardwareMap.get(DcMotor.class,"leftDriveBack");
        leftDriveFront=hardwareMap.get(DcMotor.class,"leftDriveFront");

        leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Start","Position");

        waitForStart();

        while (opModeIsActive()) {
            double max;

            // how gamepad works https://www.youtube.com/watch?v=gXegsVvXLd0

            double forward = -gamepad1.right_stick_y;
            double sideways = gamepad1.right_stick_x;
            double turn = gamepad1.left_stick_x;


            double leftDriveFrontPower = forward + sideways + turn;
            double leftDriveBackPower = forward - sideways + turn;
            double rightDriveFrontPower = forward - sideways - turn;
            double rightDiveBackPower = forward + sideways - turn;

            max = Math.max(Math.abs(leftDriveFrontPower), Math.abs(rightDriveFrontPower));
            max = Math.max(max, Math.abs(leftDriveBackPower));
            max = Math.max(max, Math.abs(rightDiveBackPower));

            if (max > 1) {
                leftDriveFrontPower /= max;
                leftDriveBackPower /= max;
                rightDriveFrontPower /= max;
                rightDiveBackPower /= max;
            }


            leftDriveFront.setPower(leftDriveFrontPower);
            rightDriveFront.setPower(rightDriveFrontPower);
            rightDriveBack.setPower(rightDiveBackPower);
            leftDriveBack.setPower(leftDriveBackPower);
            //END OF DRIVING ONLY
        }

    }
}
