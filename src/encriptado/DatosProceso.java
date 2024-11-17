package encriptado;

/* Las instancias de esta clase se utilizan para compartir los datos proporcionados por el usuario a traves
 * de la interfaz grafica*/
public class DatosProceso {

    private final String pathOrigen;
    private final String pathDirectorioDestino;
    private final String nombreSalida;

    public DatosProceso(String pathOrigen, String pathDirectorioDestino, String nombreSalida) {
        this.pathOrigen = pathOrigen;
        this.pathDirectorioDestino = pathDirectorioDestino;
        this.nombreSalida = nombreSalida;
    }

    public String getPathOrigen() {
        return pathOrigen;
    }

    public String getPathDirectorioDestino() {
        return pathDirectorioDestino;
    }

    public String getNombreSalida() {
        return nombreSalida;
    }

}