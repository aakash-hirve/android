package com.akash.cowin.otpautomator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Akash Hirve
 */
public class MainActivity extends AppCompatActivity {

    private static final String MAIN_TAG = MainActivity.class.getSimpleName();
    private static final int PERM_RECEIVE_SMS = 0;
    EditText endpointUrl;
    Button submitButton;

    public static String url = "";

    public String getEndpointUrl() {
        return url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {

            } else {

                // Request for permissions to receive SMS
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS, Manifest.permission.INTERNET}, PERM_RECEIVE_SMS);
            }
        }

        // HTTPS endpoint url
        endpointUrl = (EditText) findViewById(R.id.endpointUrl);
        submitButton = (Button) findViewById(R.id.submit);

        // Submit button listener
        submitButton.setOnClickListener((v) -> {
           url = endpointUrl.getText().toString();
           Log.d(MAIN_TAG, "URL is received: "+url);
        });

    }

    /**
     * Handle the request permission result
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            case PERM_RECEIVE_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Granted.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}