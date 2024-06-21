package giis.demo.tkrun.controller;

import giis.demo.tkrun.view.PackageView;
import giis.demo.util.SwingMain;
import giis.demo.tkrun.DTOs.PackageDTO;
import giis.demo.util.SwingUtil;

import javax.swing.JOptionPane;

public class PackageController {

    private PackageView view;
    private SwingMain main;
    private static PackageDTO dto;
    

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
		dto.addSendPackage(view.getIdSender(), view.getIdRec(), view.getDirectionSender(), view.getDirectionRec(), view.getComboCitySender(), view.getComboCityRec(), view.getWidth(), view.getHeight(), view.getLength(), view.getWeight(), view.getPrice());
        JOptionPane.showMessageDialog(null, "Registration of the package has been completed");
    }

    public void getDistance() {
        String a = (String) view.getComboCitySender().getSelectedItem();
        String b= (String) view.getComboCityRec().getSelectedItem();
        view.setDistance(dto.getRouteDistance(a, b));
    }
}
