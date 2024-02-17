package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import  com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name="TLOP", group="TLOP")
public class TLOP extends LinearOpMode {
    private DcMotorEx rightFront;
    private DcMotorEx rightRear;
    private DcMotorEx leftFront;
    private DcMotorEx leftRear;

    private Servo armL;
    private Servo armR;
    private Servo clawr;
    private Servo clawl;

    private DcMotor SliderOutLeft;
    private DcMotor SliderOutRight;
    private Servo outakeLeft;
    private CRServo jackLeft;
    private CRServo jackRight;
    private Servo outakeRight;
    private Servo outake;
    private DcMotor sliderHangLeft;
    private DcMotor sliderHangRight;



    @Override
    public void runOpMode(){
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        armL = hardwareMap.get(Servo.class, "armL");
        armR = hardwareMap.get(Servo.class, "armR");
        clawr = hardwareMap.get(Servo.class, "clawr");
        clawl = hardwareMap.get(Servo.class, "clawl");
//        claw = hardwareMap.get(Servo.class, "claw");

        //OUTAKE
        SliderOutLeft = hardwareMap.get(DcMotor.class, "sol");
        SliderOutRight = hardwareMap.get(DcMotor.class, "sor");
        outakeLeft = hardwareMap.get(Servo.class, "outl");
        outakeRight = hardwareMap.get(Servo.class, "outr");
        jackLeft = hardwareMap.get(CRServo.class, "jackl");
        jackRight = hardwareMap.get(CRServo.class, "jackr");
        outake = hardwareMap.get(Servo.class, "out");

        //MISC
        sliderHangLeft = hardwareMap.get(DcMotor.class, "shl");
        sliderHangRight = hardwareMap.get(DcMotor.class, "shr");
        waitForStart();
        while(!isStopRequested()){
            move(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            if(gamepad1.right_bumper){
//                //abre claw
//                claw.setPosition(0);
                clawr.setPosition(0);
                clawl.setPosition(0.5);
            }else if(gamepad1.left_bumper){
                //cierra claw
//                claw.setPosition(0.5);
                clawr.setPosition(0.5);
                clawl.setPosition(0);
            }
            if(gamepad1.right_trigger > 0.2){
                //sube arm
                armL.setPosition(1);
                armR.setPosition(0);
//                claw.setPosition(0);
                clawr.setPosition(0);
                clawl.setPosition(0.5);
            }else if(gamepad1.left_trigger > 0.2){
                //baja arm
                armL.setPosition(0.15);
                armR.setPosition(0.85);
//                claw.setPosition(0);
                clawr.setPosition(0);
                clawl.setPosition(0.5);
            }

            /**OUTAKE**/
            if((gamepad2.right_stick_y > 0.2) || (gamepad2.right_stick_y < -0.2)){
                if(gamepad2.right_stick_y > 0.2){
                    SliderOutLeft.setPower(-gamepad2.right_stick_y);
                    SliderOutRight.setPower(gamepad2.right_stick_y);

                }else if(gamepad2.right_stick_y < -0.2){
                    SliderOutLeft.setPower(-gamepad2.right_stick_y);
                    SliderOutRight.setPower(gamepad2.right_stick_y);
                }
            }else{
                SliderOutLeft.setPower(0);
                SliderOutRight.setPower(0);
            }
            if(gamepad2.right_trigger > 0.2){
                outakeLeft.setPosition(0.4);
            }else if(gamepad2.right_bumper){
                outakeLeft.setPosition(1);
            }else{
                outakeLeft.setPosition(0.71);
            }
            if(gamepad2.left_trigger > 0.2){
                outake.setPosition(0.75);
            }else{
                outake.setPosition(0.25);
            }

            if(gamepad2.dpad_up){
                //jack goes up
                jackLeft.setPower(1);
                jackRight.setPower(1);
            }else if(gamepad2.dpad_down){
                //jack goes down
                jackLeft.setPower(-1);
                jackRight.setPower(-1);
            }else{
                jackLeft.setPower(0);
                jackRight.setPower(0);
            }


            /**MISC**/
            if((gamepad2.left_stick_y > 0.2) || (gamepad2.left_stick_y < -0.2)){
                if(gamepad2.left_stick_y > 0.2){
                    sliderHangLeft.setPower(gamepad2.left_stick_y);
                    sliderHangRight.setPower(gamepad2.left_stick_y);

                }else if(gamepad2.left_stick_y < -0.2){
                    sliderHangLeft.setPower(gamepad2.left_stick_y);
                    sliderHangRight.setPower(gamepad2.left_stick_y);
                }
            }else{
                sliderHangLeft.setPower(0);
                sliderHangRight.setPower(0);
            }
            telemetry.addData("ServoR", outakeRight.getPosition());
            telemetry.addData("ServoL", outakeLeft.getPosition());
            telemetry.update();
        }
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
        SliderOutLeft.setPower(-power);
        SliderOutRight.setPower(power);
   }
   public void insertSlidersOut(double power){
        SliderOutLeft.setPower(power);
        SliderOutRight.setPower(-power);
   }
   public void hangSlidersOut(double power){
        sliderHangLeft.setPower(power);
        sliderHangRight.setPower(power);
   }
   public void hangSlidersIn(double power){
        sliderHangLeft.setPower(-power);
        sliderHangRight.setPower(-power);
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
    public void move(double forwardPower, double leftPower, double turnPower){
        //ly = forward, lx = leftRun, rx = turnRight
        double leftY;
        double leftX;
        double rightX;

        //MAKE THE VARIABLES ZERO IN CASE OF ERROR
        if(forwardPower < 0.2 && forwardPower > -0.2){
            leftY = 0;
        }else{
            leftY = forwardPower;
        }
        if(leftPower < 0.2 && leftPower > -0.2){
            leftX = 0;
        }else{
            leftX = leftPower;
        }
        if(turnPower < 0.2 && turnPower > -0.2){
            rightX = 0;
        }else{
            rightX = turnPower;
        }

        if(leftX != 0 && leftY != 0 && rightX != 0){
            rightFront.setPower((leftY - leftX - rightX)/3);
            rightRear.setPower((leftY + leftX - rightX)/3);
            leftFront.setPower((-leftY + leftX - rightX)/3);
            leftRear.setPower((-leftY - leftX - rightX)/3);
        }else if((leftX != 0  && leftY != 0 && rightX == 0)
                || (leftX != 0  && leftY == 0 && rightX != 0)
                || (leftX == 0  && leftY != 0 && rightX != 0)){
            rightFront.setPower((leftY - leftX - rightX)/2);
            rightRear.setPower((leftY + leftX - rightX)/2);
            leftFront.setPower((-leftY + leftX - rightX)/2);
            leftRear.setPower((-leftY - leftX - rightX)/2);
        }else if((leftX != 0  && leftY == 0 && rightX == 0)
                || (leftX == 0  && leftY != 0 && rightX == 0)
                || (leftX == 0  && leftY == 0 && rightX != 0)){
            rightFront.setPower(leftY - leftX - rightX);
            rightRear.setPower(leftY + leftX - rightX);
            leftFront.setPower(-leftY + leftX - rightX);
            leftRear.setPower(-leftY - leftX - rightX);
        }else{
            rightFront.setPower(0);
            rightRear.setPower(0);
            leftFront.setPower(0);
            leftRear.setPower(0);
        }
    }
}
