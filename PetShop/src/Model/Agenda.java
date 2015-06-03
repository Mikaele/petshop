/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author orientador
 */
public class Agenda {
    int idAnimal, idServiço ,id;
    String horario;

    public Agenda(int idAnimal, int idServiço, int id, String horario) {
        this.idAnimal = idAnimal;
        this.idServiço = idServiço;
        this.id = id;
        this.horario = horario;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdServiço() {
        return idServiço;
    }

    public void setIdServiço(int idServiço) {
        this.idServiço = idServiço;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    

    
}
