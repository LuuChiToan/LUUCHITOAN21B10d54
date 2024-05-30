package vn.viettuts.qlsv.dao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import vn.viettuts.qlsv.entity.QLTC;
import vn.viettuts.qlsv.entity.QLTCXML;
import vn.viettuts.qlsv.utils.FileUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * StudentDao class
 * 
 * @author viettuts.vn
 */
public class QLTCDao {
    private static final String QLTC_FILE_NAME = "qltc.xml";
    
    private List<QLTC> listQLTCs;
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
    int id;
    if (listQLTCs != null && !listQLTCs.isEmpty()) {
        // Find the highest existing ID
        int maxId = listQLTCs.stream()
            .mapToInt(QLTC::getId)
            .max()
            .orElse(0); // Default to 0 if the list is empty

        // Increment the highest ID to get a new unique ID
        id = maxId + 1;
    } else {
        // If the list is empty, start with ID 1
        id = 1;
    }

    qltc.setId(id);
    listQLTCs.add(qltc);
    writeListQLTCs(listQLTCs);
}

public void edit(QLTC qltc) {
    int size = listQLTCs.size();
    for (int i = 0; i < size; i++) {
        if (listQLTCs.get(i).getId() == qltc.getId()) {
            listQLTCs.get(i).setDate(qltc.getDate());
            listQLTCs.get(i).setDescription(qltc.getDescription());
            listQLTCs.get(i).setAmount((long) qltc.getAmount());
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
        if (listQLTCs.get(i).getId() == qltc.getId()) {
            qltc = listQLTCs.get(i);
            isFound = true;
            break;
        }
    }
    if (isFound) {
        listQLTCs.remove(qltc);
        // Reassign IDs to maintain sequential order
        for (int i = 0; i < listQLTCs.size(); i++) {
            listQLTCs.get(i).setId(i + 1);
        }
        writeListQLTCs(listQLTCs);
        return true;
    }
    return false;
}
public void sortQLTCByDate(List<QLTC> listQLTCs) {
    Collections.sort(listQLTCs, new Comparator<QLTC>() {
        @Override
        public int compare(QLTC q1, QLTC q2) {
            Date date1 = q1.getDate();
            Date date2 = q2.getDate();

            if (date1 == null && date2 == null) {
                return 0;
            }
            if (date1 == null) {
                return 1;
            }
            if (date2 == null) {
                return -1;
            }
            return date1.compareTo(date2);
        }
    });

    // Update IDs after sorting
//    for (int i = 0; i < listQLTCs.size(); i++) {
//        listQLTCs.get(i).setId(i + 1);
//    }

    // Print sorted dates
    for (QLTC dateChooser : listQLTCs) {
        Date date = dateChooser.getDate();
//        if (date != null) {
//            System.out.println(date.toString());
//        }
    }
}
public void sortQLTCByAmount(List<QLTC> listQLTCs) {
    Collections.sort(listQLTCs, new Comparator<QLTC>() {
        @Override
        public int compare(QLTC q1, QLTC q2) {
            long amount1 = q1.getAmount();
            long amount2 = q2.getAmount();

            return Long.compare(amount1, amount2);
        }
    });

    // Update IDs after sorting
//    for (int i = 0; i < listQLTCs.size(); i++) {
//        listQLTCs.get(i).setId(i + 1);
//    }

    // Print sorted amounts
//    for (QLTC q : listQLTCs) {
//        System.out.println("ID: " + q.getId() + ", Amount: " + q.getAmount());
//    }
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
    public List<QLTC> searchByAmountRange(String amountRange) {
    List<QLTC> filteredTransactions = new ArrayList<>();
    this.listQLTCs = readListQLTCs(); // Assuming this method reads the transactions from somewhere

    // Parse the input amount range (e.g., "100 to 500")
    String[] amounts = amountRange.split(" to ");
    if (amounts.length != 2) {
        // Handle invalid input (e.g., not in the format "amount to amount")
        return filteredTransactions;
    }

    try {
        double minAmount = Double.parseDouble(amounts[0]);
        double maxAmount = Double.parseDouble(amounts[1]);

        for (QLTC transaction : this.listQLTCs) {
            double transactionAmount = transaction.getAmount();
            if (transactionAmount >= minAmount && transactionAmount <= maxAmount) {
                filteredTransactions.add(transaction);
            }
        }
    } catch (NumberFormatException e) {
        // Handle invalid numeric input
        e.printStackTrace();
    }

    return filteredTransactions;
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
