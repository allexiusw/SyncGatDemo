package org.allex.task.syngatdemo.Services;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Document;
import com.couchbase.lite.Expression;
import com.couchbase.lite.Meta;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;

import org.allex.task.syngatdemo.DataContext.DataContext;
import org.allex.task.syngatdemo.Entities.Persona;
import org.allex.task.syngatdemo.Interfaces.IPersonaService;

import java.util.ArrayList;
import java.util.Date;

public class PersonaService implements IPersonaService {

    private DataContext _dataContext;

    public PersonaService(Context context) {
        _dataContext = DataContext.getSharedDataContext(context);
    }

    public ArrayList<Persona> get(){
        Query query = QueryBuilder.select(
                SelectResult.expression(Meta.id),
                SelectResult.property("PrimerNombre"),
                SelectResult.property("PrimerApellido"))
                .from(DataSource.database(_dataContext.getDatabase()))
                .where(Expression.property("IsDeleted").equalTo(Expression.booleanValue(false))
                .add(Expression.property("IsPersona").equalTo(Expression.booleanValue(true))));
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            ResultSet result = query.execute();
            for (Result r : result) {
                personas.add(new Persona(r.getString(0), r.getString("PrimerNombre"),
                        r.getString("PrimerApellido")));
            }
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return personas;
    }

    public Persona getById(String id){
        Document document = _dataContext.getDatabase().getDocument(id);
        Persona persona = document != null ? new Persona(document.getId(), document.getString("PrimerNombre"),
                document.getString("SegundoNombre"), document.getString("PrimerApellido"),
                document.getString("SegundoApellido"), document.getDate("FechaNacimiento")) : null;
        return persona;
    }

    public String create(Persona persona){
        MutableDocument mutableDoc = new MutableDocument()
                .setString("PrimerNombre", persona.getPrimerApellido())
                .setString("SegundoNombre", persona.getSegundoApellido())
                .setString("PrimerApellido", persona.getPrimerApellido())
                .setString("SegundoApellido", persona.getSegundoApellido())
                .setDate("FechaNacimiento", persona.getFechaNacimiento())
                .setBoolean("IsDeleted", false)
                .setBoolean("IsPersona", true);
        String id = "";
        try {
            _dataContext.getDatabase().save(mutableDoc);
            id = mutableDoc.getId();
        } catch (CouchbaseLiteException ex) {
            Log.e("error", ex.getMessage());
        }
        return id;
    }
}
