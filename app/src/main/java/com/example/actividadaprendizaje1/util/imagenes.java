package com.example.actividadaprendizaje1.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class imagenes {

    //Metodo para convertir las fotos
    public static byte[] fromImageViewToByteArray(ImageView imageView){
        imageView.buildDrawingCache();
        Bitmap bitmap=imageView.getDrawingCache();
        return fromBitmapToArray(bitmap);
    }

    public static byte[] fromBitmapToArray(Bitmap bitmap){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , bos);
        return bos.toByteArray();
    }
}
