package com.example.tugas6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import android.os.Bundle;
import android.view.View;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(MyWork.class, 70, TimeUnit.SECONDS)
                .setInitialDelay(1, TimeUnit.MINUTES)
                .addTag("periodic")
                .build();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniquePeriodicWork("periodic", ExistingPeriodicWorkPolicy.REPLACE, request);
            }
        });
    }
}
