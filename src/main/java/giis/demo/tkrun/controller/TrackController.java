package giis.demo.tkrun.controller;

import java.util.List;

import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.DTOs.TrackDTO;
import giis.demo.tkrun.model.PackageModel;
import giis.demo.tkrun.view.TrackView;
import giis.demo.util.SwingUtil;
import giis.demo.util.SwingMain;
import giis.demo.tkrun.model.TrackModel;


public class TrackController {
    private TrackView view;
    private TrackDTO dto;
    private SwingMain main;
    private TrackModel model;

    public TrackController(TrackDTO trackDTO, TrackView trackView, SwingMain swingMain) {
        this.dto = trackDTO;
        this.view = trackView;
        this.main = swingMain;
        this.model = model;

        this.initView();
    }

    public void initView() {
        view.getFrame().setLocationRelativeTo(null);
        view.getFrame().setVisible(true);
    }

    public void initController() {
        view.getSearchButton()
                .addActionListener(e -> SwingUtil.exceptionWrapper(() -> getTrack()));        
        
    }

    private void getTrack() {
        String packageId = view.getPackageIdField().getText();
        List<TrackModel> listPackages = dto.getPackagesTrack(packageId);
        TableModel tmodel = SwingUtil.getTableModelFromPojos(listPackages, new String[] { "packageId", "location", "status", "timestamp"});
        view.getPackageInfoTable().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getPackageInfoTable());
        TableColumnModel columnModel = view.getPackageInfoTable().getColumnModel();
    }
    
}
