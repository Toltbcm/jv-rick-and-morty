package mate.academy.rickandmorty.exception;

public class DataMappingExceptoin extends RuntimeException {
    public DataMappingExceptoin(String message, Exception ex) {
        super(message, ex);
    }
}
