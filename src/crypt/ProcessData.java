package crypt;

/* Las instancias de esta clase se utilizan para compartir los datos proporcionados por el usuario a traves
 * de la interfaz grafica*/
public class ProcessData {

    private final String originFilePath;
    private final String destinationPath;
    private final String password;
    private final String outputFileName;

    public ProcessData(String pathOrigen, String pathDirectorioDestino, String password, String nombreSalida) {
        this.originFilePath = pathOrigen;
        this.destinationPath = pathDirectorioDestino;
        this.password = password;
        this.outputFileName = nombreSalida;
    }

    public String getOriginFilePath() {
        return originFilePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }
    public String getPassword() {
        return password;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

}