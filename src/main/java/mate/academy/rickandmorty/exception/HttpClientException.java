package mate.academy.rickandmorty.exception;

public class HttpClientException extends RuntimeException {
    public HttpClientException(String message, Exception ex) {
        super(message, ex);
    }
}
