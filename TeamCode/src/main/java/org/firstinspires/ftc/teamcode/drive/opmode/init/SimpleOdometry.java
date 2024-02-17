package org.firstinspires.ftc.teamcode.drive.opmode.init;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.StandardTrackingWheelLocalizer;
import org.firstinspires.ftc.teamcode.util.Encoder;

import java.util.Arrays;
import java.util.List;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.Encoder;
public class SimpleOdometry {
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

    public static void goToDistance(double Distance, double rE, double lE, DcMotor rf, DcMotor rb, DcMotor lf, DcMotor lb, double P){
        double EncoderResolution = 2000;
        double circumference = 0;
        double reDistance = (EncoderResolution / rE) * circumference;
        double leDistance = (EncoderResolution / lE) * circumference;
        double fwd = (reDistance + leDistance) * 0.5;
        while(fwd < Distance - 10 || fwd > Distance + 10){
            rf.setPower(-P);
            rb.setPower(-P);
            lf.setPower(P);
            lb.setPower(P);
        }
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