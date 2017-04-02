package co.edu.poligran.serviciosalestudiante.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_ESPACIO")
public class TipoEspacioEntity extends BaseEntity {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
