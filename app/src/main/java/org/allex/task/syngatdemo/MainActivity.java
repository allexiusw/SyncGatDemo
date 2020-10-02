package org.allex.task.syngatdemo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import com.couchbase.lite.DatabaseConfiguration;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        DatabaseConfiguration config = new DatabaseConfiguration(context);
    }
}
