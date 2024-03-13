package pages;

public enum TopMenuItem {
    SEARCH("Search"), LET_THE_CAR_WORK("Let the car work"),
    TERMS_OF_USE("Terms of use"), LOGIN("Log in"),
    SIGN_UP("Sign up"), LOGOUT("Logout"),
    DELETE_ACCOUNT("Delete account");
    private String label;

    TopMenuItem(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}