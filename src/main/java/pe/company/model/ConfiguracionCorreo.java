package pe.company.model;

import jakarta.persistence.*;

@Entity
@Table(name = "configuracion_correo")
public class ConfiguracionCorreo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String asunto;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @OneToOne
    @JoinColumn(name = "reporte_id")
    private Reporte reporte;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Reporte getReporte() { return reporte; }
    public void setReporte(Reporte reporte) { this.reporte = reporte; }
}