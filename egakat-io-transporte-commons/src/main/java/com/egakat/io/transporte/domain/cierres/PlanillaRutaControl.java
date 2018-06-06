package com.egakat.io.transporte.domain.cierres;

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
@Table(name = "planillas_rutacontrol")
@DynamicUpdate
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class PlanillaRutaControl extends Registro {

	private static final long serialVersionUID = 1L;

	public static final String PLACA_PROGRAMADA = "PLACA_PROGRAMADA";
	public static final String FECHA_PROGRAMADA = "FECHA_PROGRAMADA";

	public static final String CLIENTE_IDENTIFICACION = "CLIENTE_IDENTIFICACION";
	public static final String NUMERO_SOLICITUD = "NUMERO_SOLICITUD";

	public static final String ESTADO_CODIGO_ALTERNO = "ESTADO_CODIGO_ALTERNO";
	public static final String CAUSAL_NOVEDAD_CODIGO_ALTERNO = "CAUSAL_NOVEDAD_CODIGO_ALTERNO";
	public static final String FECHA_HORA_INICIO = "FECHA_HORA_INICIO";
	public static final String FECHA_HORA_FIN = "FECHA_HORA_FIN";
	public static final String OBSERVACION = "OBSERVACION";

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

	@Column(name = "estado_codigo_alterno", length = 50)
	@NotEmpty
	private String estadoCodigoAlterno;

	@Column(name = "causal_novedad_codigo_alterno", length = 50)
	@NotNull
	private String causalNovedadCodigoAlterno;

	@Column(name = "fecha_hora_inicio")
	private LocalDateTime fechaHoraInicio;

	@Column(name = "fecha_hora_fin")
	private LocalDateTime fechaHoraFin;

	@Column(name = "observaciones", length = 1000)
	@NotNull
	private String observaciones;

	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "id_solicitud")
	private Long idSolicitud;

	@Override
	public String getIdCorrelacion() {
		return String.format("[%s][%s]", getClienteIdentificacion(), getNumeroSolicitud());
	}

	@Builder
	public PlanillaRutaControl(Long id, int version, LocalDateTime FechaCreacion, String createdBy,
			LocalDateTime FechaModificacion, String modifiedBy, Long idArchivo, EstadoRegistroType estado,
			int numeroLinea, @NotEmpty String placaProgramada, @NotNull LocalDate fechaProgramada,
			@NotEmpty String clienteIdentificacion, @NotNull String numeroSolicitud,
			@NotEmpty String estadoCodigoAlterno, @NotNull String causalNovedadCodigoAlterno,
			LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, @NotNull String observaciones, Long idCliente,
			Long idSolicitud) {
		super(id, version, FechaCreacion, createdBy, FechaModificacion, modifiedBy, idArchivo, estado, numeroLinea);
		this.placaProgramada = placaProgramada;
		this.fechaProgramada = fechaProgramada;
		this.clienteIdentificacion = clienteIdentificacion;
		this.numeroSolicitud = numeroSolicitud;
		this.estadoCodigoAlterno = estadoCodigoAlterno;
		this.causalNovedadCodigoAlterno = causalNovedadCodigoAlterno;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.observaciones = observaciones;
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
			return getFechaProgramada();

		case ESTADO_CODIGO_ALTERNO:
			return getEstadoCodigoAlterno();
		case CAUSAL_NOVEDAD_CODIGO_ALTERNO:
			return getCausalNovedadCodigoAlterno();
		case FECHA_HORA_INICIO:
			return getFechaHoraInicio();
		case FECHA_HORA_FIN:
			return getFechaHoraFin();
		case OBSERVACION:
			return getObservaciones();
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
