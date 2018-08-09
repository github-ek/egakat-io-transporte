package com.egakat.io.transporte.domain.programaciones;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import com.egakat.integration.files.domain.Registro;
import com.egakat.integration.files.enums.EstadoRegistroType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "programaciones_toursolver")
@DynamicUpdate
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class ProgramacionRutaTourSolver extends Registro {

	public static final String PLACA = "PLACA";
	public static final String SECUENCIA = "SECUENCIA";
	public static final String CLIENTE_CODIGO = "CLIENTE_CODIGO";
	public static final String IDENTIFICADOR_ORDEN = "IDENTIFICADOR_ORDEN";
	public static final String FECHA_ESTIMADA = "FECHA_ESTIMADA";
	public static final String HORA_ESTIMADA = "HORA_ESTIMADA";

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

	@Column(name = "identificador_orden", length = 8)
	@Size(max = 8)
	@NotNull
	private String identificadorOrden;

	@Column(name = "fecha_estimada", nullable = false)
	@NotNull
	private LocalDate fechaEstimada;

	@Column(name = "hora_estimada", nullable = false)
	@NotNull
	private LocalTime horaEstimada;

	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "id_solicitud", nullable = false)
	private Long idSolicitud;

	@Override
	public String getIdCorrelacion() {
		return String.format("[%s][%d]", getClienteCodigo(), getIdentificadorOrden());
	}

	@Builder
	public ProgramacionRutaTourSolver(Long id, int version, LocalDateTime FechaCreacion, String createdBy,
			LocalDateTime FechaModificacion, String modifiedBy, Long idArchivo, EstadoRegistroType estado,
			int numeroLinea, @Size(max = 20) @NotNull String placa, int secuencia,
			@Size(max = 20) @NotNull String clienteCodigo, @Size(max = 8) @NotNull String identificadorOrden,
			@NotNull LocalDate fechaEstimada, @NotNull LocalTime horaEstimada, Long idCliente, Long idSolicitud) {
		super(id, version, FechaCreacion, createdBy, FechaModificacion, modifiedBy, idArchivo, estado, numeroLinea);
		this.placa = placa;
		this.secuencia = secuencia;
		this.clienteCodigo = clienteCodigo;
		this.identificadorOrden = identificadorOrden;
		this.fechaEstimada = fechaEstimada;
		this.horaEstimada = horaEstimada;
		this.idCliente = idCliente;
		this.idSolicitud = idSolicitud;
	}

	@Override
	public Object getObjectValueFromProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
			return getClienteCodigo();
		case IDENTIFICADOR_ORDEN:
			return getIdentificadorOrden();

		case PLACA:
			return getPlaca();
		case SECUENCIA:
			return getSecuencia();
		case FECHA_ESTIMADA:
			return getFechaEstimada();
		case HORA_ESTIMADA:
			return getHoraEstimada();
		default:
			return null;
		}
	}

	@Override
	public boolean isHomologableProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
		case IDENTIFICADOR_ORDEN:
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
		case IDENTIFICADOR_ORDEN:
			return getIdentificadorOrden();

		default:
			return null;
		}
	}

	@Override
	protected Object getObjectValueFromHomologousProperty(String property) {
		switch (property) {
		case CLIENTE_CODIGO:
			return getIdCliente();
		case IDENTIFICADOR_ORDEN:
			return getIdentificadorOrden();

		default:
			return null;
		}
	}
}
