package co.edu.poligran.serviciosalestudiante.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_ESPACIO")
public class TipoEspacioEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
