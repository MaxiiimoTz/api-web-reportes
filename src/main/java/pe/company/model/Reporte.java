package pe.company.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo;
    private String nombre;
    private String estado;
    private String tipo;
    private String fuente;

    private String medio;

    @Column(name = "tipo_envio")
    private String tipoEnvio;

    private String frecuencia;

    private String dias;

    @Column(name = "hora_envio")
    private String horaEnvio;

    // ======================
    // GETTERS & SETTERS
    // ======================

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getFuente() { return fuente; }
    public void setFuente(String fuente) { this.fuente = fuente; }

    public String getMedio() { return medio; }
    public void setMedio(String medio) { this.medio = medio; }

    public String getTipoEnvio() { return tipoEnvio; }
    public void setTipoEnvio(String tipoEnvio) { this.tipoEnvio = tipoEnvio; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public String getDias() { return dias; }
    public void setDias(String dias) { this.dias = dias; }

    public String getHoraEnvio() { return horaEnvio; }
    public void setHoraEnvio(String horaEnvio) { this.horaEnvio = horaEnvio; }
}