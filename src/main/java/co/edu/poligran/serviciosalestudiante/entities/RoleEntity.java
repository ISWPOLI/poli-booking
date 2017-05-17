package co.edu.poligran.serviciosalestudiante.entities;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
