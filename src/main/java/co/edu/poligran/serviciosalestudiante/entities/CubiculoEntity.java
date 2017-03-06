package co.edu.poligran.serviciosalestudiante.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CUBICULO")
public class CubiculoEntity extends EspacioEntity {
	private static final long serialVersionUID = 7761231275177537433L;

}
