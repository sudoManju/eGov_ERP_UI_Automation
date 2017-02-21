package builders;


import entities.Allottee;

public class AllotteeBuilder {
    Allottee allottee =new Allottee ();

    public AllotteeBuilder()
    {
        allottee.setName("sam");
        allottee.setAddress("123-skylane");
        allottee.setContact_no(12345);

    }

    public Allottee build(){
        return allottee;
    }
}