package vn.viettuts.qlsv.dao;
 import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 import javax.swing.table.AbstractTableModel;
import vn.viettuts.qlsv.entity.QLTC;
import vn.viettuts.qlsv.entity.QLTCXML;
import vn.viettuts.qlsv.utils.FileUtils;
 import java.util.Locale;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;
/**
 * StudentDao class
 * 
 * @author viettuts.vn
 */
public class QLTCDao {
    private static final String QLTC_FILE_NAME = "qltc.xml";
    
    private List<QLTC> listQLTCs;
   private final String[] columnNames = {"ID","Date","Description","Amount","Type"};
    public QLTCDao() {
        listQLTCs= new ArrayList<>();
        this.listQLTCs = readListQLTCs();
        if (listQLTCs == null) {
            listQLTCs = new ArrayList<QLTC>();
        }
    }
 
    public void writeListQLTCs(List<QLTC> qltcs) {
        QLTCXML qltcXML = new QLTCXML();
        qltcXML.setQLTC(qltcs);
        FileUtils.writeXMLtoFile(QLTC_FILE_NAME, qltcXML);
    }
 
   
    public List<QLTC> readListQLTCs() {
        List<QLTC> list = new ArrayList<QLTC>();
        QLTCXML qltcXML = (QLTCXML) FileUtils.readXMLFile(QLTC_FILE_NAME, QLTCXML.class);
        if (qltcXML != null) {
            list = qltcXML.getQLTC();
        }
        return list;
    }
    public void add(QLTC qltc) {
        int id = 1;
        if (listQLTCs != null && listQLTCs.size() > 0) {
            id = listQLTCs.size() + 1;
        }
        qltc.setId(id);
        listQLTCs.add(qltc );
        writeListQLTCs(listQLTCs);
    }
     public void edit(QLTC qltc) {
        int size = listQLTCs.size();
        for (int i = 0; i < size; i++) {
            if (listQLTCs.get(i).getId() == qltc.getId()) {
                listQLTCs.get(i).setDate(qltc.getDate());
                listQLTCs.get(i).setDescription(qltc.getDescription());
                listQLTCs.get(i).setAmount((int) qltc.getAmount());
                listQLTCs.get(i).setType(qltc.getType());
                writeListQLTCs(listQLTCs);
                break;
            }
        }
     }
     public boolean delete(QLTC qltc) {
        boolean isFound = false;
        int size = listQLTCs.size();
        for (int i = 0; i < size; i++) {
            if (listQLTCs.get(i).getId() ==qltc.getId()) {
                qltc = listQLTCs.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listQLTCs.remove(qltc);
            writeListQLTCs(listQLTCs);
            return true;
        }
        return false;
    }
    
      public void sortQLTCByID() {
        Collections.sort(listQLTCs, new Comparator<QLTC>() {
            public int compare(QLTC qltc1, QLTC qltc2) {
                if (qltc1.getId() > qltc2.getId()) {
                    return 1;
                }
                return -1;
            }
        });
    }
     public void sortQLTCByDate(){
         
        Collections.sort(listQLTCs, new Comparator<QLTC>() {
        @Override
        public int compare(QLTC date1, QLTC date2) {
            Date d1 = date1.getDate();
            Date d2 = date2.getDate();

            if (d1 == null && d2 == null) {
                return 0;
            }
            if (d1 == null) {
                return 1;
            }
            if (d2 == null) {
                return -1;
            }
            return d1.compareTo(d2);
        }
    });

    // Đặt lại ID cho mỗi QLTC trong danh sách sau khi đã sắp xếp
    for (int i = 0; i < listQLTCs.size(); i++) {
        listQLTCs.get(i).setId(i + 1);
    }

    // In ra các ngày sau khi đã sắp xếp
    for (QLTC dateChooser : listQLTCs) {
        Date date = dateChooser.getDate();
        if (date != null) {
            System.out.println(date.toString());
        }
    }
    
     }
      public List<QLTC> searchDescription(String search){
        List<QLTC>temp = new ArrayList<QLTC>();
        for(QLTC pb : listQLTCs){
            if(pb.getDescription().toLowerCase().contains(search.toLowerCase())){
                temp.add(pb);
            }
        }
        return temp;
}


    public List<QLTC> searchDate(String s) {
    List<QLTC> DhTmp = new ArrayList<QLTC>();
    this.listQLTCs = readListQLTCs();
    List<QLTC> ldh = this.listQLTCs;
    SimpleDateFormat cvt = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat cvtDay = new SimpleDateFormat("dd");
    SimpleDateFormat cvtMonth = new SimpleDateFormat("MM");
    for(QLTC dh : ldh) {
        String day = cvtDay.format(dh.getDate());
        String month = cvtMonth.format(dh.getDate());
        String date = cvt.format(dh.getDate());
        if(date.equals(s)) {
            DhTmp.add(dh);
        }
    }
    return DhTmp;
}
      
     public List<QLTC> searchMonth(String s){
    List<QLTC> DhTmp = new ArrayList<QLTC>();
    this.listQLTCs = readListQLTCs();
    List<QLTC> ldh = this.listQLTCs;
    SimpleDateFormat cvt = new SimpleDateFormat("MM");
    for(QLTC dh : ldh){
        String month = cvt.format(dh.getDate());
        if(month.equals(s)){
            DhTmp.add(dh);
        }
    }
    return DhTmp;
}

     public List<QLTC> searchWeek(String s) {
    List<QLTC> DhTmp = new ArrayList<QLTC>();
    this.listQLTCs = readListQLTCs();
    List<QLTC> ldh = this.listQLTCs;
    SimpleDateFormat cvt = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat weekFormat = new SimpleDateFormat("w");
    Calendar cal = Calendar.getInstance();
    try {
        cal.setTime(cvt.parse(s));
    } catch (ParseException e) {
        e.printStackTrace();
    }
    int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
    for(QLTC dh : ldh) {
        String week = weekFormat.format(dh.getDate());
        if(Integer.parseInt(week) == weekOfYear) {
            DhTmp.add(dh);
        }
    }
    return DhTmp;
}

      
      
    

      public List<QLTC> getListQLTCs() {
        return listQLTCs;
    }

    public void setListGymers(List<QLTC> listQLTCs) {
        this.listQLTCs = listQLTCs;
    }
}
