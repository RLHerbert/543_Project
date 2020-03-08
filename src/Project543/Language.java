package Project543;

public enum Language {
    NONE, ASSEMBLER, ADA, C, CPP, CSHARP, COBOL, FORTRAN, HTML, JAVA, JAVASCRIPT, VBSCRIPT, VISUALBASIC;

    //Member Methods
    //Constructor(s)
    //TODO: Implement public Language(SAVEDFILE){}

    //Misc. Member Methods
    @Override
    public String toString() {
        //return super.toString();

        String progLang = "ERROR";

        switch(this){
            case NONE:
                progLang = "None";
                break;
            case ASSEMBLER:
                progLang = "Assembler";
                break;
            case ADA:
                progLang = "Ada 95";
                break;
            case C:
                progLang = "C";
                break;
            case CPP:
                progLang = "C++";
                break;
            case HTML:
                progLang = "HTML";
                break;
            case JAVA:
                progLang = "Java";
                break;
            case COBOL:
                progLang = "COBOL";
                break;
            case CSHARP:
                progLang = "C#";
                break;
            case FORTRAN:
                progLang = "FORTRAN";
                break;
            case VBSCRIPT:
                progLang = "VBScript";
                break;
            case JAVASCRIPT:
                progLang = "JavaScript";
                break;
            case VISUALBASIC:
                progLang = "Visual Basic";
                break;
            default:
                //progLang = "ERROR";
                System.err.println("ERROR: PROGRAMMING LANGUAGE NOT FOUND");
                break;
        }

        System.out.println("Languages.toString returned: " + progLang);
        return progLang;
    }

    public int locPerFP(){
        //Returns average lines of code per FP for a given language

        //int[] locPerFP = new int[] {-1,-1,-1};
        int locPerFP = -1;
        switch(this) {
            case NONE:
                locPerFP = 0;
                break;
            case VISUALBASIC:
                locPerFP = 50;
                break;
            case JAVASCRIPT:
                locPerFP = 54;
                break;
            case VBSCRIPT:
                locPerFP = 38;
                break;
            case FORTRAN:
                locPerFP = 90;
                break;
            case CSHARP:
                locPerFP = 58;
                break;
            case C:
                locPerFP = 148;
                break;
            case COBOL:
                locPerFP = 80;
                break;
            case JAVA:
                locPerFP = 55;
                break;
            case HTML:
                locPerFP = 43;
                break;
            case CPP:
                locPerFP = 59;
                break;
            case ADA:
                locPerFP = 154;
                break;
            case ASSEMBLER:
                locPerFP = 209;
                break;
            default:
                System.err.println("ERROR: PROGRAMMING LANGUAGE NOT FOUND");
                //locPerFP = new int[]{-1,-1,-1};
                break;

        }
        System.out.println("LOC per FP for " + this + ": " + locPerFP);
        return locPerFP;
    }

    public boolean isNone(){
        //Returns true if the selected languages is None
        if (this == NONE){
            return true;
        }

        return false;
    }
}
