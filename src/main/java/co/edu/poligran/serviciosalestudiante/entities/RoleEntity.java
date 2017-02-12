package co.edu.poligran.serviciosalestudiante.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class RoleEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 30, unique = true, nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleTypeEnum type;

	public RoleTypeEnum getType() {
		return type;
	}

	public void setType(RoleTypeEnum type) {
		this.type = type;
	}
}
