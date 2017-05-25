package co.edu.poligran.serviciosalestudiante.service.dto;

import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;

public class RoleDTO extends BaseDTO {
    private static final long serialVersionUID = 3603880991336955182L;
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
