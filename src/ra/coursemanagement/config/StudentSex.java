package ra.coursemanagement.config;

public enum StudentSex {
    MALE(true, "Nam"),
    FEMALE(false, "Nữ");
    private final boolean dbValue;
    private final String displayName;

    StudentSex(boolean dbValue, String displayName) {
        this.dbValue = dbValue;
        this.displayName = displayName;
    }

    public boolean isDbValue() {
        return dbValue;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static StudentSex sexFromDB(boolean dbValue) {
        return dbValue ? MALE : FEMALE;
    }
}
