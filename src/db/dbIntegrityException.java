package db;

public class dbIntegrityException extends RuntimeException {
    public dbIntegrityException(String message) {
        super(message);
    }
}
