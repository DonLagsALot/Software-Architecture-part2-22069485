package util;

public final class Validators {
    private Validators() {}

    public static Result<String> required(String s, String field) {
        if (s == null || s.trim().isEmpty()) return Result.fail(field + " is required");
        return Result.ok(s.trim());
    }
}
