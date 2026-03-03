package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class GigaDecodeDriveTrain {
    @TeleOp(name = "DecodeBotTeleop", group="Linear OpMode")
    public static class decodeDriveTrain extends LinearOpMode {
        /*drive*/
        DcMotor rightDriveFront = null;
        DcMotor rightDriveBack = null;
        DcMotor leftDriveBack = null;
        DcMotor leftDriveFront = null;


        //ball shooter
        boolean isShootFar =false,isShootClose =false;
          DcMotorEx IntakeMotor;
         DcMotorEx Launcher;

        @Override
        public void runOpMode() {
            //drive
            rightDriveBack = hardwareMap.get(DcMotor.class, "rightDriveBack");
            rightDriveFront = hardwareMap.get(DcMotor.class, "rightDriveFront");
            leftDriveBack = hardwareMap.get(DcMotor.class, "leftDriveBack");
            leftDriveFront = hardwareMap.get(DcMotor.class, "leftDriveFront");

            leftDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftDriveBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDriveBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


            leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
            rightDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
            rightDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);


            //ball shooter
             IntakeMotor=hardwareMap.get(DcMotorEx.class,"IntakeMotor");
            Launcher=hardwareMap.get(DcMotorEx.class,"Launcher");

            Launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
              Launcher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(300, 0, 0, 10));

            IntakeMotor.setMode((DcMotor.RunMode.RUN_WITHOUT_ENCODER));
            IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);


            telemetry.addData("status", "Initialized");
            telemetry.update();
            waitForStart();

            while (opModeIsActive()) {
                double max;

                // how gamepad works https://www.youtube.com/watch?v=gXegsVvXLd0

                double forward = -gamepad1.right_stick_y;
                double sideways = gamepad1.right_stick_x;
                double turn = gamepad1.left_stick_x / 0.8;


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

                if (gamepad1.dpad_left){
                    IntakeMotor.setVelocity(2000);

                } else if(gamepad1.dpad_right) {
                    IntakeMotor.setVelocity(-2000);
                }IntakeMotor.setPower(0);

                if (gamepad1.aWasPressed()) {
                    isShootFar = !isShootFar;
                    //Launcher.setVelocity(1700);//1125
                } else if (gamepad1.xWasPressed()) {
                    isShootClose = !isShootClose;
                }

                                                                                             //else if (gamepad1.b) {
                                                                                                      //Launcher.setVelocity(0);
                                                                                                                 // }
                                                                                         /* if (gamepad1.x){
                                                                                             // Launcher.setVelocity(1290);
                                                                                               isShootClose = !isShootClose;
               }*/                                                                            //else if (gamepad1.y) {
                                                                                            // Launcher.setVelocity(0);
                                                                                                     // }
                if (gamepad1.right_trigger > 0.3){
                    Launcher.setVelocity(-900);
                }
                //Launcher.setVelocity(0);

                if (isShootFar){
                    Launcher.setVelocity(1700);//1125
                } else if (isShootClose) {
                    Launcher.setVelocity(1290);
                }else {
                        Launcher.setVelocity(0);//1125
                }



                //while(gamepad1.dpad_up){
                 //   stopper.setPosition(0);
                //}
                //while(gamepad1.dpad_down){
                 //   stopper.setPosition(1);
                //}

                /*if (gamepad1.left_trigger > 0.5){
                    Launcher.setVelocity(2000);
                }Launcher.setVelocity(0);
*/
                telemetry.addData("Right F", rightDriveFront.getCurrentPosition());
                telemetry.addData("Right B", rightDriveBack.getCurrentPosition());
                telemetry.addData("Left F", leftDriveFront.getCurrentPosition());
                telemetry.addData("Left B", leftDriveBack.getCurrentPosition());

                telemetry.update();



            }
        }
    }
}

/*
        THIS IS RHE TELE OP CODE WITH NO MODEFICATIONS OR CHANGES MADE TO IT
        DcMotor rightDriveFront = null;
        DcMotor rightDriveBack = null;
        DcMotor leftDriveBack = null;
        DcMotor leftDriveFront = null;

        //ball shooter
          DcMotorEx IntakeMotor;
         DcMotorEx Launcher;

        @Override
        public void runOpMode() {
            //drive
            rightDriveBack = hardwareMap.get(DcMotor.class, "rightDriveBack");
            rightDriveFront = hardwareMap.get(DcMotor.class, "rightDriveFront");
            leftDriveBack = hardwareMap.get(DcMotor.class, "leftDriveBack");
            leftDriveFront = hardwareMap.get(DcMotor.class, "leftDriveFront");

            leftDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftDriveBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDriveBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            leftDriveFront.setDirection(DcMotorSimple.Direction.REVERSE);
            rightDriveFront.setDirection(DcMotorSimple.Direction.FORWARD);
            rightDriveBack.setDirection(DcMotorSimple.Direction.FORWARD);

            //ball shooter
             IntakeMotor=hardwareMap.get(DcMotorEx.class,"IntakeMotor");
            Launcher=hardwareMap.get(DcMotorEx.class,"Launcher");

            Launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
              Launcher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(300, 0, 0, 10));

            IntakeMotor.setMode((DcMotor.RunMode.RUN_WITHOUT_ENCODER));
            IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

            telemetry.addData("status", "Initialized");
            telemetry.update();
            waitForStart();

            while (opModeIsActive()) {
                double max;

                // how gamepad works https://www.youtube.com/watch?v=gXegsVvXLd0

                double forward = -gamepad1.right_stick_y;
                double sideways = gamepad1.right_stick_x;
                double turn = gamepad1.left_stick_x / 0.8;

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

                if (gamepad1.dpad_left){
                    IntakeMotor.setVelocity(2000);

                } else if(gamepad1.dpad_right) {
                    IntakeMotor.setVelocity(-2000);
                }IntakeMotor.setPower(0);
                if (gamepad1.a) {
                    Launcher.setVelocity(1800);//1125
                } else if (gamepad1.b) {
                    Launcher.setVelocity(0);
                }
                if (gamepad1.x){
                    Launcher.setVelocity(1290);
                } else if (gamepad1.y) {
                    Launcher.setVelocity(0);
                }
                if (gamepad1.right_trigger > 0.3){
                    Launcher.setVelocity(-900);
                }
                Launcher.setVelocity(0);
*/

