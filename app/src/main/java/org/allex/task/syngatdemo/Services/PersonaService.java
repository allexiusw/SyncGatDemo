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
import org.allex.task.syngatdemo.Utils.GenericObjectResponse;
import java.util.ArrayList;

public class PersonaService implements IPersonaService {

    private DataContext _dataContext;
    private String NOT_FOUND_RESPONSE = "No se ha encontrado el registro.";

    public PersonaService(Context context) {
        _dataContext = DataContext.getSharedDataContext(context);
    }

    public PersonaService() {

    }

    public PersonaService(String primerNombre, String primerApellido) {
    }


    //*******************************************************************************
    //Obtiene un listado de personas que no hayan sido eliminados
    public ArrayList<Persona> get(){
        //Selecciona los datos del documento
        Query query = QueryBuilder.select(
                SelectResult.expression(Meta.id),
                SelectResult.property("PrimerNombre"),
                SelectResult.property("PrimerApellido"))
                .from(DataSource.database(_dataContext.getDatabase()))
                .where(Expression.property("IsDeleted").equalTo(Expression.booleanValue(false))
                .add(Expression.property("IsPersona").equalTo(Expression.booleanValue(true))));
        ArrayList<Persona> personas = new ArrayList<>();
        try {

            //Busca los datos en la BD y los recorre
            ResultSet result = query.execute();
            for (Result r : result) {
                personas.add(new Persona(r.getString("id"), r.getString("PrimerNombre"),
                        r.getString("PrimerApellido")));
            }
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return personas;
    }

    //Obtiene un documento por id
    public GenericObjectResponse<Boolean, Persona> getById(String id){
        Document document = _dataContext.getDatabase().getDocument(id);
        Persona persona = document != null ? new Persona(document.getId(), document.getString("PrimerNombre"),
                document.getString("SegundoNombre"), document.getString("PrimerApellido"),
                document.getString("SegundoApellido"), document.getDate("FechaNacimiento")) : null;

        //Devuelve true y la persona si la encuentra, de lo contrario, false y null respectivamente
        GenericObjectResponse<Boolean, Persona> response = new GenericObjectResponse<>(persona != null ? true : false, persona);
        return response;
    }

    //Crea una nueva persona
    public GenericObjectResponse<Boolean, String> create(Persona persona){

        //Agrega los valores al nuevo documento
        MutableDocument mutableDoc = new MutableDocument()
                .setString("PrimerNombre", persona.getPrimerNombre())
                .setString("SegundoNombre", persona.getSegundoNombre())
                .setString("PrimerApellido", persona.getPrimerApellido())
                .setString("SegundoApellido", persona.getSegundoApellido())
                .setDate("FechaNacimiento", persona.getFechaNacimiento())
                .setBoolean("IsDeleted", false)
                .setBoolean("IsPersona", true);
        GenericObjectResponse<Boolean, String> response = null;
        try {
            //Guarda los valores en la BD y devuelve true con el id del nuevo documento
            _dataContext.getDatabase().save(mutableDoc);
            response = new GenericObjectResponse<>(true, mutableDoc.getId());
            _dataContext.startReplication(_dataContext.getDatabase());
        } catch (CouchbaseLiteException ex) {
            Log.e("error", ex.getMessage());

            //Si ocurre un error devuelve false y null
            response = new GenericObjectResponse<>(false, null);
        }
        return response;
    }

    //Actualiza un documento mediante el id
    public GenericObjectResponse<Boolean, String> update(String id, Persona persona){

        //Busca el documento por id
        MutableDocument mutableDoc = _dataContext.getDatabase().getDocument(id).toMutable();

        //Si no encuentra el documento devuelve false y un mensaje de error.
        GenericObjectResponse response = null;
        if(mutableDoc == null)
            return new GenericObjectResponse<>(false, NOT_FOUND_RESPONSE);

        //Agrega los nuevos valores al documento
        mutableDoc.setString("PrimerNombre", persona.getPrimerNombre())
                .setString("SegundoNombre", persona.getSegundoNombre())
                .setString("PrimerApellido", persona.getPrimerApellido())
                .setString("SegundoApellido", persona.getSegundoApellido())
                .setDate("FechaNacimiento", persona.getFechaNacimiento());

        try {
            //Actualiza los valores en la BD y devuelve true con el id del documento
            _dataContext.getDatabase().save(mutableDoc);
            response = new GenericObjectResponse<>(true, mutableDoc.getId());
            _dataContext.startReplication(_dataContext.getDatabase());
        } catch (CouchbaseLiteException ex) {
            Log.e("error", ex.getMessage());
            //Si ocurre un error devuelve false y null
            response = new GenericObjectResponse<>(false, "Ha ocurrido un error al intentar actualizar el registro.");
        }
        return response;
    }

    //Elimina un documento mediante el id
    public GenericObjectResponse<Boolean, String> delete(String id){
        Document document = _dataContext.getDatabase().getDocument(id);

        GenericObjectResponse response = null;
        try {
            if(document != null){
                //Si encuentra el documento lo elimina, devuelve true y un mensaje
                _dataContext.getDatabase().delete(document);
                response = new GenericObjectResponse<>(true, "Se ha eliminado el registro.");
                _dataContext.startReplication(_dataContext.getDatabase());
            }else{
                //Si no encuentra el documento devuelve false y un mensaje
                response = new GenericObjectResponse<>(false, NOT_FOUND_RESPONSE);
            }

        } catch (CouchbaseLiteException ex) {
            Log.e("error", ex.getMessage());
            //Si ocurre un error devuelve false y null
            response = new GenericObjectResponse<>(false, "Un error interno ha ocurrido.");
        }
        return response;
    }

    //Cambia el estado IsDeleted a true a un documento mediante el id
    //Metodo alternativo para eliminar
    public GenericObjectResponse<Boolean, String> setDelete(String id){

        //Busca el documento por id
        MutableDocument mutableDoc = _dataContext.getDatabase().getDocument(id).toMutable();

        //Si no encuentra el documento devuelve false y un mensaje de error.

        if(mutableDoc == null)
            return new GenericObjectResponse<>(false, NOT_FOUND_RESPONSE);

        GenericObjectResponse response = null;
        //Agrega los nuevos valores al documento
        mutableDoc.setBoolean("IsDeleted", true);

        try {
            //Actualiza los valores en la BD y devuelve true con el id del documento
            _dataContext.getDatabase().save(mutableDoc);
            response = new GenericObjectResponse<>(true, "Se ha eliminado el registro.");
            _dataContext.startReplication(_dataContext.getDatabase());
        } catch (CouchbaseLiteException ex) {
            Log.e("error", ex.getMessage());
            //Si ocurre un error devuelve false y null
            response = new GenericObjectResponse<>(false, "Un error interno ha ocurrido.");
        }
        return response;
    }
}
