package vn.viettuts.qlsv.entity;
 
import java.util.List;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "QLTC")
@XmlAccessorType(XmlAccessType.FIELD)
public class QLTCXML {
     
    private List<QLTC> qltc;
 
    public List<QLTC> getQLTC() {
        return qltc;
    }
 
    public void setQLTC(List<QLTC> qltc) {
        this.qltc = qltc;
    }
}