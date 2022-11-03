package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.MyUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileCreation extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_GALLERY = 200;
    private static final String UPLOAD_FILE_URL = AppConstants.BASE_URL+"upload.php";
    private long downloadId;

    TextView file_name;
    String file_path=null;
    Button upload;
    ProgressBar progressBar;
    String email;
    String type;
    String receivedUsername;
    MyUtils myUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        myUtils=new MyUtils(getApplicationContext());
        receivedUsername=myUtils.getUsername();
        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        Button upload_file=findViewById(R.id.upload_file);
        upload_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check permission greater than equal to marshmeellow we used run time permission
                if(Build.VERSION.SDK_INT>=23){
                    if(checkPermission()){
                        filePicker();
                    }
                    else{
                        requestPermission();
                    }
                }
                else{
                    filePicker();
                }
            }
        });

        progressBar=findViewById(R.id.progressBar);
        upload=findViewById(R.id.upload);
        file_name=findViewById(R.id.filename);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(Upload.this,"Type is "+type,Toast.LENGTH_LONG).show();
                if(file_path!=null){
                    UploadFile();
                }
                else{
                    Toast.makeText(ProfileCreation.this, "Please Select File and Type First ", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        Button advacneUpload=findViewById(R.id.advanceupload);
//        advacneUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Upload.this,AdvanceFileUpload.class));
//            }
//        });



    }

    private void UploadFile() {
        progressBar.setVisibility(View.VISIBLE);
        UploadTask uploadTask=new UploadTask();
        uploadTask.execute(new String[]{file_path});
    }

    private void beginDownload(String file_link){
        File file=new File(getExternalFilesDir(null),"Dummy");

        //checking if android version is equal and greater than noughat

        //now if download complete file not visible now lets show it
        DownloadManager.Request request=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            request=new DownloadManager.Request(Uri.parse(file_link))
                    .setTitle("Dummy")
                    .setDescription("Downloading")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationUri(Uri.fromFile(file))
                    .setRequiresCharging(false)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true);
        }
        else{
            request=new DownloadManager.Request(Uri.parse(file_link))
                    .setTitle("Dummy")
                    .setDescription("Downloading")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationUri(Uri.fromFile(file))
                    .setAllowedOverRoaming(true);
        }

        DownloadManager downloadManager=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        downloadId=downloadManager.enqueue(request);


    }

    private void filePicker(){
        //.Now Permission Working
        Toast.makeText(ProfileCreation.this, "File Picker Call", Toast.LENGTH_SHORT).show();
        //Let's Pick File
        Intent opengallery=new Intent(Intent.ACTION_PICK);
        opengallery.setType("image/*");
        startActivityForResult(opengallery,REQUEST_GALLERY);
    }

    //now checking if download complete

    private BroadcastReceiver onDownloadComplete=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id=intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
            if(downloadId==id){
                Toast.makeText(ProfileCreation.this, "Download Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(ProfileCreation.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(ProfileCreation.this, "Please Give Permission to Upload File", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(ProfileCreation.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission(){
        int result= ContextCompat.checkSelfPermission(ProfileCreation.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(ProfileCreation.this, "Permission Successfull", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ProfileCreation.this, "Permission Failed", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_GALLERY && resultCode== Activity.RESULT_OK){
            String filePath=getRealPathFromUri(data.getData(),ProfileCreation.this);
            Log.d("File Path : "," "+filePath);
            //now we will upload the file
            //lets import okhttp first
            this.file_path=filePath;

            File file=new File(filePath);
            file_name.setText(file.getName());

        }
    }

    public String getRealPathFromUri(Uri uri,Activity activity){
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor=activity.getContentResolver().query(uri,proj,null,null,null);
        if(cursor==null){
            return uri.getPath();
        }
        else{
            cursor.moveToFirst();
            int id=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(id);
        }
    }


    public class UploadTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // progressBar.setVisibility(View.GONE);
            if(s.equalsIgnoreCase("true")){
                Toast.makeText(ProfileCreation.this, "File uploaded", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(ProfileCreation.this, "Failed Upload", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            if(uploadFile(strings[0])){
                return "true";
            }
            else{
                return "failed";
            }
        }

        private boolean uploadFile(String path){


            File file=new File(path);
            try{
                RequestBody requestBody=new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("files",file.getName(),RequestBody.create(MediaType.parse("image/*"),file))
                        .addFormDataPart("some_key","some_value")
//                      .addFormDataPart("email",email)
                        .addFormDataPart("receivedUsername",receivedUsername)
                        .addFormDataPart("submit","submit")
                        .build();

                Request request=new Request.Builder()
                        .url(UPLOAD_FILE_URL)
                        .post(requestBody)
                        .build();

                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showAlert("Uploading failed!!!!,Try again!!!", false);

                            }
                        });
                        Log.d("ERROR UKUDOX",e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showAlert("Uploaded successfully", false);

                            }
                        });
                        Log.d("SUCCESS UKUDOX",response.body().string());
                    }
                });
                return true;
            }
            catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
    public void showAlert(String message, final boolean shouldTryAgain) {
        AlertDialog.Builder alert = new AlertDialog.Builder(ProfileCreation.this);
        alert.setTitle("Result");
        alert.setMessage(message);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (shouldTryAgain) {
                    // fetchTickets(source, destination, date);
                }
                dialog.dismiss();
            }
        });

        progressBar.setVisibility(View.GONE);
        alert.show();
    }
}