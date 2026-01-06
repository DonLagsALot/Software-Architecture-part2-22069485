package util;

public final class Result<T> {
    private final boolean ok;
    private final T value;
    private final String error;

    private Result(boolean ok, T value, String error) {
        this.ok = ok; this.value = value; this.error = error;
    }
    public static <T> Result<T> ok(T value) { return new Result<>(true, value, null); }
    public static <T> Result<T> fail(String error) { return new Result<>(false, null, error); }

    public boolean isOk() { return ok; }
    public T value() { return value; }
    public String error() { return error; }
}
