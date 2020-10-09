package org.allex.task.syngatdemo.Entities;

import java.util.Date;

public class Persona {
    private String Id;
    private String PrimerNombre;
    private String SegundoNombre;
    private String PrimerApellido;
    private String SegundoApellido;
    private Date FechaNacimiento;
    private boolean IsDeleted;
    private boolean IsPersona;

    public Persona() {
    }

    public Persona(String id, String primerNombre, String segundoNombre, String primerApellido,
                   String segundoApellido, Date fechaNacimiento, boolean isDeleted, boolean isPersona) {
        Id = id;
        PrimerNombre = primerNombre;
        SegundoNombre = segundoNombre;
        PrimerApellido = primerApellido;
        SegundoApellido = segundoApellido;
        FechaNacimiento = fechaNacimiento;
        IsDeleted = isDeleted;
        IsPersona = isPersona;
    }

    public Persona(String id, String primerNombre, String segundoNombre, String primerApellido,
                   String segundoApellido, Date fechaNacimiento) {
        Id = id;
        PrimerNombre = primerNombre;
        SegundoNombre = segundoNombre;
        PrimerApellido = primerApellido;
        SegundoApellido = segundoApellido;
        FechaNacimiento = fechaNacimiento;
    }

    public Persona(String primerNombre, String segundoNombre, String primerApellido,
                   String segundoApellido, Date fechaNacimiento) {
        PrimerNombre = primerNombre;
        SegundoNombre = segundoNombre;
        PrimerApellido = primerApellido;
        SegundoApellido = segundoApellido;
        FechaNacimiento = fechaNacimiento;
    }

    public Persona(String id, String primerNombre, String primerApellido) {
        Id = id;
        PrimerNombre = primerNombre;
        PrimerApellido = primerApellido;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPrimerNombre() {
        return PrimerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        PrimerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return SegundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        SegundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return PrimerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        PrimerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return SegundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        SegundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public boolean isPersona() {
        return IsPersona;
    }

    public void setPersona(boolean persona) {
        IsPersona = persona;
    }
}
