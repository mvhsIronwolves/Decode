package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import kalandparsa.Config.Hardware.Motor;

@TeleOp

public class MainTeleOp extends LinearOpMode {
    //private Gyroscope imu;
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private final Gamepad previousGamepad1;
    private final Gamepad currentGamepad1;

    //private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
//    private Servo servoTest;

    public MainTeleOp() {
        this.previousGamepad1 = new Gamepad();
        this.currentGamepad1 = new Gamepad();
    }


    @Override
    public void runOpMode() throws InterruptedException {
        //imu = hardwareMap.get(Gyroscope.class, "imu");
        this.fl = (DcMotor) this.hardwareMap.dcMotor.get(Motor.FLMotorName);
        this.bl = (DcMotor) this.hardwareMap.dcMotor.get(Motor.BLMotorName);
        this.fr = (DcMotor) this.hardwareMap.dcMotor.get(Motor.FRMotorName);
        this.br = (DcMotor) this.hardwareMap.dcMotor.get(Motor.BRMotorName);
        this.fl.setDirection(Motor.FLMotorDirection);
        this.fr.setDirection(Motor.FRMotorDirection);
        this.bl.setDirection(Motor.BLMotorDirection);
        this.br.setDirection(Motor.BRMotorDirection);
//        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
//        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
//        servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            try {
                this.currentGamepad1.copy(this.gamepad1);
            } catch (Exception var9) {
                Exception e = var9;
                e.printStackTrace();
            }

            telemetry.addData("Status", "Running");
            telemetry.update();

            double y = (double) (-this.gamepad1.left_stick_y);
            double x = (double) this.gamepad1.left_stick_x * 1.1;
            double rx = (double) this.gamepad1.right_stick_x;
            this.drive(x, y, rx, 0.5);
        }
    }

    public void drive(double x, double y, double rx, double _power) throws InterruptedException {
        double power = _power;

        double precise = 1;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0);
        double frontLeftPower = Math.pow(y + x + rx, 1.0) / denominator;
        double backLeftPower = Math.pow(y - x + rx, 1.0) / denominator;
        double frontRightPower = Math.pow(y - x - rx, 1.0) / denominator;
        double backRightPower = Math.pow(y + x - rx, 1.0) / denominator;


        if (frontLeftPower == 0.0 && frontRightPower == 0.0 && backLeftPower == 0.0 && backRightPower == 0.0) {
            this.fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        this.fl.setPower((frontLeftPower * power)/precise);
        this.bl.setPower((backLeftPower * power)/precise);
        this.fr.setPower((frontRightPower * power)/precise);
        this.br.setPower((backRightPower * power)/precise);
    }
}
