package pe.com.carsharing.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombres", length = 50)
	private String nombres;
	@Column(name = "apellido_paterno", length = 35)
	private String apellidoPaterno;
	@Column(name = "apellido_materno", length = 35)
	private String apellidoMaterno;
	@Column(name = "dni",length = 8)
	private String dni;
	@Column(name = "celular",length = 9)
	private String celular;
    @Basic( fetch = FetchType.LAZY)
	@Column(name = "licencia")
	@Lob()
	private byte[] licencia;
	@Column(name = "correo",length = 200)
	private String correo;
	@Column(name = "password", length = 50)
	private String password;
	@Column(name = "es_adm", columnDefinition = "bit")
	private boolean esAdm;
	@Column(name = "enable", columnDefinition = "bit")
	private boolean enable;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)	
	@Column(name = "fecha_nacimiento")
	private Date fechaNac;
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Reserva> reservas;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEsAdm() {
		return esAdm;
	}
	public void setEsAdm(boolean esAdm) {
		this.esAdm = esAdm;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public byte[] getLicencia() {
		return licencia;
	}
	public void setLicencia(byte[] licencia) {
		this.licencia = licencia;
	}
}
