package tju.litao.cfaces;

/**
 * Created by litao on 2016/5/9.
 */
public class Contact{
    private String name;
    private String phoneNumber;
    private String comment;
    private String denote;
    public Contact(String name, String phoneNumber, String comment, String denote){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.denote = denote;
    }
    public boolean storeInto(String database){
        //TODO: storeInto database;
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDenote() {
        return denote;
    }

    public void setDenote(String denote) {
        this.denote = denote;
    }
}
