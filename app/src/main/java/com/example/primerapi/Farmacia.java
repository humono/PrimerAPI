package com.example.primerapi;

import java.io.Serializable;

public class Farmacia implements Serializable {
    public String nombre;
    public String fecha;
    public String turno;
    public String horario;
    public String sector;

    public Farmacia() {
    }

    public Farmacia(String nombre, String fecha, String turno, String horario, String sector) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.turno = turno;
        this.horario = horario;
        this.sector = sector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getResumen() {
        return this.nombre + "\n" +
                "\t\t\t FECHA: " + this.fecha + "\n" +
                "\t\t\t TURNO: " + this.turno + "\n" +
                "\t\t\t HORARIO: " + this.horario + "\n" +
                "\t\t\t SECTOR: " + this.sector + "\n\n";
    }
}
