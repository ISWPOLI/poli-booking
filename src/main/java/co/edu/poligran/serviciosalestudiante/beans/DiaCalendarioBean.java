package co.edu.poligran.serviciosalestudiante.beans;

import java.io.Serializable;
import java.util.Date;

public class DiaCalendarioBean implements Serializable {
    private static final long serialVersionUID = -6605456356481775458L;

    private Date dia;

    public Date getDia() {
        return this.dia != null ? (Date) this.dia.clone() : null;
    }

    public void setDia(Date dia) {
        this.dia = (dia != null ? (Date) dia.clone() : null);
    }
}
