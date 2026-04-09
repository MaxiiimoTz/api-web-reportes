package pe.company.dto;

public class CumplimientoDTO {

    private Integer usuarioId;
    private String nombre;

    private int enviadosATiempo;
    private int enviadosTarde;
    private int noEnviados;

    private double porcentaje;

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEnviadosATiempo() { return enviadosATiempo; }
    public void setEnviadosATiempo(int enviadosATiempo) { this.enviadosATiempo = enviadosATiempo; }

    public int getEnviadosTarde() { return enviadosTarde; }
    public void setEnviadosTarde(int enviadosTarde) { this.enviadosTarde = enviadosTarde; }

    public int getNoEnviados() { return noEnviados; }
    public void setNoEnviados(int noEnviados) { this.noEnviados = noEnviados; }

    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
}