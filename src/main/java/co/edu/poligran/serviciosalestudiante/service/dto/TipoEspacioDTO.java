package co.edu.poligran.serviciosalestudiante.service.dto;

public class TipoEspacioDTO extends BaseDTO {
    public static final String CUBICULO_ESTUDIO = "CUBICULO_ESTUDIO";
    public static final String CUBICULO_VIDEO = "CUBICULO_VIDEO";
    public static final String GIMNASIO = "GIMNASIO";
    public static final String CANCHA_TENIS = "CANCHA_TENIS";
    public static final String CANCHA_MULTIPLE = "CANCHA_MULTIPLE";
    public static final String CANCHA_FUTBOL = "CANCHA_FUTBOL";
    public static final String COMPUTADOR="COMPUTADOR";
    public static final String LABORATORIO_FISICA="LABORATORIO_FISICA";
    public static final String LABORATORIO_QUIMICA="LABORATORIO_QUIMICA";
    public static final String LABORATORIO_ELECTRONICA="LABORATORIO_ELECTRONICA";
    public static final String LABORATORIO_TELECO="LABORATORIO_TELECO";

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
