package Project543;

public enum Languages {
    ASSEMBLER, ADA, C, CPP, CSHARP, COBOL, FORTRAN, HTML, JAVA, JAVASCRIPT, VBSCRIPT, VISUALBASIC;

    @Override
    public String toString() {
        //return super.toString();

        String progLang = "";

        switch(this){
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
                progLang = "ERROR";
                break;
        }

        return progLang;
    }

    public int[] locPerFP(){
        int[] locPerFP;

        switch(this){
            case VISUALBASIC:
                break;
            case JAVASCRIPT:
                break;
            case VBSCRIPT:
                break;
            case FORTRAN:
                break;
            case CSHARP:
                break;
            case C:
                break;
            case COBOL:
                break;
            case JAVA:
                break;
            case HTML:
                break;
            case CPP:
                break;
            case ADA:
                break;
            case ASSEMBLER:
                break;
            default:
                break;
        }

        System.err.println("ERROR: PROGRAMMING LANGUAGE NOT FOUND");
        locPerFP = new int[]{-1,-1,-1};
        return locPerFP;

    }
}
