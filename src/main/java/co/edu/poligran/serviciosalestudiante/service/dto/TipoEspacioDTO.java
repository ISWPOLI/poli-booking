package co.edu.poligran.serviciosalestudiante.service.dto;

public class TipoEspacioDTO extends BaseDTO {
    public static String CUBICULO_ESTUDIO = "CUBICULO_ESTUDIO";
    public static String CUBICULO_VIDEO = "CUBICULO_VIDEO";

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
