package com.alltrustnetworks.smartcheck.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;

import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.TiffImageParser;
import org.beyka.tiffbitmapfactory.CompressionScheme;
import org.beyka.tiffbitmapfactory.IProgressListener;
import org.beyka.tiffbitmapfactory.ResolutionUnit;
import org.beyka.tiffbitmapfactory.TiffConverter;
import org.beyka.tiffbitmapfactory.TiffSaver;

/*
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
*/

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Util {

    public static String removeUnderScore(String data){
        if (data != null)
            return data.replaceAll("_"," ");
        else
            return null;
    }

    public static String getDateStr(Date date) {
        String dateStr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);
        return dateStr;
    }

    public static String camelCase(String in, String split) {
        if (in == null || in.length() < 1) { return ""; } //validate in
        String out = "";
        for (String part : in.toLowerCase().split(split)) {
            if (part.length() < 1) { //validate length
                continue;
            }

            if (out == "") {
                out += part.substring(0, 1).toUpperCase();
            }
            else {
                out += " " + part.substring(0, 1).toUpperCase();
            }

            if (part.length() > 1) {
                out += part.substring(1);
            }
        }
        return out;
    }

    public static String convertDate(String dateStr) {
        String m = dateStr.substring(0, 2);
        String d = dateStr.substring(2, 4);
        String y = dateStr.substring(4, 8);

        if (m != null && d != null && y != null) {
            return y + "-" + m + "-" + d;
        }

        return "";
    }

    public static String convertRegulaDate(String dateStr) {
        if (dateStr == null) return "";
        String formattedDate = "";

        try {
            Date date = new SimpleDateFormat("M/d/yy").parse(dateStr);
            formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
        catch(Exception e) {
        }

        return formattedDate;
    }

    public static String toBase64(byte [] image) {
        return Base64.encodeToString(image, Base64.DEFAULT);
    }

    public static String parseRawMicr(String rawMicr) {
        if (rawMicr == null) return rawMicr;

        rawMicr = rawMicr
                .replaceAll("\\.", "T")
                .replaceAll("_", " ")
                .replaceAll(",", "U");

        return rawMicr;
    }

    public static byte[] convertBitmapToByteArray(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bmp.recycle();
        return byteArray;
    }

    public static Bitmap converToMonochrome(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                int gray = (int) (0.2989 * R + 0.5870 * G + 0.1140 * B);
                // use 128 as threshold, above -> white, below -> black
                if (gray > 128) {
                    gray = 0;
                    bmOut.setPixel(x, y, gray);
                }
                else{
                    gray = 255;
                    bmOut.setPixel(x, y, Color.rgb(gray, gray, gray));
                }
                // set new pixel color to output bitmap

            }
        }

        Log.i("SmartCheck", "Color: " + Color.rgb(0, 0, 0) + "");
        return bmOut;
    }

    public static String convertBitmapToTiff(Context context, Bitmap bitmap) {
        try {
            File outputDir = context.getCacheDir();
            File file = File.createTempFile("check", ".tiff", outputDir);
            TiffSaver.SaveOptions options = new TiffSaver.SaveOptions();
            options.compressionScheme = CompressionScheme.CCITTFAX4;

            bitmap = converToMonochrome(bitmap);
            options.xResolution = 200;
            options.yResolution = 200;

            options.resUnit = ResolutionUnit.INCH;

            boolean converted = TiffSaver.saveBitmap(file, bitmap, options);
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            return Base64.encodeToString(bytes, Base64.DEFAULT);

        }
        catch(Exception e) {
            Log.i("SmartCheck", e.getMessage());
            return null;
        }
    }

    public static byte[] convertTiffToByteArray(Context context, File tiffFile) {
        IProgressListener progressListener = new IProgressListener() {
            @Override
            public void reportProgress(long processedPixels, long totalPixels) { }
        };
        File jpgFile = null;
        try {

            TiffConverter.ConverterOptions options = new TiffConverter.ConverterOptions();

            File outputDir = context.getCacheDir();

            jpgFile = File.createTempFile("check", ".jpg", outputDir);
            options.throwExceptions = true;
            options.availableMemory = 128 * 1024 * 1024;
            options.readTiffDirectory = 1;
            TiffConverter.convertTiffJpg(tiffFile.getAbsolutePath(), jpgFile.getAbsolutePath(), options, progressListener);
            FileInputStream fileInputStream = new FileInputStream(jpgFile);
            byte[] bytes = new byte[(int) jpgFile.length()];
            fileInputStream.read(bytes);
            if (jpgFile != null)
                deleteImageFile(jpgFile.getAbsolutePath());
            return bytes;
        }
        catch (Exception e) {
            Log.i("SmartCheck", "Conversion error");
            Log.i("SmartCheck", e.getMessage());
            if (jpgFile != null)
                deleteImageFile(jpgFile.getAbsolutePath());
            return null;
        }

    }

    public static Bitmap convertByteArrayToBitmap(byte[] source) {
        return BitmapFactory.decodeByteArray(source , 0, source .length);
    }

    public static byte[] convertTo70Dpi(byte[] imageData) {
        int dpi = 70;
        imageData[13] = 1;
        imageData[14] = (byte) (dpi >> 8);
        imageData[15] = (byte) (dpi & 0xff);
        imageData[16] = (byte) (dpi >> 8);
        imageData[17] = (byte) (dpi & 0xff);
        return imageData;
    }

    public static String convertTiffToBase64String(File tiffFile){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(tiffFile);
            byte[] bytes = new byte[(int) tiffFile.length()];
            fileInputStream.read(bytes);
            return Util.toBase64( bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static byte[] compressCheckImage(byte[] source){
        try{
            Bitmap bitmap = BitmapFactory.decodeByteArray(source, 0, source.length);
            Log.i("SmartCheck", "capicility before = " + source.length);
            byte[] arr = null;
            if (bitmap != null) {
                int scaleHeight = (1000* bitmap.getHeight())/ bitmap.getWidth();
                bitmap = Bitmap.createScaledBitmap(bitmap, 1000, scaleHeight, true );
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                arr = stream.toByteArray();
                Log.i("SmartCheck", "capicility after = " + arr.length);
                bitmap.recycle();
            }
            else {
                Log.i("SmartCheck", "front image not present");
            }
            return arr;
        }catch (Exception e){
            e.printStackTrace();
            return source;
        }
    }

    public static Boolean deleteImageFile(String path){
         File fdelete = new File(path);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                return true;
            }
            return false;
        }
        return true;
    }
    /*
    public static Bitmap findRectangle(Bitmap image) throws Exception {
        Mat tempor = new Mat();
        Mat src = new Mat();
        Utils.bitmapToMat(image, tempor);

        Imgproc.cvtColor(tempor, src, Imgproc.COLOR_BGR2RGB);

        Mat blurred = src.clone();
        Imgproc.medianBlur(src, blurred, 9);

        Mat gray0 = new Mat(blurred.size(), CvType.CV_8U), gray = new Mat();

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

        List<Mat> blurredChannel = new ArrayList<Mat>();
        blurredChannel.add(blurred);
        List<Mat> gray0Channel = new ArrayList<Mat>();
        gray0Channel.add(gray0);

        MatOfPoint2f approxCurve;

        double maxArea = 0;
        int maxId = -1;

        for (int c = 0; c < 3; c++) {
            int ch[] = { c, 0 };
            Core.mixChannels(blurredChannel, gray0Channel, new MatOfInt(ch));

            int thresholdLevel = 1;
            for (int t = 0; t < thresholdLevel; t++) {
                if (t == 0) {
                    Imgproc.Canny(gray0, gray, 10, 20, 3, true); // true ?
                    Imgproc.dilate(gray, gray, new Mat(), new Point(-1, -1), 1); // 1
                    // ?
                } else {
                    Imgproc.adaptiveThreshold(gray0, gray, thresholdLevel, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, (src.width() + src.height()) / 200, t);
                }

                Imgproc.findContours(gray, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

                for (MatOfPoint contour : contours) {
                    MatOfPoint2f temp = new MatOfPoint2f(contour.toArray());

                    double area = Imgproc.contourArea(contour);
                    approxCurve = new MatOfPoint2f();
                    Imgproc.approxPolyDP(temp, approxCurve, Imgproc.arcLength(temp, true) * 0.02, true);

                    if (approxCurve.total() == 4 && area >= maxArea) {
                        double maxCosine = 0;

                        List<Point> curves = approxCurve.toList();
                        for (int j = 2; j < 5; j++) {

                            double cosine = Math.abs(angle(curves.get(j % 4), curves.get(j - 2), curves.get(j - 1)));
                            maxCosine = Math.max(maxCosine, cosine);
                        }

                        if (maxCosine < 0.3) {
                            maxArea = area;
                            maxId = contours.indexOf(contour);
                        }
                    }
                }
            }
        }

        Bitmap bmp;

        if (maxId >= 0) {
            Rect rect = Imgproc.boundingRect(contours.get(maxId));

            Imgproc.rectangle(src, rect.tl(), rect.br(), new Scalar(255, 0, 0, .8), 4);

            int mDetectedWidth = rect.width;
            int mDetectedHeight = rect.height;

            Mat cropped = new Mat(src, rect);
            bmp = Bitmap.createBitmap(cropped.cols(), cropped.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(cropped, bmp);
            return bmp;
        }
        else {
            bmp = Bitmap.createBitmap(src.cols(), src.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(src, bmp);
            return bmp;
        }
    }

    public static double angle(Point p1, Point p2, Point p0) {
        double dx1 = p1.x - p0.x;
        double dy1 = p1.y - p0.y;
        double dx2 = p2.x - p0.x;
        double dy2 = p2.y - p0.y;
        return (dx1 * dx2 + dy1 * dy2)
                / Math.sqrt((dx1 * dx1 + dy1 * dy1) * (dx2 * dx2 + dy2 * dy2)
                + 1e-10);
    }
    */

}
