package com.egakat.io.transporte.domain.programaciones;

import java.time.LocalDate;

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
@Table(name = "programaciones_manuales")
@DynamicUpdate
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class ProgramacionRutaManual extends Registro {

	public static final String PLACA = "PLACA";
	public static final String SECUENCIA = "SECUENCIA";
	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String NUMERO_SOLICITUD = "NUMERO_SOLICITUD";
	public static final String FECHA_ESTIMADA = "FECHA_ESTIMADA";

	@Column(name = "placa", length = 20)
	@Size(max = 20)
	@NotNull
	private String placa;

	@Column(name = "secuencia", nullable = false)
	private int secuencia;

	@Column(name = "cliente_codigo", length = 20)
	@Size(max = 20)
	@NotNull
	private String clienteCodigo;

	@Column(name = "numero_solicitud", length = 20)
	@Size(max = 20)
	@NotNull
	private String numeroSolicitud;

	@Column(name = "fecha_estimada", nullable = false)
	@NotNull
	private LocalDate fechaEstimada;

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
		
		case PLACA:
			return getPlaca();
		case SECUENCIA:
			return getSecuencia();
		case FECHA_ESTIMADA:
			return getFechaEstimada();
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