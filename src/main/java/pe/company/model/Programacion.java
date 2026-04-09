package pe.company.model;

import jakarta.persistence.*;

@Entity
@Table(name = "programacion")
public class Programacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String frecuencia;
    private Integer hora;
    private Integer minuto;
    private Boolean activo;

    @OneToOne
    @JoinColumn(name = "reporte_id")
    private Reporte reporte;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public Integer getHora() { return hora; }
    public void setHora(Integer hora) { this.hora = hora; }

    public Integer getMinuto() { return minuto; }
    public void setMinuto(Integer minuto) { this.minuto = minuto; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public Reporte getReporte() { return reporte; }
    public void setReporte(Reporte reporte) { this.reporte = reporte; }
}