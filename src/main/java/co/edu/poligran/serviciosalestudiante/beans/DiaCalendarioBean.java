package co.edu.poligran.serviciosalestudiante.beans;

import java.util.Date;

public class DiaCalendarioBean {
    private Date dia;

    public Date getDia() {
        return this.dia != null ? (Date) this.dia.clone() : null;
    }

    public void setDia(Date dia) {
        this.dia = (dia != null ? (Date) dia.clone() : null);
    }
}
