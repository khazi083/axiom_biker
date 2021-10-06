package com.axiom.fulfillment.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.api.APIClient;
import com.axiom.fulfillment.api.APIInterface;
import com.axiom.fulfillment.helper.SignatureView;
import com.axiom.fulfillment.helper.UserSharedPreferences;
import com.axiom.fulfillment.helper.constants;
import com.axiom.fulfillment.model.Attachements;
import com.axiom.fulfillment.model.BikerRequest;
import com.axiom.fulfillment.model.CommonApiResponse;
import com.axiom.fulfillment.model.DeliveryRequest;
import com.axiom.fulfillment.model.UserDetails;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class OrderDeliverySaudi extends BaseActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks {

    Button Btnimage1, Btnimage2,  upload;
    public static int GALLERY = 502;
    public static int CAMERA = 603;
    private String attachment = "null";
    private ImageView imageview_one, imageview_two;
    String base64img1, base64img2;
    int OABOID, OADBID;
    String orderno, cust_name;
    private UserSharedPreferences upref;
    SignatureView mSignature;
    EditText customer_name, order_no, auth_no;
    EditText id_name,id_number;
    Spinner id_type;
    Button clear;
    private String amount;
    private Uri imageUri;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest locationRequest;
    ScrollView scrollview;

    public String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_saudi);

        OABOID = getIntent().getIntExtra(constants.OABOID, 0);
        OADBID = getIntent().getIntExtra(constants.OADBID, 0);
        orderno = getIntent().getStringExtra(constants.ORDERNO);
        cust_name = getIntent().getStringExtra("cust_name");
        amount = getIntent().getStringExtra("order_amount");
        LinearLayout signlayout = findViewById(R.id.signlayout);
        mSignature = new SignatureView(getApplicationContext());
        mSignature.setBackgroundColor(Color.WHITE);

        // Dynamically adding Layout through code
        LinearLayout.LayoutParams ly = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
        signlayout.addView(mSignature, 0, ly);

        imageview_one = findViewById(R.id.image1);
        imageview_two = findViewById(R.id.image2);

        Btnimage1 = findViewById(R.id.btmimage1);
        Btnimage2 = findViewById(R.id.btmimage2);
        id_name=findViewById(R.id.id_name);
        id_number=findViewById(R.id.id_number);
        id_type=findViewById(R.id.id_type);

        upload = findViewById(R.id.upload);
        scrollview=findViewById(R.id.scrollview);
        customer_name = findViewById(R.id.cust_name);
        customer_name.setText(cust_name);
        order_no = findViewById(R.id.order_no);
        order_no.setText(orderno);
        clear = findViewById(R.id.clear);
        auth_no = findViewById(R.id.auth_no);
        EditText amonttxt = findViewById(R.id.order_amount);
        amonttxt.setText(amount);
        clear.setOnClickListener(this);
        getToken(this);

        Btnimage1.setOnClickListener(this);
        Btnimage2.setOnClickListener(this);
        upload.setOnClickListener(this);
        upref = new UserSharedPreferences(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.Order_Delivery));
        statusCheck();
        buildGoogleApiClient();
        createLocationRequest();

        mSignature.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        // Disable the scroll view to intercept the touch event
                        scrollview.requestDisallowInterceptTouchEvent(true);
                        return false;
                    case MotionEvent.ACTION_UP:
                        // Allow scroll View to interceot the touch event
                        scrollview.requestDisallowInterceptTouchEvent(false);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        scrollview.requestDisallowInterceptTouchEvent(true);
                        return false;
                    default:
                        return true;
                }
            }
        });
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void createLocationRequest() {
        int LOCATION_DISTANCE = 10;
        int LOCATION_UPDATE_INTERVAL = 8000;
        locationRequest = new LocationRequest();
        locationRequest.setInterval(LOCATION_UPDATE_INTERVAL); //in milliseconds
        locationRequest.setFastestInterval(5000); //in milliseconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(LOCATION_DISTANCE);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.clear:
                mSignature.clearSignature();
                break;

            case R.id.btmimage1:
                callcamera("image1");

                break;

            case R.id.btmimage2:
                callcamera("image2");

                break;


            case R.id.upload:
                uploaddata();
                break;

        }

    }

    private void uploaddata() {
        if (mSignature.getSignature() == null) {
            ShowToast("Please take the signature of customer.", OrderDeliverySaudi.this);
            return;
        }

        if (id_type.getSelectedItem().toString().equalsIgnoreCase("Select Id") ||
        id_name.getText().toString().isEmpty()|| id_number.getText().toString().isEmpty()) {
            ShowToast("Please enter all id details.", OrderDeliverySaudi.this);
            return;
        }


        DeliveryRequest req = new DeliveryRequest();
        BikerRequest bike = new BikerRequest();
        List<Attachements> pics = new ArrayList<>();
        req.setAction("DELIVER");
        bike.setOaboId(String.valueOf(OABOID));
        bike.setOadbId(String.valueOf(OADBID));
        bike.setOaboStatus("ORDER_DELIVERED");
        bike.setOaboComments("delivered by mobile .");
        bike.setOaboBankAuthCode(auth_no.getText().toString());
        if (mLastLocation != null) {
            bike.setOaboUserGps(mLastLocation.getLatitude() + "," + mLastLocation.getLongitude());
        }
        bike.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));
        bike.setOaboSignature("data:image/jpeg;base64," + convertbase64(mSignature.getSignature()));
        bike.setOaboSignFileName(cust_name.replace(" ", ""));
        bike.setOaboSignFileType("image/jpeg");
        bike.setDocumentIdType(id_type.getSelectedItem().toString());
        bike.setDocumentHolderName(id_name.getText().toString());
        bike.setDocumentId(id_number.getText().toString());

        req.setBikerRequest(bike);

        if (base64img1 != null && !base64img1.isEmpty()) {
            Attachements a1 = new Attachements();
            a1.setOabodId(1);
            a1.setOaboId(String.valueOf(OABOID));
            a1.setOadbId(String.valueOf(OADBID));
            a1.setOabodFile("data:image/jpeg;base64," + base64img1);
            a1.setOabodFileType("image/jpeg");
            a1.setOabodFileName(orderno + "_1");
            pics.add(a1);
        }
        if (base64img2 != null && !base64img2.isEmpty()) {
            Attachements a2 = new Attachements();
            a2.setOabodId(2);
            a2.setOaboId(String.valueOf(OABOID));
            a2.setOadbId(String.valueOf(OADBID));
            a2.setOabodFile("data:image/jpeg;base64," + base64img2);
            a2.setOabodFileType("image/jpeg");
            a2.setOabodFileName(orderno + "_2");
            pics.add(a2);
        }

        req.setUpdateDocs(pics);
        apicall(req);
    }

    private void apicall(DeliveryRequest req) {

        if(!internetavailable(OrderDeliverySaudi.this)) {
            ShowToast(getString(R.string.nointernet), OrderDeliverySaudi.this);
            return;
        }

        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        Call<CommonApiResponse> stringCall = apiService.deliverorder(req);
        startLoader("Saving delivery data", this);
        stringCall.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                stopLoader();
                if (response.body().getStatus().getOutResult()) {
                    ShowToast("Order Saved successfully.", OrderDeliverySaudi.this);
                    Intent intent = getIntent();
                    intent.putExtra("order_status", "delivered");
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDeliverySaudi.this);
                }

            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                stopLoader();
                ShowToast("Error Please try again", OrderDeliverySaudi.this);
            }
        });

    }

    private void callcamera(String img) {

        PackageManager packageManager = getApplicationContext().getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    callPermission();
                }
            }
            else if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    callPermission();
                }
            }

            else if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    callPermission();
                }
            }

            else if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    callPermission();
                }
            }

            else
                showPictureDialog(img);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void callPermission() {

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION}, 5);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 5) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                   /* PackageManager packageManager = getApplicationContext().getPackageManager();
                    if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
                    {*/
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }

                }
            }

        }
    }

    private void showPictureDialog(String attachhment_section) {
        attachment = attachhment_section;
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                openCameraIntent();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private File createImageFile() {
        int startindex=0;
        if(orderno.length()>10)
            startindex=orderno.length()-10;
//
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        return new File(storageDir, orderno.substring(startindex,orderno.length()) + "_" + attachment.replace("image","")+".JPEG");

    }


    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile=null;
            try {
                photoFile = createImageFile();
                mCurrentPhotoPath=photoFile.getAbsolutePath();
            } catch (Exception ex) {
                ex.getLocalizedMessage();

            }
            if (photoFile != null) {

                if (photoFile != null) {
                    imageUri = FileProvider.getUriForFile(this, "com.axiom.fulfillment.provider", photoFile);
                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            imageUri);
                    startActivityForResult(pictureIntent,
                            CAMERA);
                }

            }
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            attachment = "null";
            return;
        }
        else if (requestCode == GALLERY) {
            if (data != null) {

                try {
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    if(cursor != null) {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(projection[0]);
                        Bitmap bitmap = resizeGalleryImageNSave(cursor.getString(columnIndex));
                        cursor.close();

                        switch (attachment) {
                            case "image1":
                                imageview_one.setImageBitmap(bitmap);
                                base64img1 = convertbase64(bitmap);
                                break;
                            case "image2":
                                imageview_two.setImageBitmap(bitmap);
                                base64img2 = convertbase64(bitmap);
                                break;

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(OrderDeliverySaudi.this, "Failed!", Toast.LENGTH_LONG).show();
                }
            }

        } else if (requestCode == CAMERA) {
            try {
                Bitmap thumbnail = resizeNSave();

                switch (attachment) {
                    case "image1":
                        imageview_one.setImageBitmap(thumbnail);
                        base64img1 = convertbase64(thumbnail);
                        break;
                    case "image2":
                        imageview_two.setImageBitmap(thumbnail);
                        base64img2 = convertbase64(thumbnail);
                        break;

                }
            } catch (Exception e) {
                Log.d("UPLOAD", e.getLocalizedMessage() + "");
                Toast.makeText(OrderDeliverySaudi.this, "Failed!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static String convertbase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    private Bitmap resizeNSave() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Matrix matrix = new Matrix();
        try {
            ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
                matrix.postRotate(90);
            else if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
                matrix.postRotate(180);
            else if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
                matrix.postRotate(270);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath,
                options);
        if(bitmap!=null) {
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            File file;
            file = new File(mCurrentPhotoPath);
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }




    public Bitmap resizeGalleryImageNSave( String path) {
        File photoFile;
        try {
            photoFile = createImageFile();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            Matrix matrix = new Matrix();

            try {
                ExifInterface ei = new ExifInterface(path);
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
                    matrix.postRotate(90);
                else if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
                    matrix.postRotate(180);
                else if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
                    matrix.postRotate(270);
            }
            catch (Exception e){
                e.printStackTrace();
            }


            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            if (bitmap != null) {
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                File file;
                file = new File(photoFile.getAbsolutePath());
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
//            mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                callPermission();
                return;
            }
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    /* Second part*/

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }
}

