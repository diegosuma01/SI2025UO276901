package giis.demo.tkrun.controller;

import giis.demo.tkrun.view.UserView;
import giis.demo.util.SwingMain;
import giis.demo.tkrun.DTOs.UserDTO;
import giis.demo.tkrun.model.UsersModel;
import giis.demo.util.SwingUtil;

import javax.rmi.CORBA.Util;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class UserController {

    private UserView view;
    private SwingMain main;
    private UserDTO dto;
    

    public UserController(UserDTO packageDTO, UserView packageView, SwingMain swingMain) {
        this.dto = packageDTO;
        this.view = packageView;
        this.main = swingMain;
        this.initView();
    }

    public void initController() {
        view.getbtnRegister().addActionListener(e -> SwingUtil.exceptionWrapper(() -> saveUser()));
    }

    public void initView() {
        view.getFrame().setVisible(true);
	}

    public void saveUser() {

		dto.addSaveUser(view.getIdSender(), view.getNameSender(), view.getPhoneSender(), view.getEmailSender());
		JOptionPane.showMessageDialog(null, "Registration of the User has been completed");
	}

}
