package pe.company.model;

import jakarta.persistence.*;

@Entity
@Table(name = "destinatarios")
public class Destinatario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    @Enumerated(EnumType.STRING)
    private TipoDestinatario tipo;

    @ManyToOne
    @JoinColumn(name = "reporte_id")
    private Reporte reporte;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public TipoDestinatario getTipo() { return tipo; }
    public void setTipo(TipoDestinatario tipo) { this.tipo = tipo; }

    public Reporte getReporte() { return reporte; }
    public void setReporte(Reporte reporte) { this.reporte = reporte; }
}