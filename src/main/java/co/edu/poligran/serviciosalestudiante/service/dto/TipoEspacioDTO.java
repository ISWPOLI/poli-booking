package co.edu.poligran.serviciosalestudiante.service.dto;

public class TipoEspacioDTO extends BaseDTO {
    public static final String CUBICULO_ESTUDIO = "CUBICULO_ESTUDIO";
    public static final String CUBICULO_VIDEO = "CUBICULO_VIDEO";
    public static final String GIMNASIO = "GIMNASIO";
    public static final String CANCHA_TENIS = "CANCHA_TENIS";
    public static final String CANCHA_MULTIPLE = "CANCHA_MULTIPLE";
    public static final String CANCHA_FUTBOL = "CANCHA_FUTBOL";

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
