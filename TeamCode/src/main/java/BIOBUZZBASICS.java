import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class BIOBUZZBASICS {
    
    @TeleOp(name = "Buzzz", group ="Linear OpmMode")
    public static class biobuzzTrain extends LinearOpMode {
        DcMotor rightDriveFront = null;
        DcMotor rightDriveBack = null;
        DcMotor leftDriveBack = null;
        DcMotor leftDriveFront = null;

        boolean isShootFar= false, isShootClose=false;

        DcMotorEx Launcher;
        DcMotorEx IntakeMotor;


        @Override
        public void runOpMode() {

            rightDriveFront = hardwareMap.get(DcMotor.class, "rightDriveFront");
            rightDriveBack = hardwareMap.get(DcMotor.class, "rightDriveBack");
            leftDriveBack = hardwareMap.get(DcMotor.class, "leftDriveBack");
            leftDriveFront = hardwareMap.get(DcMotor.class, "leftDriveFront");

            rightDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDriveBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftDriveBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftDriveFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            telemetry.addData("status", "initialized");
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

                leftDriveBack.setPower(leftDriveBackPower);
                leftDriveFront.setPower(leftDriveFrontPower);
                rightDriveFront.setPower(rightDriveFrontPower);
                rightDriveBack.setPower(rightDiveBackPower);


                // EXPLAIN THIS NEXT TIME WE OPEN UP
                if (gamepad1.dpad_left){
                    IntakeMotor.setVelocity(4000); //2000 is default
                    // stopper.setPosition(0);

                } else if(gamepad1.dpad_right) {
                    IntakeMotor.setVelocity(-1000);
                }
                else {
                    IntakeMotor.setPower(0);
                }

                if (gamepad1.aWasPressed()) {
                    isShootFar = !isShootFar;
                    //Launcher.setVelocity(1700);//1125
                } else if (gamepad1.xWasPressed()) {
                    isShootClose = !isShootClose;
                }

                if (gamepad1.right_trigger > 0.3){
                    Launcher.setVelocity(-900);
                }

                if (isShootFar){
                    Launcher.setVelocity(1900);//1125

                } else if (isShootClose) {
                    Launcher.setVelocity(1290);
                }else {
                    // stopper.setPosition(0);
                    Launcher.setVelocity(0);//1125
                }
                telemetry.addData("Right F", rightDriveFront.getCurrentPosition());
                telemetry.addData("Right B", rightDriveBack.getCurrentPosition());
                telemetry.addData("Left F", leftDriveFront.getCurrentPosition());
                telemetry.addData("Left B", leftDriveBack.getCurrentPosition());

                telemetry.update();
            }
        }
    }
}