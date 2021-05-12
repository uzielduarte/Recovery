/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;
import view.panels.PnlVehicle;
import view.panels.PnlVisualizacionVehicle;
import view.panels.controllers.PnlVehicleController;

/**
 *
 * @author UZIEL
 */
public class DlgVehicle extends javax.swing.JDialog
{
    private PnlVehicle pnlVehicle;
    private PnlVehicleController pnlVehicleController;
    /**
     * Creates new form DlgVehicle
     */
    public DlgVehicle(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }

    public void addPropertySupport(PropertyChangeListener pcl)
    {
        pnlVehicleController.addPropertyChangeListener(pcl);
    }
    
    public void loadPnlVehicleData(PnlVisualizacionVehicle pnlVisualizacionVehicle, int row)
    {
        pnlVehicle.getTxtStockNumber().setText(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 0).toString().trim());
        pnlVehicle.getSpnYear().setValue(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 1));
        pnlVehicle.getCmbMake().setSelectedItem(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 2).toString().trim());
        pnlVehicle.getCmbModel().setSelectedItem(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 3).toString().trim());
        pnlVehicle.getTxtStyle().setText(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 4).toString().trim());
        pnlVehicle.getTxtVIN().setText(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 5).toString().trim());
        pnlVehicle.getCmbEColor().setSelectedItem(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 6).toString().trim());
        pnlVehicle.getCmbIColor().setSelectedItem(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 7).toString().trim());
        //Integer.parseInt(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 8).toString())
        Integer miles = Integer.parseInt(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 8).toString());
        Double price = Double.parseDouble(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 9).toString());
        pnlVehicle.getSpnMiles().getModel().setValue(miles);
        pnlVehicle.getSpnPrice().getModel().setValue(price);
        
        if(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 10).toString() == "AUTOMATIC")
            pnlVehicle.getBtngTrans().setSelected(pnlVehicle.getRbtnAut().getModel(), true);
        else
            pnlVehicle.getBtngTrans().setSelected(pnlVehicle.getRbtnMan().getModel(), true);
        pnlVehicle.getTxtEngine().setText(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 11).toString().trim());
        pnlVehicle.getTxtImage().setText(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 12).toString().trim());
        pnlVehicle.getCmbStatus().setSelectedItem(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(row, 13).toString().trim());
        
        //int vehicleId = pnlVisualizacionVehicleController.getVehicles().get(row).getId();
        int id = (int) pnlVisualizacionVehicle.getTblVisualizacionVehicle().getValueAt(pnlVisualizacionVehicle.getTblVisualizacionVehicle().getSelectedRow(), 14);

        pnlVehicleController.setVehicleIdToEdit(id);
        pnlVehicleController.setIsUpdate(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        pnlContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New or Update Vehicle");
        setModal(true);

        pnlContent.setLayout(new java.awt.BorderLayout());
        pnlVehicle = new PnlVehicle();
        pnlVehicleController = new PnlVehicleController(pnlVehicle);
        pnlContent.add(pnlVehicle, BorderLayout.CENTER);
        getContentPane().add(pnlContent, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(559, 526));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(DlgVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DlgVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DlgVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DlgVehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                DlgVehicle dialog = new DlgVehicle(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlContent;
    // End of variables declaration//GEN-END:variables
}
