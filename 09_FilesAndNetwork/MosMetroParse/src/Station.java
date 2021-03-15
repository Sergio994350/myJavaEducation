class Station {
    public String name;
    public String line;

    public Station(String name,String line){
        this.line=line;
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public String getLine(){
        return this.line;
    }
    public String toString()
    {
        return name;
    }
}