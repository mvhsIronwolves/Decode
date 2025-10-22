package kalandparsa;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Config {
    public static class Hardware {
        public static class Motor {
            public static String FLMotorName = "fl";
            public static DcMotorEx.Direction FLMotorDirection = DcMotorEx.Direction.FORWARD;
            public static String FRMotorName = "fr";
            public static DcMotorEx.Direction FRMotorDirection = DcMotorEx.Direction.REVERSE;
            public static String BLMotorName = "bl";
            public static DcMotorEx.Direction BLMotorDirection = DcMotorEx.Direction.FORWARD;
            public static String BRMotorName = "br";
            public static DcMotorEx.Direction BRMotorDirection = DcMotorEx.Direction.REVERSE;
            public static double moveVelo = 1.0;

            public static double driveMotorPPR = ((((1 + (46d / 17))) * (1 + (46d / 11))) * 28);
            public static double winchMotorPPR = ((((1 + (46d / 17))) * (1 + (46d / 11))) * 28);
            public static double strafeMultiplier = 100d / 89d;
            public static double rotateMultiplierLeft = -1050.0 / (Math.PI / 2);
            public static double rotateMultiplierRight = 1000.0 / (Math.PI / 2);

        }
    }
}
