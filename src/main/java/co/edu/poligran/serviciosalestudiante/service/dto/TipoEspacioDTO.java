package co.edu.poligran.serviciosalestudiante.service.dto;

public class TipoEspacioDTO extends BaseDTO {
    public static String CUBICULO_ESTUDIO = "CUBICULO_ESTUDIO";
    public static String CUBICULO_VIDEO = "CUBICULO_VIDEO";
    public static String GIMNASIO = "GIMNASIO";
    public static String CANCHA_TENIS = "CANCHA_TENIS";
    public static String CANCHA_MULTIPLE = "CANCHA_MULTIPLE";
    public static String CANCHA_FUTBOL = "CANCHA_FUTBOL";

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
