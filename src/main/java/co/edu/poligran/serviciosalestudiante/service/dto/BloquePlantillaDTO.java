package co.edu.poligran.serviciosalestudiante.service.dto;


import java.time.DayOfWeek;
import java.util.Date;

public class BloquePlantillaDTO extends BaseDTO {
    private DayOfWeek dia;

    private TipoEspacioDTO tipoEspacio;

    private Date horaInicio;

    private Date horaFin;

    public BloqueDTO obtenerBloqueDTO(EspacioDTO espacioDTO, Date dia) {
        BloqueDTO bloqueDTO = new BloqueDTO();
        bloqueDTO.setEspacio(espacioDTO);
        bloqueDTO.setDia(dia);
        bloqueDTO.setTiempoInicio(new Date(dia.getTime() + getHoraInicio().getTime()));
        bloqueDTO.setTiempoFin(new Date(dia.getTime() + getHoraFin().getTime()));

        return bloqueDTO;
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    public TipoEspacioDTO getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacioDTO tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
}
