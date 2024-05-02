package vn.viettuts.qlsv.entity;
 import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "qltc")
@XmlAccessorType(XmlAccessType.FIELD)
public class QLTC implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private Date date;
    private String description;
    private long amount;
    private String type; // The type of the entry (expense or income).

    public QLTC() {
    }
    
    
    
    public QLTC(int id, Date date, String description, long amount, String type)
    {
        this.id=id;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }
    public int getId(){return id;}
    public Date getDate(){ return date;}
    public String getDescription(){ return description;}
    public long getAmount(){ return amount;}
    public String getType(){ return type;}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

   

    
}
   
    