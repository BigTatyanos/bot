package appBot;

public class Hero {
    private String testName;
    private String name;
    private String description;
    
    public Hero(String testName, String name, String description) {
        this.testName = testName;
        this.name = name;
        this.description = description;

    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTestName(){
        return this.testName;
    }

    public void setTestName(String testName){
        this.testName = testName;
    }

    public String getDiscription(){
        return this.description;
    }

    public void setDiscription(String discription){
        this.description = discription;
    }
}
