package com.rysecamp.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;
import androidx.core.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.rysecamp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PermissionChecker {

    private final int MY_PERMISSIONS_REQUEST = 1;
    private PermissionCallback callback;
    private Activity context;
    private Context mContext;
    private String[] permissions;

    private Boolean allPermissionsNotGranted = true;

    public PermissionChecker() {

    }

    public PermissionChecker(Activity context, String[] permissions, PermissionCallback callback) {
        this.context = context;
        attachListener(callback);
        this.permissions = permissions;
    }

    public static Double availableMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(mi);
        double freeM = (double) mi.availMem / (double) (1024 * 1024);
        Log.i("Free Memory", String.valueOf(freeM));
        return freeM;
    }

    public static File getTemporalFile(Context context, String payload) {
        return new File(context.getExternalCacheDir(), "FILE" + payload);
    }

    public PermissionChecker attachListener(PermissionCallback callback) {
        this.callback = callback;
        return this;
    }

    public PermissionChecker initPermissionChecker(Activity context, String[] permissions) {
        initPermissionChecker(context, permissions, null);
        return this;
    }

    public PermissionChecker initPermissionChecker(Activity context, String[] permissions, PermissionCallback callback) {
        this.context = context;
        attachListener(callback);
        this.permissions = permissions;
        return this;
    }

    public PermissionChecker initPermissionChecker(Context context, String[] permissions, PermissionCallback callback) {
        this.mContext = context;
        attachListener(callback);
        this.permissions = permissions;
        return this;
    }

    public PermissionChecker checkPermissions() {
        // Here, thisActivity is the current activity
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context,
                    permission)
                    != PackageManager.PERMISSION_GRANTED) {
                allPermissionsNotGranted = false;
                break;
            }
        }

        if (!allPermissionsNotGranted) {
            // Should we show an explanation?
            /*if (ActivityCompat.shouldShowRequestPermissionRationale(context, permissions[0])) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(context, permissions, MY_PERMISSIONS_REQUEST);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(context, permissions, MY_PERMISSIONS_REQUEST);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }*/

            ActivityCompat.requestPermissions(context, permissions, MY_PERMISSIONS_REQUEST);

        } else {
            if (callback != null)
                callback.allPermissionsGranted();
        }

        return this;
    }

    public PermissionChecker checkPermissions(int requestCode) {
        // Here, thisActivity is the current activity
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context,
                    permission)
                    != PackageManager.PERMISSION_GRANTED) {
                allPermissionsNotGranted = false;
                break;
            }
        }

        if (!allPermissionsNotGranted) {
            // Should we show an explanation?
            /*if (ActivityCompat.shouldShowRequestPermissionRationale(context, permissions[0])) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(context, permissions, MY_PERMISSIONS_REQUEST);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(context, permissions, MY_PERMISSIONS_REQUEST);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }*/

            ActivityCompat.requestPermissions(context, permissions, requestCode);

        } else {
            if (callback != null)
                callback.allPermissionsGranted();
        }

        return this;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public Boolean getAllPermissionsGranted() {
        return allPermissionsNotGranted;
    }

    public Boolean checkPermissionsGranted() {
        Boolean allPermissionsNotGranted = true;
        // Here, thisActivity is the current activity
        if (mContext != null)
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(mContext,
                        permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsNotGranted = false;
                    break;
                }
            }
        return allPermissionsNotGranted;
    }

    public Boolean checkPermissionsGranted(Context mContext) {
        Boolean allPermissionsNotGranted = true;
        // Here, thisActivity is the current activity
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(mContext,
                    permission)
                    != PackageManager.PERMISSION_GRANTED) {
                allPermissionsNotGranted = false;
                break;
            }
        }
        return allPermissionsNotGranted;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (callback != null)
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST:
                    if (permissions.length == grantResults.length) {
                        // If request is cancelled, the result arrays are empty.
                        if (grantResults.length > 0) {
                            for (Integer grant : grantResults) {
                                if (grant != PackageManager.PERMISSION_GRANTED) {
                                    permissionNotGranted();
                                    return;
                                }
                            }
                            // permission was granted, yay! Do the
                            // contacts-related task you need to do.
                            callback.allPermissionsGranted();
                        } else {
                            // permission denied, boo! Disable the
                            // functionality that depends on this permission.
                            permissionNotGranted();
                        }
                    } else {
                        permissionNotGranted();
                    }
                    // other 'case' lines to check for other
                    // permissions this app might request
                    break;
            }
    }

    private void permissionNotGranted() {
//        Toast.makeText(context, "All Permissions Required", Toast.LENGTH_LONG).show();
        if (callback != null) {
            callback.permissionsNotGranted();
        }
    }

    public void dispatchPickImageFromGallery(int CODE) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivityForResult(i, CODE);
    }

    public void dispatchPickImageFromGallery(int CODE, FragmentActivity context) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivityForResult(i, CODE);
    }

    public String dispatchTakePictureIntent(int CODE) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(context, "Some error in taking photo", Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                context.startActivityForResult(takePictureIntent, CODE);
                return photoFile.getAbsolutePath();
            }
        }
        return null;
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public String dispatchTakePictureIntent(int CODE, FragmentActivity context) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(context, "Some error in taking photo", Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                context.startActivityForResult(takePictureIntent, CODE);
                return photoFile.getAbsolutePath();
            }
        }
        return null;
    }

    public String dispatchTakePictureIntent(int CODE, String fileName) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(fileName);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(context, "Some error in taking photo", Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile));
                    context.startActivityForResult(takePictureIntent, CODE);
                } else {
                    String authorities = context.getApplicationContext().getPackageName() + ".fileprovider";
                    try {
                        Uri photoURI = FileProvider.getUriForFile(context, authorities, photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    } catch (Exception e) {
                        Log.e("Nouget", e.getMessage());
                        Toast.makeText(context, "Android 7", Toast.LENGTH_LONG).show();
                    }
                }
                return photoFile.getAbsolutePath();
            }
        }
        return null;
    }

    public String dispatchTakePictureIntent(int CODE, FragmentActivity context, String fileName) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(fileName);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(context, "Some error in taking photo", Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile));
                } else {
                    String authorities = context.getApplicationContext().getPackageName() + ".fileprovider";
                    try {
                        Uri photoURI = FileProvider.getUriForFile(context, authorities, photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    } catch (Exception e) {
                        Log.e("Nouget", e.getMessage());
                        Toast.makeText(context, "Android 7", Toast.LENGTH_LONG).show();
                    }
                }
                context.startActivityForResult(takePictureIntent, CODE);
                return photoFile.getAbsolutePath();

            }
        }
        return null;
    }

    public void loadFileChooser(int CODE) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            context.startActivityForResult(
                    Intent.createChooser(intent, "Pick Image"),
                    CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(context, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageDir = new File(storageDir.getAbsolutePath(), context.getString(R.string.app_name));
        if (imageDir.exists() ? imageDir.exists() : imageDir.mkdirs()) {
            // Save a file: path for use with ACTION_VIEW intents

            return File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    imageDir      /* directory */

            );

        }
        return null;
    }

    public File createImageFile(String fileName) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = fileName + "_" + timeStamp;
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageDir = new File(storageDir.getAbsolutePath(), context.getString(R.string.app_name));
        if (imageDir.exists() ? imageDir.exists() : imageDir.mkdirs()) {
            // Save a file: path for use with ACTION_VIEW intents

            return File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    imageDir      /* directory */

            );
        }
        return null;
    }


    public String saveBitmap(Bitmap image) throws Exception {
        File file = createImageFile();
        if (file != null) {
            OutputStream outStream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            return file.getAbsolutePath();
        }
        return null;
    }

    public Boolean checkLocationProviderAvailable() {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && !manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "Enable location services for accurate data", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public String getPathUri(Context context, Uri uri) throws URISyntaxException {
        Log.d("File",
                "Authority: " + uri.getAuthority() +
                        ", Fragment: " + uri.getFragment() +
                        ", Port: " + uri.getPort() +
                        ", Query: " + uri.getQuery() +
                        ", Scheme: " + uri.getScheme() +
                        ", Host: " + uri.getHost() +
                        ", Segments: " + uri.getPathSegments().toString()
        );
        String selection = null;
        String[] selectionArgs = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } else if (isDownloadsDocument(uri)) {
                    final String id = DocumentsContract.getDocumentId(uri);
                    uri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                } else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];
                    if ("image".equals(type)) {
                        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    selection = "_id=?";
                    selectionArgs = new String[]{
                            split[1]
                    };
                }
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String filePath = null;
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = 0;
                if (cursor != null) {
                    column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    if (cursor.moveToFirst()) {
                        filePath = cursor.getString(column_index);
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            if (filePath == null) {
                try {
                    InputStream is = context.getContentResolver().openInputStream(Uri.parse(uri.toString()));
                    saveBitmap(BitmapFactory.decodeStream(is));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return filePath;
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public void getPathUriWithCallBack(Context context, Uri uri, FilePathsCallBacks checkerCallBacks) {
        try {
            Log.d("File",
                    "Authority: " + uri.getAuthority() +
                            ", Fragment: " + uri.getFragment() +
                            ", Port: " + uri.getPort() +
                            ", Query: " + uri.getQuery() +
                            ", Scheme: " + uri.getScheme() +
                            ", Host: " + uri.getHost() +
                            ", Segments: " + uri.getPathSegments().toString()
            );
            String selection = null;
            String[] selectionArgs = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (DocumentsContract.isDocumentUri(context, uri)) {
                    if (isExternalStorageDocument(uri)) {
                        final String docId = DocumentsContract.getDocumentId(uri);
                        final String[] split = docId.split(":");
                        checkerCallBacks.filePath(Environment.getExternalStorageDirectory() + "/" + split[1]);
                        return;
                    } else if (isDownloadsDocument(uri)) {
                        final String id = DocumentsContract.getDocumentId(uri);
                        uri = ContentUris.withAppendedId(
                                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                    } else if (isMediaDocument(uri)) {
                        final String docId = DocumentsContract.getDocumentId(uri);
                        final String[] split = docId.split(":");
                        final String type = split[0];
                        if ("image".equals(type)) {
                            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        } else if ("video".equals(type)) {
                            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        } else if ("audio".equals(type)) {
                            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        }
                        selection = "_id=?";
                        selectionArgs = new String[]{
                                split[1]
                        };
                    }
                }
            }
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                String filePath = null;
                String[] projection = {
                        MediaStore.Images.Media.DATA
                };
                Cursor cursor = null;
                try {
                    cursor = context.getContentResolver()
                            .query(uri, projection, selection, selectionArgs, null);
                    int column_index = 0;
                    if (cursor != null) {
                        column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        if (cursor.moveToFirst()) {
                            filePath = cursor.getString(column_index);
                        }
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                if (filePath == null) {
                    saveBitmap(uri, checkerCallBacks);
                } else {
                    checkerCallBacks.filePath(filePath);
                }
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                checkerCallBacks.filePath(uri.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            checkerCallBacks.filePath(null);
        }
    }

    public void saveBitmap(final Uri image, final FilePathsCallBacks checkerCallBacks) {
        new SaveImage(checkerCallBacks).execute(image);
    }

    public interface PermissionCallback {
        int REQUEST_IMAGE_CAPTURE = 1;
        int RESULT_LOAD_IMAGE = 101;
        int REQUEST_FILECHOOSER = 2;

        void allPermissionsGranted();

        void permissionsNotGranted();
    }

    public interface FilePathsCallBacks {
        void filePath(String path);
    }

    private class SaveImage extends AsyncTask<Uri, Void, String> {

        private FilePathsCallBacks filePathsCallBacks;

        public SaveImage(FilePathsCallBacks filePathsCallBacks) {
            this.filePathsCallBacks = filePathsCallBacks;
        }

        @Override
        protected String doInBackground(Uri... params) {
            try {
                File file = createImageFile();
                if (file != null) {
                    Bitmap bitmap = Picasso.with(context).load(params[0]).get();
                    OutputStream outStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                    return file.getAbsolutePath();
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (filePathsCallBacks != null)
                filePathsCallBacks.filePath(s);
        }
    }

}