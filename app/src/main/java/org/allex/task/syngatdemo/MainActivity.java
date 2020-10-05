package org.allex.task.syngatdemo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Database;
import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Meta;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.SelectResult;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Expression;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.Result;
import com.couchbase.lite.URLEndpoint;
import com.couchbase.lite.Endpoint;
import java.net.URI;
import java.net.URISyntaxException;
import com.couchbase.lite.BasicAuthenticator;
import com.couchbase.lite.Replicator;

import com.couchbase.lite.ReplicatorConfiguration;

public class MainActivity extends Activity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        // Get the database (and create it if it doesn’t exist).
        DatabaseConfiguration config = new DatabaseConfiguration(context);
        try {
            database = new Database(BuildConfig.DB, config);
        } catch (CouchbaseLiteException e) {
            Log.e("error", e.getMessage());
        }


        // Create a new document (i.e. a record) in the database.
        MutableDocument mutableDoc = new MutableDocument()
                .setString("name", "Tarea desde Android")
                .setBoolean("status", true);
        Toast.makeText(this, mutableDoc.getId(), Toast.LENGTH_SHORT).show();
        // Save it to the database.
        try {
            database.save(mutableDoc);
            Log.i("success", "Datos guardados");
        } catch (CouchbaseLiteException e) {
            Log.e("Error", "Error al guardar");
        }

        // Create a query to fetch documents of type SDK.
        Query query = QueryBuilder.select(SelectResult.expression(Meta.id),
                SelectResult.property("name"),
                SelectResult.property("status"))
                .from(DataSource.database(database))
                .where(Expression.property("status").equalTo(Expression.booleanValue(true)));


        try {
            ResultSet result = query.execute();
            for (Result r : result) {
                Log.i("success", r.getString(0));
                Log.i("success", r.getString("name"));
                Log.i("success", String.valueOf(r.getBoolean("status")));
                Log.i("success", "-----------------------------");
            }
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }


        // Create replicators to push and pull changes to and from the cloud.

        Endpoint targetEndpoint = null;
        try {
            targetEndpoint = new URLEndpoint(new URI(BuildConfig.ENDPOINT));
        } catch (URISyntaxException e) {
            Log.e("error", e.getMessage());
        }

        ReplicatorConfiguration replConfig = new ReplicatorConfiguration(database, targetEndpoint);
        replConfig.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL);
        // Add authentication.
        replConfig.setAuthenticator(new BasicAuthenticator(BuildConfig.USER, BuildConfig.PASSWORD));

        // Create replicator (be sure to hold a reference somewhere that will prevent the Replicator from being GCed)
        Replicator replicator = new Replicator(replConfig);

        // Listen to replicator change events.
        replicator.addChangeListener(change -> {
            if (change.getStatus().getError() != null) {
                Log.i("error", "Error code ::  " + change.getStatus().getError().getCode());
            }
        });

// Start replication.
        replicator.start();
    }
}
