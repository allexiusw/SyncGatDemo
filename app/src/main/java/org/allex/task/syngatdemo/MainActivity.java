package org.allex.task.syngatdemo;

import android.app.Activity;
import android.os.Bundle;
import com.couchbase.lite.CouchbaseLite;
import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Database;
import com.couchbase.lite.MutableDocument;

public class MainActivity extends Activity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        // Initialize the Couchbase Lite system
        CouchbaseLite.init(context);

        // Get the database (and create it if it doesn’t exist).
        DatabaseConfiguration config = new DatabaseConfiguration();
        try {
            database = new Database("ugb", config);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        // Create a new document (i.e. a record) in the database.
        MutableDocument mutableDoc = new MutableDocument()
                .setString("name", "Tarea desde Android")
                .setBoolean("status", true);

        // Save it to the database.

        try {
            database.save(mutableDoc);
            Log.i("success", "Datos guardados");
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
            Log.e("Error", "Error al guardar");
        }

    }
}
