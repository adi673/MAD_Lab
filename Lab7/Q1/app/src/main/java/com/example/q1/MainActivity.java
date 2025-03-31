package com.example.q1;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<AppInfo> appList;
    private AppAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listView = findViewById(R.id.appListView);
        appList = getInstalledApps();
        adapter = new AppAdapter(this, appList);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }
    private List<AppInfo> getInstalledApps() {
        List<AppInfo> apps = new ArrayList<>();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        for (ApplicationInfo app : packages) {
            String name = pm.getApplicationLabel(app).toString();
            String packageName = app.packageName;
            apps.add(new AppInfo(name, packageName));
        }
        return apps;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.app_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AppInfo app = appList.get(info.position);

        switch (item.getItemId()) {
            case R.id.:
                startActivity(getPackageManager().getLaunchIntentForPackage(app.getPackageName()));
                return true;

            case R.id.menu_uninstall:
                startActivity(new Intent(Intent.ACTION_DELETE, android.net.Uri.parse("package:" + app.getPackageName())));
                return true;

            case R.id.menu_details:
                Intent intent = new Intent(this, activity_app_details.class);
                intent.putExtra("package", app.getPackageName());
                startActivity(intent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}