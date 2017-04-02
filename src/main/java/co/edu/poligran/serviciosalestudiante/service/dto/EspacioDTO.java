package co.edu.poligran.serviciosalestudiante.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class EspacioDTO extends BaseDTO {
    private TipoEspacioDTO tipoEspacio;
    private String nombre;

    @JsonIgnore
    private List<BloqueDTO> bloques;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<BloqueDTO> getBloques() {
        return bloques;
    }

    public void setBloques(List<BloqueDTO> bloques) {
        this.bloques = bloques;
    }

    public TipoEspacioDTO getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacioDTO tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

}
