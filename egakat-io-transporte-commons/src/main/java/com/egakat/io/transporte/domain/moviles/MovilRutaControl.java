package com.egakat.io.transporte.domain.moviles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import com.egakat.integration.commons.archivos.domain.Registro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "moviles_rutacontrol")
@DynamicUpdate
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class MovilRutaControl extends Registro {

	public static final String MOVIL = "MOVIL";
	public static final String PLACA = "PLACA";

	@Column(name = "movil", length = 20)
	@Size(max = 20)
	@NotNull
	private String movil;

	@Column(name = "placa", length = 20)
	@Size(max = 20)
	@NotNull
	private String placa;

	@Override
	public String getIdCorrelacion() {
		return getMovil();
	}

	@Override
	public Object getObjectValueFromProperty(String property) {
		switch (property) {
		case MOVIL:
			return getMovil();
		case PLACA:
			return getPlaca();
		default:
			return null;
		}
	}

	@Override
	public boolean isHomologableProperty(String property) {
		return false;
	}

	@Override
	protected String getStringValueFromHomologableProperty(String property) {
		return null;
	}

	@Override
	protected Object getObjectValueFromHomologousProperty(String property) {
		return null;
	}
}
