package restApi3;

public enum ApplicationUserPermissions {
    STUDENT_READ("student:read"), STUDENT_WRITE("student:write");

    private final String permission;

    private ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
