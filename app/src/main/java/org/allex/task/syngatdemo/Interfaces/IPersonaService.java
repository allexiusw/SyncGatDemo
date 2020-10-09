package org.allex.task.syngatdemo.Interfaces;

import org.allex.task.syngatdemo.Entities.Persona;
import org.allex.task.syngatdemo.Utils.GenericObjectResponse;

import java.util.ArrayList;

public interface IPersonaService {
    ArrayList<Persona> get();
    GenericObjectResponse<Boolean, Persona> getById(String id);
    GenericObjectResponse<Boolean, String> create(Persona persona);
    GenericObjectResponse<Boolean, String> update(String id, Persona persona);
    GenericObjectResponse<Boolean, String> delete(String id, Persona persona);
}
