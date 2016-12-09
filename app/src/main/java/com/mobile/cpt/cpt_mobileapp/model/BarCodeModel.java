package com.mobile.cpt.cpt_mobileapp.model;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.widget.EditText;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class BarCodeModel {
    private Bitmap image;
    private String code;

    public BarCodeModel(Bitmap image){
        this.image = image;
        Log.i("BarCodeModel", "constructor");
    }

    private Mat toGrayScale(){
        Mat mat = new Mat (image.getHeight(), image.getWidth(), CvType.CV_8UC1);
        Utils.bitmapToMat(image, mat);
        Log.i("BarCodeModel", "Bitmap is mat");
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY);
        return mat;
    }

    public String getCode() {
        Mat mat = toGrayScale();
        Log.i("BarCodeModel", "grey scale made");
        return code;
    }
}
