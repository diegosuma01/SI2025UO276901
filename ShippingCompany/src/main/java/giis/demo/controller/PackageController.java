package ShippingCompany.src.main.java.giis.demo.controller;

import ShippingCompany.src.main.java.giis.demo.view.PackageView;
import ShippingCompany.src.main.java.giis.demo.SwingMain;
import ShippingCompany.src.main.java.giis.demo.DTOs.PackageDTO;
import ShippingCompany.src.main.java.giis.demo.model.PackageModel;
import ShippingCompany.src.main.java.giis.demo.util.SwingUtil;

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
