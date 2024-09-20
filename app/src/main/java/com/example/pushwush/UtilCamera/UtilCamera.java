package com.example.pushwush.UtilCamera;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

public class UtilCamera {

    // Method to get the file path from URI
    public static String getPathFromUri(Context context, Uri uri) {
        String filePath = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        return filePath;
    }

    // Method to upload image
    public static void uploadImage(String imagePath) {
        // Add Retrofit or any HTTP client logic here to upload the file
        // Example: Retrofit or OkHttp calls to upload image
                File file = new File(imagePath);

                Log.i("imageFilepath",file.toString());
//        // Create RequestBody instance from the image file
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
//
//        // MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
    }



}
