class Hero {
    private String testName;
    private String name;
    private String description;

    Hero(String name, String description) {
        this.name = name;
        this.description = description;
    }

    String getName() {
        return this.name;
    }

    String getDescription() {
        return this.description;
    }

    void setDescription(String description) {
        this.description = description;
    }
}
