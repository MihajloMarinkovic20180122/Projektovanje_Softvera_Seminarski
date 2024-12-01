/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import forme.angazovanja.DodajAngazovanjeForma;
import forme.angazovanja.ObrisiAngazovanjeForma;
import forme.projekti.DodajProjekatForma;
import forme.projekti.ObrisiProjekatForma;
import forme.zaposleni.DodajZaposlenogForma;
import forme.zaposleni.IzmeniZaposlenogForma;
import forme.zaposleni.ObrisiZaposlenogForma;
import javax.swing.JOptionPane;
import kontroler.KlijentKontroler;
import kontroler.KlijentKontrolerAdministrator;
import sesija.Sesija;

/**
 *
 * @author Mihajlo
 */
public class GlavnaForma extends javax.swing.JFrame{

    /**
     * Creates new form GlavnaForma
     */
    public GlavnaForma() throws Exception {
        initComponents();
        setLocationRelativeTo(null);
        lblUlogovani.setText("Administrator: " + Sesija.getInstanca().getUlogovani());
        menuAngazovanja.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUlogovani = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuZaposleni = new javax.swing.JMenu();
        menuItemDodajZaposlenog = new javax.swing.JMenuItem();
        menuItemIzmeniZaposlenog = new javax.swing.JMenuItem();
        menuProjekti = new javax.swing.JMenu();
        menuItemDodajProjekat = new javax.swing.JMenuItem();
        menuItemObrisiProjekat = new javax.swing.JMenuItem();
        menuAngazovanja = new javax.swing.JMenu();
        btnDodajAngazovanje = new javax.swing.JMenuItem();
        menuItemObrisiAngazovanje = new javax.swing.JMenuItem();
        menuOdjava = new javax.swing.JMenu();
        menuItemOdjaviSe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUlogovani.setText("jLabel1");

        menuZaposleni.setText("Zaposleni");

        menuItemDodajZaposlenog.setText("Dodavanje");
        menuItemDodajZaposlenog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDodajZaposlenogActionPerformed(evt);
            }
        });
        menuZaposleni.add(menuItemDodajZaposlenog);

        menuItemIzmeniZaposlenog.setText("Izmena i Brisanje");
        menuItemIzmeniZaposlenog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemIzmeniZaposlenogActionPerformed(evt);
            }
        });
        menuZaposleni.add(menuItemIzmeniZaposlenog);

        jMenuBar1.add(menuZaposleni);

        menuProjekti.setText("Projekti");

        menuItemDodajProjekat.setText("Dodavanje");
        menuItemDodajProjekat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDodajProjekatActionPerformed(evt);
            }
        });
        menuProjekti.add(menuItemDodajProjekat);

        menuItemObrisiProjekat.setText("Izmena i Brisanje");
        menuItemObrisiProjekat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemObrisiProjekatActionPerformed(evt);
            }
        });
        menuProjekti.add(menuItemObrisiProjekat);

        jMenuBar1.add(menuProjekti);

        menuAngazovanja.setText("Angazovanja");

        btnDodajAngazovanje.setText("Dodaj");
        btnDodajAngazovanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajAngazovanjeActionPerformed(evt);
            }
        });
        menuAngazovanja.add(btnDodajAngazovanje);

        menuItemObrisiAngazovanje.setText("Obriši");
        menuItemObrisiAngazovanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemObrisiAngazovanjeActionPerformed(evt);
            }
        });
        menuAngazovanja.add(menuItemObrisiAngazovanje);

        jMenuBar1.add(menuAngazovanja);

        menuOdjava.setText("Odjava");

        menuItemOdjaviSe.setText("Odjavi se");
        menuItemOdjaviSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOdjaviSeActionPerformed(evt);
            }
        });
        menuOdjava.add(menuItemOdjaviSe);

        jMenuBar1.add(menuOdjava);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(589, Short.MAX_VALUE)
                .addComponent(lblUlogovani)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblUlogovani)
                .addGap(0, 262, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemOdjaviSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOdjaviSeActionPerformed
        try{
            int izbor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da se odjavite?", "Odjava", JOptionPane.YES_NO_OPTION);

            if (izbor == JOptionPane.YES_OPTION) {
                Sesija.getInstanca().setUlogovani(null);
                KlijentKontrolerAdministrator.getInstanca().logout();
                this.dispose();
                //new LoginForma().setVisible(true);
            }
            
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_menuItemOdjaviSeActionPerformed

    private void menuItemDodajZaposlenogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDodajZaposlenogActionPerformed
        new DodajZaposlenogForma(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuItemDodajZaposlenogActionPerformed

    private void menuItemDodajProjekatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDodajProjekatActionPerformed
        new DodajProjekatForma(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuItemDodajProjekatActionPerformed

    private void menuItemObrisiProjekatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemObrisiProjekatActionPerformed
        new ObrisiProjekatForma(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuItemObrisiProjekatActionPerformed

    private void btnDodajAngazovanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajAngazovanjeActionPerformed
        new DodajAngazovanjeForma(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnDodajAngazovanjeActionPerformed

    private void menuItemObrisiAngazovanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemObrisiAngazovanjeActionPerformed
        new ObrisiAngazovanjeForma(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuItemObrisiAngazovanjeActionPerformed

    private void menuItemIzmeniZaposlenogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemIzmeniZaposlenogActionPerformed
        new IzmeniZaposlenogForma(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menuItemIzmeniZaposlenogActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnDodajAngazovanje;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblUlogovani;
    private javax.swing.JMenu menuAngazovanja;
    private javax.swing.JMenuItem menuItemDodajProjekat;
    private javax.swing.JMenuItem menuItemDodajZaposlenog;
    private javax.swing.JMenuItem menuItemIzmeniZaposlenog;
    private javax.swing.JMenuItem menuItemObrisiAngazovanje;
    private javax.swing.JMenuItem menuItemObrisiProjekat;
    private javax.swing.JMenuItem menuItemOdjaviSe;
    private javax.swing.JMenu menuOdjava;
    private javax.swing.JMenu menuProjekti;
    private javax.swing.JMenu menuZaposleni;
    // End of variables declaration//GEN-END:variables
}
