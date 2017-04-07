package builders.eGovEIS.CreateEmployee;


import entities.requests.eGovEIS.Employee.Hod;

public class HodBuilder {

    Hod hod = new Hod();

    public HodBuilder(){}

    public HodBuilder withDepartment(int department){
        hod.setDepartment(department);
        return this;
    }

    public Hod build(){
        return hod;
    }
}
