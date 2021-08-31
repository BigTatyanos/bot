class Hero {
    private String testName;
    private String name;
    private String description;

    Hero(String testName, String name, String description) {
        this.testName = testName;
        this.name = name;
        this.description = description;
    }

    String getName() {
        return this.name;
    }

    String getTestName() {
        return this.testName;
    }

    String getDescription() {
        return this.description;
    }

    void setDescription(String description) {
        this.description = description;
    }
}
