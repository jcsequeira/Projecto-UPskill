package exceptions;

/**
 * A custom runtime exception for service-related errors.
 */
public class ServiceException extends RuntimeException {

    /**
     * Constructs a new {@code ServiceException} with no specified detail message.
     */
    public ServiceException() {
        super();
    }

    /**
     * Constructs a new {@code ServiceException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ServiceException} with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code ServiceException} with the specified cause and a detail message of
     * (cause==null ? null : cause.toString()) (which typically contains the class and detail
     * message of cause).
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
