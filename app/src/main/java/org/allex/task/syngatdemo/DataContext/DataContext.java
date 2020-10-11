package org.allex.task.syngatdemo.DataContext;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.BasicAuthenticator;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Endpoint;
import com.couchbase.lite.Replicator;
import com.couchbase.lite.ReplicatorConfiguration;
import com.couchbase.lite.URLEndpoint;

import org.allex.task.syngatdemo.BuildConfig;

import java.net.URI;
import java.net.URISyntaxException;

public class DataContext {
    private Database database;
    private static DataContext instance = null;

    public Database getDatabase() {
        return database;
    }

    protected DataContext(Context context) {

        //Obtiene la base de datos o la crea si no existe
        DatabaseConfiguration dbConfig = new DatabaseConfiguration(context);
        try{
            database = new Database(BuildConfig.DB, dbConfig);
        }catch (CouchbaseLiteException ex){
            Log.e("error", ex.getMessage());
        }

        startReplication(database);
    }


    //Metodo que nos permite obtener una instacia de nuestro contexto de datos
    public static DataContext getSharedDataContext(Context context){
        instance = instance == null ? new DataContext(context) : instance;

        return instance;
    }

    public void startReplication(Database database){
        //Crea la URI del recurso a manejar
        Endpoint endpoint = null;
        try{
            endpoint = new URLEndpoint(new URI(BuildConfig.ENDPOINT));
        }catch (URISyntaxException ex){
            Log.e("error", ex.getMessage());
        }

        //Opciones de replicación de los datos
        ReplicatorConfiguration replicatorConfiguration = new ReplicatorConfiguration(database, endpoint);
        replicatorConfiguration.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL);
        replicatorConfiguration.setAuthenticator(new BasicAuthenticator(BuildConfig.USER, BuildConfig.PASSWORD));

        //Crea el replicador de datos
        Replicator replicator = new Replicator(replicatorConfiguration);

        replicator.addChangeListener(change -> {
            if(change.getStatus().getError() != null){
                Log.e("error", "Error code: "+change.getStatus().getError().getCode());
            }
        });

        //Inicia la replicación
        replicator.start();
    }
}
