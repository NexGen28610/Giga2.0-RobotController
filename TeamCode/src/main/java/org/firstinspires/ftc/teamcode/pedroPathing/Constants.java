package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//https://panels.bylazar.com/docs/com.bylazar.docs/Prerequisites/

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
             .mass(8.6);
    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("rightDriveFront")
            .rightRearMotorName("rightDriveBack")
            .leftRearMotorName("leftDriveBack")
            .leftFrontMotorName("leftDriveFront")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

    // https://pedropathing.com/docs/pathing/tuning/localization/pinpoint
    // https://www.youtube.com/watch?v=vihb2LPtSK0
    // https://pedropathing.com/docs/pathing/tuning

    public static PinpointConstants localizerConstants = new PinpointConstants()
            //https://www.google.com/search?q=18+inches+in+cm&oq=18+inches&gs_lcrp=EgZjaHJvbWUqCggAEAAYsQMYgAQyCggAEAAYsQMYgAQyCQgBEEUYORiABDIGCAIQBhhAMgcIAxAAGIAEMgcIBBAAGIAEMgcIBRAAGIAEMgcIBhAAGIAEMgcIBxAAGIAEMgcICBAAGIAEMgcICRAAGIAE0gEINjU1OWowajeoAgCwAgA&sourceid=chrome&ie=UTF-8
            //to make converting       easer
            .forwardPodY(-5) // TODO: measure and set from the centre to the midle of the wheels vertically in inches or multiply by 2.54cm
            .strafePodX(0.5) // TODO: measure and set from the centre to the back of the wheels horizontally
            .distanceUnit(DistanceUnit.CM)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
             // TODO: check which way these go
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pinpointLocalizer(localizerConstants )
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)

                .build();
    }
}
