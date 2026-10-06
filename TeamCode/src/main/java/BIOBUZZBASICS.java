import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

public class BIOBUZZBASICS {
    
    @TeleOp(name = "Buzzz", group ="Linear OpmMode")
    public static class biobuzzTrain extends LinearOpMode {
        DcMotor rightDriveFront = null;
        DcMotor rightDriveBack = null;
        DcMotor leftDriveBack = null;
        DcMotor leftDriveFront = null;


        @Override
        public void runOpMode(){
        rightDriveFront = hardwareMap.get(DcMotor.class,"rightDriveFront");
        rightDriveBack = hardwareMap.get(DcMotor.class,"rightDriveBack");
        leftDriveBack = hardwareMap.get(DcMotor.class,"leftDriveBack");
        leftDriveFront = hardwareMap.get(DcMotor.class,"leftDriveFront");



        }
    }
}
