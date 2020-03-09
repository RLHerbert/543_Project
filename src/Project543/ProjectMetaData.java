package Project543;

public class ProjectMetaData {
    //Member Variables
    public static final String FILE_EXT = ".ms";
    protected String fileName, projectName, productName, creatorName, projectComments;
    protected Language projectLanguage;

    //Member Methods
    //Constructor(s)
    public ProjectMetaData(){
        this.projectName = "";
        this.productName = "";
        this.creatorName = "";
        this.projectComments = "";
        this.setFileName();

        //currentLanguage not set by default;
    }

    //TODO: implement: public ProjectMetaData(SAVEDFILE){}

    //Getters
    public String getFileName() {
        return this.fileName;
    }

    public String getProjectName(){
        return this.projectName;
    }

    public String getProductName(){
        return this.productName;
    }

    public String getCreatorName(){
        return this.creatorName;
    }

    public String getProjectComments(){
        return this.projectComments;
    }

    public Language getProjectLanguage(){
        return this.projectLanguage;
    }

    public int getLanguageLOC(){
        if (projectLanguage != null) {
            return this.projectLanguage.locPerFP();
        }

        System.err.println("ERROR: LANGUAGE_NOT_SET");
        return 0;
    }

    //Setters
    public void setFileName(){
        this.fileName = projectName + FILE_EXT;
    }

    public void setFileName(String fileName){
        this.fileName = fileName + FILE_EXT;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setProjectComments(String projectComments){
        this.projectComments = projectComments;
    }

    public void setProjectLanguage(Language language){
        this.projectLanguage = language;
    }

    //Misc. Member Methods
    //TODO: Fix
    @Override
    public String toString() {
        //To allow simple saving
        String outString =
                        "PROJECT_NAME:" + projectName + "\n"        +
                        "PRODUCT_NAME:" + projectName + "\n"        +
                        "CREATOR_NAME:" + projectName + "\n"        +
                        "PROJECT_COMMENTS:" + projectName + "\n"    +
                        "PROJECT_LANGUAGE:" + projectName; //CALLER ALWAYS ADDS "\n"
        return outString;
    }
}
