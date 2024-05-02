package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.ArrayList;
import javax.swing.JTable;
import vn.viettuts.qlsv.dao.QLTCDao;
import vn.viettuts.qlsv.entity.QLTC;
import vn.viettuts.qlsv.view.QLTCView;

public class QLTCController {

    private QLTCDao qltcDao;
    private QLTCView qltcView;
   

    public QLTCController(QLTCView view) {
        this.qltcView = view;
        qltcDao = new QLTCDao();

        view.addAddQLTCListener(new AddQLTCListener());
        view.addEditQLTCListener(new EditQLTCListener());
        view.addDeleteQLTCListener(new DeleteQLTCListener());
        view.addClearListener(new ClearQLTCListener());

        view.addListQLTCSelectionListener(new ListQLTCSelectionListener());
        view.addSearchListener(new SearchListener());
        view.addCancelSearchQLTCListener(new CancelSearchQLTCListener());
    }

    public void showQLTCView() {
        List<QLTC> qltcList = qltcDao.getListQLTCs();
        qltcView.setVisible(true);
        qltcView.showListQLTC(qltcList);
        qltcView.updateLabels(qltcList);

    }

    class AddQLTCListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            qltcView.getAddBtn().setEnabled(true);
            QLTC qltc = qltcView.getQLTCInfo();
            if (qltc != null) {
                qltcDao.add(qltc);

                qltcView.showQLTC(qltc);
                qltcView.showListQLTC(qltcDao.getListQLTCs());
                qltcView.showMessage("Thêm thành công!");
            }
        }
    }

    class EditQLTCListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            QLTC qltc = qltcView.getQLTCInfo();
            if (qltc != null) {
                qltcDao.edit(qltc);
                qltcView.showQLTC(qltc);
                qltcView.showListQLTC(qltcDao.getListQLTCs());
                qltcView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class DeleteQLTCListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            QLTC qltc = qltcView.getQLTCInfo();
            if (qltc != null) {
                qltcDao.delete(qltc);
                qltcView.clearQLTCInfo();
                qltcView.showListQLTC(qltcDao.getListQLTCs());
                qltcView.showMessage("Xóa thành công!");
            }
        }
    }

    class ClearQLTCListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            qltcView.clearQLTCInfo();
        }
    }

    class SortIDListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            qltcDao.sortQLTCByID();
            qltcView.showListQLTC(qltcDao.getListQLTCs());
        }
    }

    class SortDateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            qltcDao.sortQLTCByDate();
            qltcView.showListQLTC(qltcDao.getListQLTCs());
        }
    }

    class ListQLTCSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {

            qltcView.fillQLTCFromSelectedRow();

        }

    }

    class SearchListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            List<QLTC> temp = new ArrayList<>();
            int check = qltcView.getChoseSelectSearch();
            String search = qltcView.validateSearch();
            if (check == 1) {
                temp = qltcDao.searchDate(search);
            } else if (check == 2) {
                temp = qltcDao.searchDescription(search);
            } else if (check == 3) {
                temp = qltcDao.searchMonth(search);
            } else if (check == 4) {
                temp = qltcDao.searchWeek(search);
            }

            qltcView.getBtnSearch().setEnabled(true);
            qltcView.getFieldSearch().setEnabled(true);
            if (!temp.isEmpty()) {
                
                qltcView.showListQLTC(temp);
                qltcView.showExpenseIncomePieChart(temp);
                qltcView.updateLabels(temp);
           
                
            } else {
                qltcView.showMessage("Không tìm thấy kết quả!");
            }

            temp.clear();
            
        }

    }

    class CancelSearchQLTCListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            qltcView.showExpenseIncomePieChart(qltcDao.getListQLTCs());
            qltcView.showListQLTC(qltcDao.getListQLTCs());
            qltcView.cancelSearch();

        }
    }

}
