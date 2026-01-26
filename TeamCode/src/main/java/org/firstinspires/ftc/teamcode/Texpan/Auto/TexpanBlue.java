package org.firstinspires.ftc.teamcode.Texpan.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

        @Autonomous(name = "TexpanBlue", group = "Autonomous")
       public class TexpanBlue extends LinearOpMode {

            DcMotor rightDriveFront = null;
            DcMotor rightDriveBack = null;
            DcMotor leftDriveBack = null;
            DcMotor leftDriveFront = null;

            DcMotorEx IntakeMotor;
            DcMotorEx Launcher;

            ElapsedTime runtime = new ElapsedTime();
            double ticksPerCm = 537.7 / (9.6 * Math.PI);

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
                    drive_distance(-156);
                    Launcher.setVelocity(1225);
                    sleep(2000);

                    shoot();

                    shoot();

                    shoot();

                    sleep(1000);
                    Launcher.setVelocity(0);
                    IntakeMotor.setVelocity(0);
                    turn(-43);

                    //intake of artifacts
                    IntakeMotor.setVelocity(1800);
                    drive_distance(70);
                    Collection();

                    drive_distance(30);
                    Collection();

                    drive_distance(30);
                    Collection();

                    drive_distance(-60);
                    turn(-43);
                    drive_distance(60);
                    turn(43);

                    sleep(1000);
                    Launcher.setVelocity(1227);
                    sleep(2000);
                    shoot();
                    shoot();
                    shoot();

                    Launcher.setVelocity(0);
                    IntakeMotor.setVelocity(0);
                    rightDriveBack.setPower(0);
                    rightDriveFront.setPower(0);
                    leftDriveFront.setPower(0);
                    leftDriveBack.setPower(0);
/*
            //this code allows us to drive to the pre setted balls, pick them up, then drive back to the goal to score points
             turn(95);
            drive_distance(60);
            turn(-90);
            drive_distance(80);
            drive_distance(-80);
             turn(90);
            drive_distance(60);

            Launcher.setVelocity(1200);
            sleep(2000);
            shoot();
            shoot();
            shoot();

            Launcher.setVelocity(0);
            IntakeMotor.setPower(0);
            */


                }
            }

            public void shoot() {
                IntakeMotor.setVelocity(2000);
                runtime.reset();
                while (runtime.milliseconds() < 2000) {

                    if (runtime.milliseconds() > 1100) {
                        IntakeMotor.setVelocity(0);
                    }
                    //  if (runtime.milliseconds()>900){
                    //     IntakeMotor.setPower(0);
                    //}

                }
            }

            public void Collection() {
                IntakeMotor.setVelocity(2000);
                runtime.reset();
                while (runtime.milliseconds() < 2000) {

                    if (runtime.milliseconds() > 1000) {
                        IntakeMotor.setVelocity(0);
                    }

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


