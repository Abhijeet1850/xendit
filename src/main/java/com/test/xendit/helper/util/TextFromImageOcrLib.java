package com.test.xendit.helper.util;

import com.test.xendit.helper.resource.ResourceHelper;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

public class TextFromImageOcrLib {

    public static final String tessDataPath = "//src//main//resources//tessdata";
    TessBaseAPI tessBaseAPI;
    PIX image;


    public String getTextFromImage(String imgLocation) {
        tessBaseAPI = new TessBaseAPI();
        tessBaseAPI.Init(ResourceHelper.getResourcePath(tessDataPath), "eng");
        image = lept.pixRead(imgLocation);
        tessBaseAPI.SetImage(image);
        BytePointer bytePointer = tessBaseAPI.GetUTF8Text();
        return bytePointer.getString();

    }
}
