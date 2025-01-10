package mate.academy.rickandmorty.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Exception ex) {
        super(message, ex);
    }
}
