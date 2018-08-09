package com.egakat.io.transporte.domain.programaciones;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import com.egakat.integration.files.domain.Registro;
import com.egakat.integration.files.enums.EstadoRegistroType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "remesas_programadas")
@DynamicUpdate
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class RemesaProgramada extends Registro {

	public static final String PLACA_PROGRAMADA = "PLACA_PROGRAMADA";
	public static final String FECHA_PROGRAMADA = "FECHA_PROGRAMADA";

	public static final String CLIENTE_IDENTIFICACION = "CLIENTE_IDENTIFICACION";
	public static final String NUMERO_SOLICITUD = "NUMERO_SOLICITUD";

	public static final String ES_SERVICIO_DEDICADO = "ES_SERVICIO_DEDICADO";
	public static final String PAQUETEADORA_CODIGO = "PAQUETEADORA_CODIGO";

	@Column(name = "placa_programada", length = 20)
	@NotEmpty
	private String placaProgramada;

	@Column(name = "fecha_programada")
	@NotNull
	private LocalDate fechaProgramada;

	@Column(name = "cliente_identificacion", length = 20)
	@NotEmpty
	private String clienteIdentificacion;

	@Column(name = "numero_solicitud", length = 20)
	@NotNull
	private String numeroSolicitud;

	@Column(name = "es_servicio_dedicado")
	private boolean servicioDedicado;

	@Column(name = "paqueteadora_codigo", length = 50)
	@NotNull
	private String paqueteadoraCodigo;

	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "id_solicitud")
	private Long idSolicitud;

	@Override
	public String getIdCorrelacion() {
		return String.format("[%s][%s]", getClienteIdentificacion(), getNumeroSolicitud());
	}

	@Builder
	public RemesaProgramada(Long id, int version, LocalDateTime FechaCreacion, String createdBy,
			LocalDateTime FechaModificacion, String modifiedBy, Long idArchivo, EstadoRegistroType estado,
			int numeroLinea, @NotEmpty String placaProgramada, @NotNull LocalDate fechaProgramada,
			@NotEmpty String clienteIdentificacion, @NotNull String numeroSolicitud, boolean servicioDedicado,
			@NotNull String paqueteadoraCodigo, Long idCliente, Long idSolicitud) {
		super(id, version, FechaCreacion, createdBy, FechaModificacion, modifiedBy, idArchivo, estado, numeroLinea);
		this.placaProgramada = placaProgramada;
		this.fechaProgramada = fechaProgramada;
		this.clienteIdentificacion = clienteIdentificacion;
		this.numeroSolicitud = numeroSolicitud;
		this.servicioDedicado = servicioDedicado;
		this.paqueteadoraCodigo = paqueteadoraCodigo;
		this.idCliente = idCliente;
		this.idSolicitud = idSolicitud;
	}

	@Override
	public Object getObjectValueFromProperty(String property) {
		switch (property) {
		case CLIENTE_IDENTIFICACION:
		case NUMERO_SOLICITUD:
			return getStringValueFromHomologableProperty(property);
		
		case PLACA_PROGRAMADA:
			return getPlacaProgramada();
		case FECHA_PROGRAMADA:
			return (getFechaProgramada() == null);
		case ES_SERVICIO_DEDICADO:
			return isServicioDedicado();
		case PAQUETEADORA_CODIGO:
			return getPaqueteadoraCodigo();
		default:
			return null;
		}
	}

	@Override
	public boolean isHomologableProperty(String property) {
		switch (property) {
		case CLIENTE_IDENTIFICACION:
		case NUMERO_SOLICITUD:
			return true;
		default:
			return false;
		}
	}

	@Override
	protected String getStringValueFromHomologableProperty(String property) {
		switch (property) {
		case CLIENTE_IDENTIFICACION:
			return getClienteIdentificacion();
		case NUMERO_SOLICITUD:
			return getNumeroSolicitud();
		default:
			return null;
		}
	}

	@Override
	protected Object getObjectValueFromHomologousProperty(String property) {
		switch (property) {
		case CLIENTE_IDENTIFICACION:
			return getIdCliente();
		case NUMERO_SOLICITUD:
			return getIdSolicitud();
		default:
			return null;
		}
	}
}
