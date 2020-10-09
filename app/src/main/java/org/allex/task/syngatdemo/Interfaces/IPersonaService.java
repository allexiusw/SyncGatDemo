package org.allex.task.syngatdemo.Interfaces;

import org.allex.task.syngatdemo.Entities.Persona;
import org.allex.task.syngatdemo.Utils.GenericResponse;

import java.util.ArrayList;

public interface IPersonaService {
    ArrayList<Persona> get();
    GenericResponse<Boolean, Persona> getById(String id);
    GenericResponse<Boolean, String> create(Persona persona);
    GenericResponse<Boolean, String> update(String id, Persona persona);
    GenericResponse<Boolean, String> delete(String id, Persona persona);
}
