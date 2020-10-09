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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
