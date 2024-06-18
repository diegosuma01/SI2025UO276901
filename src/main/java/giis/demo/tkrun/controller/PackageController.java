package giis.demo.tkrun.controller;

import giis.demo.tkrun.view.PackageView;
import giis.demo.util.SwingMain;
import giis.demo.tkrun.DTOs.PackageDTO;
import giis.demo.tkrun.model.PackageModel;
import giis.demo.util.SwingUtil;

import javax.rmi.CORBA.Util;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class PackageController {

    private PackageView view;
    private SwingMain main;
    private PackageDTO dto;
    

    public PackageController(PackageDTO packageDTO, PackageView packageView, SwingMain swingMain) {
        this.dto = packageDTO;
        this.view = packageView;
        this.main = swingMain;
        this.initView();
    }

    public void initController() {
        view.getBtnSend().addActionListener(e -> SwingUtil.exceptionWrapper(() -> sendPackage()));
    }

    public void initView() {
        view.getFrame().setVisible(true);
	}

    public void sendPackage() {

		dto.addSendPackage(view.getNameSender(), view.getNameRec(), view.getPhoneSender(), view.getPhoneRec(), view.getEmailSender(), view.getEmailRec(), view.getDirectionSender(),
         view.getDirectionRec(), view.getComboCitySender(), view.getComboCityRec(), view.getComboPackageSize(), view.getWeight());
		JOptionPane.showMessageDialog(null, "The enrollment has been completed");
	}

}
