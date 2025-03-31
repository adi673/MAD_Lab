package com.example.q1;



import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class AppDetailsActivity extends AppCompatActivity {

    private TextView appName, packageName, appVersion, appSize, appPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        // Initialize TextViews
        appName = findViewById(R.id.appName);
        packageName = findViewById(R.id.packageName);
        appVersion = findViewById(R.id.appVersion);
        appSize = findViewById(R.id.appSize);
        appPermissions = findViewById(R.id.appPermissions);

        // Get the selected app's package name from the intent
        String packageNameStr = getIntent().getStringExtra("package");

        if (packageNameStr != null) {
            displayAppDetails(packageNameStr);
        } else {
            Toast.makeText(this, "Failed to load app details", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void displayAppDetails(String packageNameStr) {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(packageNameStr, PackageManager.GET_PERMISSIONS);
            ApplicationInfo appInfo = pm.getApplicationInfo(packageNameStr, 0);

            // Set app name and package name
            appName.setText("App: " + pm.getApplicationLabel(appInfo));
            packageName.setText("Package: " + packageNameStr);

            // Set version
            appVersion.setText("Version: " + packageInfo.versionName);

            // Get app size
            File file = new File(appInfo.sourceDir);
            long size = file.length() / (1024 * 1024); // Convert bytes to MB
            appSize.setText("Size: " + size + " MB");

            // Display permissions
            if (packageInfo.requestedPermissions != null) {
                StringBuilder permissions = new StringBuilder("Permissions:\n");
                for (String perm : packageInfo.requestedPermissions) {
                    permissions.append(perm).append("\n");
                }
                appPermissions.setText(permissions.toString());
            } else {
                appPermissions.setText("No permissions requested.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to retrieve app details", Toast.LENGTH_SHORT).show();
        }
    }
}
