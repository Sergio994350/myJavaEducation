class ForJson {
    private String name;
    private String number;

    public ForJson(String name,String number){
        this.number=number;
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public String getNumber(){
        return this.number;
    }
}