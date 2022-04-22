public enum Status{
    ACTIVE("Active"),
    HYBERNATED("Hybernated"),
    STOPPED("Stopped"),
    TERMINATED("Terminated");
    
    private final String label;
    
    private Status(String label) {
        this.label = label;
    }
    
    public String getLabel(){
        return label;
    }

    // Mutating Constant -> Can't set enum.
    // public void setLabel(String label){
    //     this.label = label;
    // }
}