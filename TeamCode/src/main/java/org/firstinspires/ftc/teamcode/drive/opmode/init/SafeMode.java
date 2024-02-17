package org.firstinspires.ftc.teamcode.drive.opmode.init;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous
public class SafeMode  extends LinearOpMode {
    private Servo armL, armR;
    private Servo clawl, clawr;

    //OUTAKE
    private DcMotor sliderOutLeft, sliderOutRight;
    private Servo outakeLeft, outakeRight;
    private CRServo jackLeft, jackRight;
    private Servo outake;

    private DcMotor rightRear;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor leftFront;

    //MISC
    private DcMotor sliderHangLeft, sliderHangRight;
    //    private Servo plane;


    //AUTONOMOUS VARIABLES
    String aprilPosition = "";
    double timePassed = 0;

    public void runOpMode(){
        //    AprilTagOpenCvCamera aprilCamera = new AprilTagOpenCvCamera(hardwareMap, telemetry);
        ElapsedTime time = new ElapsedTime();
        //INTAKE
        armL = hardwareMap.get(Servo.class, "armL");
        armR = hardwareMap.get(Servo.class, "armR");
        clawr = hardwareMap.get(Servo.class, "clawr");
        clawl = hardwareMap.get(Servo.class, "clawl");

        //OUTAKE
        sliderOutLeft = hardwareMap.get(DcMotor.class, "sol");
        sliderOutRight = hardwareMap.get(DcMotor.class, "sor");
        outakeLeft = hardwareMap.get(Servo.class, "outl");
        outakeRight = hardwareMap.get(Servo.class, "outr");
        jackLeft = hardwareMap.get(CRServo.class, "jackl");
        jackRight = hardwareMap.get(CRServo.class, "jackr");
        outake = hardwareMap.get(Servo.class, "out");

        //MISC
        sliderHangLeft = hardwareMap.get(DcMotor.class, "shl");
        sliderHangRight = hardwareMap.get(DcMotor.class, "shr");

        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
//        plane = hardwareMap.get(Servo.class, "plane");
        //

        telemetry.update();

        closeClaw();
        boxUp();
        outClose();
        // cameraServo.setPosition(0.65);

        time.startTime();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {
            time.reset();

            forward(0.3);
            sleep(2500);
            stopChassis();
            armUp();
            sleep(2000);
            openClaw();
            sleep(1000);
            forward(-0.1);
            sleep(2500);
            stopChassis();
            closeClaw();
            sleep(1000);
            armDown();



            stopChassis();

            sleep(30000);
        }
    }

    public void boxUp(){
        outakeLeft.setPosition(0.70);
        outakeRight.setPosition(0.25);
    }
    public void closeClaw(){
        clawr.setPosition(0);
        clawl.setPosition(0.5);
    }
    public void openClaw(){
        clawr.setPosition(0.5);
        clawl.setPosition(0);
    }

    public void armDown(){
        armL.setPosition(1);
        armR.setPosition(0);
    }
    public void armUp(){
        armL.setPosition(0.17);
        armR.setPosition(0.83);
    }

    public void jackUp(double power){
        jackLeft.setPower(power);
        jackRight.setPower(power);
    }
    public void jackDown(double power){
        jackLeft.setPower(-power);
        jackRight.setPower(-power);
    }
    public void stopJack(){
        jackLeft.setPower(0);
        jackRight.setPower(0);
    }

    public void drawSlidersOut(double power){
        sliderOutLeft.setPower(-power);
        sliderOutRight.setPower(power);
    }
    public void insertSlidersOut(double power){
        sliderOutLeft.setPower(power);
        sliderOutRight.setPower(-power);
    }
    public void stopSlidersOut(){
        sliderOutLeft.setPower(0);
        sliderOutRight.setPower(0);
    }


    public void boxDown(){
        outakeLeft.setPosition(0.5);
        outakeRight.setPosition(0.65);
    }
    public void outOpen(){
        outake.setPosition(0.75);
    }
    public void outClose(){
        outake.setPosition(0.35);
    }
    public void forward(double power){
        rightFront.setPower(-power);
        rightRear.setPower(-power);
        leftFront.setPower(power);
        leftRear.setPower(power);
    }
    public void leftRun(double power){
        rightFront.setPower(-power);
        rightRear.setPower(power);
        leftFront.setPower(power);
        leftRear.setPower(-power);
    }
    public void turnRight(double power){
        rightFront.setPower(-power);
        rightRear.setPower(-power);
        leftFront.setPower(-power);
        leftRear.setPower(-power);
    }
    public void stopChassis(){
        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        leftRear.setPower(0);
    }
}