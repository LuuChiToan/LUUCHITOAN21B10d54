package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.ArrayList;
import vn.viettuts.qlsv.dao.QLTCDao;
import vn.viettuts.qlsv.entity.QLTC;
import vn.viettuts.qlsv.view.QLTCView;

public class QLTCController {

    private QLTCDao qltcDao;
    private QLTCView qltcView;
    List<QLTC> t;
   

    public QLTCController(QLTCView view) {
        this.qltcView = view;
        qltcDao = new QLTCDao();
        t  = qltcDao.readListQLTCs();

        view.addAddQLTCListener(new AddQLTCListener());
        view.addEditQLTCListener(new EditQLTCListener());
        view.addDeleteQLTCListener(new DeleteQLTCListener());
        view.addClearListener(new ClearQLTCListener());

        view.addListQLTCSelectionListener(new ListQLTCSelectionListener(qltcView));
        view.addListQLTCSelectionListener2(new ListQLTCSelectionListener(qltcView));
        view.addListQLTCSelectionListener3(new ListQLTCSelectionListener(qltcView));
        view.addListQLTCSelectionListener1(new ListQLTCSelectionListener(qltcView));
        view.addSearchListener(new SearchListener());
        view.addCancelSearchQLTCListener(new CancelSearchQLTCListener());
        view.addSortbydateListener(new SortbydateListener ());
        view.addSortbyamountListener(new SortbyamountListener () );
    }

    public void showQLTCView() {
        List<QLTC> qltcList = qltcDao.getListQLTCs();
        qltcView.setVisible(true);
        qltcView.showListQLTC(qltcList);
        qltcView.showListQLTC1(qltcList);
        qltcView.updateLabels(qltcList);
        qltcView.updateLabelsThongke(qltcList);
    }

    class AddQLTCListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            qltcView.getAddBtn().setEnabled(true);
            QLTC qltc = qltcView.getQLTCInfo();
            if (qltc != null) {
                qltcDao.add(qltc);

                qltcView.showQLTC(qltc);
                qltcView.showListQLTC(qltcDao.getListQLTCs());
                qltcView.showListQLTC1(qltcDao.getListQLTCs());
                //qltcView.showListQLTC2(qltcDao.getListQLTCs());
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
                qltcView.showListQLTC1(qltcDao.getListQLTCs());
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
                qltcView.showListQLTC1(qltcDao.getListQLTCs());
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
            qltcView.showListQLTC(t);
            qltcView.showListQLTC1(t);
        }
    }

    class SortDateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            qltcDao.sortQLTCByDate(t);
            qltcView.showListQLTC(t);
            qltcView.showListQLTC1(t);
            qltcDao.sortQLTCByAmount(t);
        }
    }
    class ListQLTCSelectionListener implements ListSelectionListener {
        private QLTCView qltcView;

        public ListQLTCSelectionListener(QLTCView qltcView) {
            this.qltcView = qltcView;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                if (e.getSource() == qltcView.getQltcTable().getSelectionModel()) {
                    qltcView.fillQLTCFromSelectedRow();
                } else if (e.getSource() == qltcView.getQltcTable2().getSelectionModel()) {
                    qltcView.fillQLTCFromSelectedRow2();
                }
            }
        }
    }
    class SearchListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            List<QLTC> temp = new ArrayList<>();
            temp.clear();
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
            }else if (check == 5){
                temp = qltcDao.searchByAmountRange(search);
            }
            t = temp;
            

            qltcView.getBtnSearch().setEnabled(true);
            qltcView.getFieldSearch().setEnabled(true);
            if (!temp.isEmpty()) {
                
                qltcView.showListQLTC(temp);
                qltcView.showListQLTC1(temp);
                qltcView.showExpenseIncomePieChart(temp);
                qltcView.updateLabels(temp);
                qltcView.updateLabelsThongke(temp);
                
            } else {
                qltcView.showMessage("Không tìm thấy kết quả!");
            }
            
            
            
        }

    }
    class CancelSearchQLTCListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            QLTCDao qltcDAOtmp = new QLTCDao();
            t = qltcDAOtmp.readListQLTCs();
            qltcView.showExpenseIncomePieChart(t);
            qltcView.showListQLTC(t);
            qltcView.showListQLTC1(t);
            qltcView.cancelSearch();

        }
    }
class SortbydateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            qltcDao.sortQLTCByDate(t);
            qltcView.showListQLTC(t);
            qltcView.showListQLTC1(t);
        }
    }
class SortbyamountListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            qltcDao.sortQLTCByAmount(t);
            qltcView.showListQLTC(t);
            qltcView.showListQLTC1(t);
        }
    }
}
