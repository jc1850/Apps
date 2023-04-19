package de.storchp.fdroidbuildstatus.adapter;

public class ApiResponse<T> {

    private final T value;
    private final Status status;
    private final String errorMessage;

    private ApiResponse(T value, Status status, String errorMessage) {
        this.value = value;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static <T> ApiResponse<T> error(String errorMessage) {
        return new ApiResponse<>(null, Status.ERROR, errorMessage);
    }

    public static <T> ApiResponse<T> error(Status status, String errorMessage) {
        return new ApiResponse<>(null, status, errorMessage);
    }

    public static <T> ApiResponse<T> success(T value) {
        return new ApiResponse<>(value, Status.SUCCESS, null);
    }

    public T value() {
        return value;
    }

    public Status status() {
        return status;
    }

    public String errorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    public enum Status {
        NOT_FOUND,
        SUCCESS,
        ERROR
    }

}
