
package com.egakat.io.transporte.domain.programaciones;

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
@Table(name = "consultas_solicitudes")
@DynamicUpdate
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class ConsultaSolicitudes extends Registro {

	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String NUMERO_SOLICITUD = "NUMERO_SOLICITUD";

	@Column(name = "cliente_codigo", length = 20)
	@Size(max = 20)
	@NotNull
	private String clienteCodigo;

	@Column(name = "numero_solicitud", length = 20)
	@Size(max = 20)
	@NotNull
	private String numeroSolicitud;

	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "id_solicitud")
	private Long idSolicitud;

	@Override
	public String getIdCorrelacion() {
		return String.format("[%s][%s]", getClienteCodigo(), getNumeroSolicitud());
	}

	@Override
	public Object getObjectValueFromProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
		case NUMERO_SOLICITUD:
			return getStringValueFromHomologableProperty(property);
		default:
			return null;
		}
	}

	@Override
	public boolean isHomologableProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
		case NUMERO_SOLICITUD:
			return true;
		default:
			return false;
		}
	}

	@Override
	protected String getStringValueFromHomologableProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
			return getClienteCodigo();
		case NUMERO_SOLICITUD:
			return getNumeroSolicitud();
		default:
			return null;
		}
	}

	@Override
	protected Object getObjectValueFromHomologousProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
			return getIdCliente();
		case NUMERO_SOLICITUD:
			return getIdSolicitud();
		default:
			return null;
		}
	}
}