package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name ="AutoFarB",group = "Autonomous")
public class AutoFarB extends LinearOpMode {
    DcMotor rightDriveFront = null;
    DcMotor rightDriveBack = null;
    DcMotor leftDriveBack = null;
    DcMotor leftDriveFront = null;

    DcMotorEx IntakeMotor;
    DcMotorEx Launcher;

    ElapsedTime runtime = new ElapsedTime();
    double ticksPerCm = 537.7/(9.6*Math.PI);


    @Override
    public void runOpMode() throws InterruptedException {
        rightDriveBack = hardwareMap.get(DcMotor.class, "rightDriveBack");
        rightDriveFront = hardwareMap.get(DcMotor.class, "rightDriveFront");
        leftDriveBack = hardwareMap.get(DcMotor.class, "leftDriveBack");
        leftDriveFront = hardwareMap.get(DcMotor.class, "leftDriveFront");

        leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Start", "Position");

        //shoot
        IntakeMotor = hardwareMap.get(DcMotorEx.class, "IntakeMotor");
        Launcher = hardwareMap.get(DcMotorEx.class, "Launcher");
        IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        IntakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("current state", "intialize");

        waitForStart();


         Launcher.setVelocity(1710);
        //Launcher.setVelocity(1680); // change it to (1700)
        sleep(5000);

        shoot();

        //Launcher.setVelocity(1719);
        sleep(900);
        shoot();

        shoot();
        Launcher.setVelocity(-100);
        //  sleep(1000);
        Launcher.setVelocity(0);
        IntakeMotor.setVelocity(0);
        turn(-45);
        drive_distance(90);
        //4.9 feed 1.5 T1
        //T2
    }

    public void shoot() {
        IntakeMotor.setVelocity(2105);
        runtime.reset();
        while (runtime.milliseconds() < 2000) {

            if (runtime.milliseconds() > 1900) {
                IntakeMotor.setVelocity(0);
            }
            telemetry.addData("Launcher", getRuntime());
            telemetry.update();
        }
    }
    public void drive_distance(double distance) {

        int targetPosition = (int) (distance * ticksPerCm);
        double targetposition = distance * ticksPerCm;

        leftDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDriveBack.setTargetPosition(targetPosition);
        leftDriveFront.setTargetPosition(targetPosition);
        rightDriveFront.setTargetPosition(targetPosition);
        rightDriveBack.setTargetPosition(targetPosition);

        leftDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (leftDriveBack.isBusy()) {
            rightDriveBack.setPower(1);
            rightDriveFront.setPower(1);
            leftDriveFront.setPower(1);
            leftDriveBack.setPower(1);
        }
        rightDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);


    }

    public void turn(double distance) {
        int targetPosition = (int) (distance * ticksPerCm);
        double targetposition = distance * ticksPerCm;

        leftDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDriveBack.setTargetPosition(targetPosition);
        leftDriveFront.setTargetPosition(targetPosition);
        rightDriveFront.setTargetPosition(-targetPosition);
        rightDriveBack.setTargetPosition(-targetPosition);

        leftDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (leftDriveBack.isBusy()) {
            rightDriveBack.setPower(1);
            rightDriveFront.setPower(1);
            leftDriveFront.setPower(1);
            leftDriveBack.setPower(1);
        }
        rightDriveBack.setPower(0);
        rightDriveFront.setPower(0);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
    }
}