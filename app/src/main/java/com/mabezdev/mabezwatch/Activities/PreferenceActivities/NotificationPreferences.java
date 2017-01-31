package com.mabezdev.mabezwatch.Activities.PreferenceActivities;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.mabezdev.mabezwatch.Adapters.NotificationSettingAdapter;
import com.mabezdev.mabezwatch.Model.AppInfoItem;
import com.mabezdev.mabezwatch.Model.NotificationSettingItem;
import com.mabezdev.mabezwatch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mabez on 30/01/17.
 */
public class NotificationPreferences extends AppCompatActivity {

    private ListView notificationSettingsListView;
    private ArrayAdapter<NotificationSettingItem> notificationSettingItemArrayAdapter;
    private ArrayList<NotificationSettingItem> rowArray;

    private static final String TAG = NotificationPreferences.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_notifications);

        rowArray = loadPackageData();
        System.out.println(rowArray.get(0).getPackageName());

        notificationSettingItemArrayAdapter = new NotificationSettingAdapter(this,R.layout.view_item_notification_preferences,rowArray);

        notificationSettingsListView = (ListView) findViewById(R.id.notificationSettingListView);
        notificationSettingsListView.setAdapter(notificationSettingItemArrayAdapter);
    }

    private ArrayList<NotificationSettingItem> loadPackageData() {
        // eventually load from a file
        ArrayList<NotificationSettingItem> toReturn = new ArrayList<>();
        final PackageManager pm = getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Drawable icon = pm.getApplicationIcon(packageInfo);
            String name = (String) pm.getApplicationLabel(packageInfo);

            // TODO instead of creating new info we should use the data in AppINfoHandler
            AppInfoItem appInfo = new AppInfoItem(packageInfo.packageName,false,false);

            toReturn.add(new NotificationSettingItem(name,icon, appInfo));
        }
        return toReturn;
    }
}
