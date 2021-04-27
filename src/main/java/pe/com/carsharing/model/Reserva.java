package pe.com.carsharing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name ="direccion_origen", length = 100, nullable = false)
	private String dirOrigen;
	@Column(name ="direccion_destino", length = 100, nullable = false)
	private String dirDestino;
	@Column(name ="costo", length = 7, precision = 2, nullable = false)
	private float costo;
	@Column(name = "visa", columnDefinition = "bit",nullable = false)
	private boolean visa;
	@Column(name = "mastercard", columnDefinition = "bit",nullable = false)
	private boolean mastercard;
	@Column(name = "uso", columnDefinition = "bit",nullable = false)
	private boolean uso;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)	
	@Column(name = "fecha")
	private Date fecha;
	//2020-04-03T05:31:48 <- ejemplo de hora el sql solo leera 05:31:48
	@Temporal(TemporalType.TIME)
	@Column(name = "hora")
	private Date hora;
	@ManyToOne
	@JoinColumn(name = "auto_id", nullable = false)
	private Auto auto;
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDirOrigen() {
		return dirOrigen;
	}
	public void setDirOrigen(String dirOrigen) {
		this.dirOrigen = dirOrigen;
	}
	public String getDirDestino() {
		return dirDestino;
	}
	public void setDirDestino(String dirDestino) {
		this.dirDestino = dirDestino;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public boolean isUso() {
		return uso;
	}
	public void setUso(boolean uso) {
		this.uso = uso;
	}
	public Auto getAuto() {
		return auto;
	}
	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isVisa() {
		return visa;
	}
	public void setVisa(boolean visa) {
		this.visa = visa;
	}
	public boolean isMastercard() {
		return mastercard;
	}
	public void setMastercard(boolean mastercard) {
		this.mastercard = mastercard;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	
}
