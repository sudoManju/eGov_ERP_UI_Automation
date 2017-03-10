package entities.Login;


public class User
{
    private String date_of_appointment;

    private String guardian_relation;

    private String device_type;

    private String guardian;

    private String type;

    private String password;

    private String confirm_password;

    private String aadhaar_number;

    private String name;

    private String gender;

    private String alt_contact_number;

    private String mobile_no;

    private String status;

    private String device_id;

    private String code;

    private String account_locked;

    private String pan;

    private String os_version;

    private String user_name;

    private String email;

    private String date_of_retirement;

    private String dob;

    private String new_password;

    private String salutation;

    private String pwd_expiry_date;

    public String getDate_of_appointment ()
    {
        return date_of_appointment;
    }

    public void setDate_of_appointment (String date_of_appointment)
    {
        this.date_of_appointment = date_of_appointment;
    }

    public String getGuardian_relation ()
    {
        return guardian_relation;
    }

    public void setGuardian_relation (String guardian_relation)
    {
        this.guardian_relation = guardian_relation;
    }

    public String getDevice_type ()
    {
        return device_type;
    }

    public void setDevice_type (String device_type)
    {
        this.device_type = device_type;
    }

    public String getGuardian ()
    {
        return guardian;
    }

    public void setGuardian (String guardian)
    {
        this.guardian = guardian;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getConfirm_password ()
    {
        return confirm_password;
    }

    public void setConfirm_password (String confirm_password)
    {
        this.confirm_password = confirm_password;
    }

    public String getAadhaar_number ()
    {
        return aadhaar_number;
    }

    public void setAadhaar_number (String aadhaar_number)
    {
        this.aadhaar_number = aadhaar_number;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getAlt_contact_number ()
    {
        return alt_contact_number;
    }

    public void setAlt_contact_number (String alt_contact_number)
    {
        this.alt_contact_number = alt_contact_number;
    }

    public String getMobile_no ()
    {
        return mobile_no;
    }

    public void setMobile_no (String mobile_no)
    {
        this.mobile_no = mobile_no;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDevice_id ()
    {
        return device_id;
    }

    public void setDevice_id (String device_id)
    {
        this.device_id = device_id;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getAccount_locked ()
    {
        return account_locked;
    }

    public void setAccount_locked (String account_locked)
    {
        this.account_locked = account_locked;
    }

    public String getPan ()
    {
        return pan;
    }

    public void setPan (String pan)
    {
        this.pan = pan;
    }

    public String getOs_version ()
    {
        return os_version;
    }

    public void setOs_version (String os_version)
    {
        this.os_version = os_version;
    }

    public String getUser_name ()
    {
        return user_name;
    }

    public void setUser_name (String user_name)
    {
        this.user_name = user_name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getDate_of_retirement ()
    {
        return date_of_retirement;
    }

    public void setDate_of_retirement (String date_of_retirement)
    {
        this.date_of_retirement = date_of_retirement;
    }

    public String getDob ()
    {
        return dob;
    }

    public void setDob (String dob)
    {
        this.dob = dob;
    }

    public String getNew_password ()
    {
        return new_password;
    }

    public void setNew_password (String new_password)
    {
        this.new_password = new_password;
    }

    public String getSalutation ()
    {
        return salutation;
    }

    public void setSalutation (String salutation)
    {
        this.salutation = salutation;
    }

    public String getPwd_expiry_date ()
    {
        return pwd_expiry_date;
    }

    public void setPwd_expiry_date (String pwd_expiry_date)
    {
        this.pwd_expiry_date = pwd_expiry_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [date_of_appointment = "+date_of_appointment+", guardian_relation = "+guardian_relation+", device_type = "+device_type+", guardian = "+guardian+", type = "+type+", password = "+password+", confirm_password = "+confirm_password+", aadhaar_number = "+aadhaar_number+", name = "+name+", gender = "+gender+", alt_contact_number = "+alt_contact_number+", mobile_no = "+mobile_no+", status = "+status+", device_id = "+device_id+", code = "+code+", account_locked = "+account_locked+", pan = "+pan+", os_version = "+os_version+", user_name = "+user_name+", email = "+email+", date_of_retirement = "+date_of_retirement+", dob = "+dob+", new_password = "+new_password+", salutation = "+salutation+", pwd_expiry_date = "+pwd_expiry_date+"]";
    }
}