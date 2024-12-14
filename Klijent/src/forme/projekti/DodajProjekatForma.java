/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme.projekti;

import domen.Prioritet;
import domen.Projekat;
import domen.Stanje;
import domen.Zaposleni;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import kontroler.KlijentKontroler;
import kontroler.KlijentKontrolerProjekat;
import kontroler.KlijentKontrolerZaposleni;
import modeli.ModelTabeleZaposleni;

/**
 *
 * @author Mihajlo
 */
public class DodajProjekatForma extends javax.swing.JDialog {

    /**
     * Creates new form DodajProjekatForma
     */
    ModelTabeleZaposleni mtz;
    public DodajProjekatForma(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            setLocationRelativeTo(null);
            setTitle("Dodavanje projekta");
            mtz = new ModelTabeleZaposleni();
            tblZaposleni.setModel(mtz);
            popuniCmbRukovodilac();
            popuniCmbPrioriter();
            popuniCmbZaposleni();
        } catch (Exception ex) {
            Logger.getLogger(DodajProjekatForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNazivProjekta = new javax.swing.JLabel();
        lblNazivProjekta1 = new javax.swing.JLabel();
        lblRukovodilacProjekta = new javax.swing.JLabel();
        lblPrioritetProjekta = new javax.swing.JLabel();
        txtNazivProjekta = new javax.swing.JTextField();
        txtPocetakRealizacijeProjekta = new javax.swing.JFormattedTextField();
        cmbRukovodilacProjekta = new javax.swing.JComboBox<>();
        cmbPrioritetProjekta = new javax.swing.JComboBox();
        btnDodajProjekat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbZaposleni = new javax.swing.JComboBox<>();
        btnDodajZaposlenog = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblZaposleni = new javax.swing.JTable();
        btnObrisiZaposlenog = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNazivProjekta.setText("Naziv:");

        lblNazivProjekta1.setText("Pocetak realizacije:");

        lblRukovodilacProjekta.setText("Rukovodilac:");

        lblPrioritetProjekta.setText("Prioritet:");

        try {
            txtPocetakRealizacijeProjekta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.##.####.")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnDodajProjekat.setText("Sačuvaj");
        btnDodajProjekat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajProjekatActionPerformed(evt);
            }
        });

        jLabel1.setText("Zaposleni:");

        btnDodajZaposlenog.setText("Dodaj");
        btnDodajZaposlenog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajZaposlenogActionPerformed(evt);
            }
        });

        tblZaposleni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblZaposleni);

        btnObrisiZaposlenog.setText("Obriši");
        btnObrisiZaposlenog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiZaposlenogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNazivProjekta1)
                            .addComponent(lblRukovodilacProjekta)
                            .addComponent(lblPrioritetProjekta)
                            .addComponent(lblNazivProjekta)
                            .addComponent(jLabel1))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNazivProjekta)
                            .addComponent(txtPocetakRealizacijeProjekta)
                            .addComponent(cmbRukovodilacProjekta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPrioritetProjekta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnDodajProjekat))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cmbZaposleni, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDodajZaposlenog)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnObrisiZaposlenog, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNazivProjekta)
                    .addComponent(txtNazivProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNazivProjekta1)
                    .addComponent(txtPocetakRealizacijeProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRukovodilacProjekta)
                    .addComponent(cmbRukovodilacProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrioritetProjekta)
                    .addComponent(cmbPrioritetProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDodajZaposlenog)
                    .addComponent(btnObrisiZaposlenog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDodajProjekat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDodajProjekatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajProjekatActionPerformed
        try {
            String naziv = txtNazivProjekta.getText();
            if (naziv.isEmpty()) {
                throw new Exception("Niste uneli naziv.");

            }

            if(txtPocetakRealizacijeProjekta.getText().contains(" ")){
                throw new Exception("Niste uneli pocetak realizacije.");
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
            Date datumPocetkaRealizacije = sdf.parse(txtPocetakRealizacijeProjekta.getText());
            
            if(datumPocetkaRealizacije.after(new Date())){
                throw new Exception("Datum početka realizacije ne sme biti u buducnosti.");
            }

            Zaposleni rukovodilac = (Zaposleni) cmbRukovodilacProjekta.getSelectedItem();

            Prioritet prioritet = (Prioritet) cmbPrioritetProjekta.getSelectedItem();
            
            LinkedList<Zaposleni> listaZaposlenih = mtz.vratiListu();
            
            if(listaZaposlenih.size() == 0){
                int izbor = JOptionPane.showConfirmDialog(this, "Niste dodali nijednog zaposlenog. Da li zelite da sačuvate projekat bez zaposlenih?", "Čuvanje projekta", JOptionPane.YES_NO_OPTION);

                if (izbor == JOptionPane.YES_OPTION) {
                    Projekat projekat = new Projekat(0, naziv, datumPocetkaRealizacije, rukovodilac, prioritet, Stanje.Kreiran, listaZaposlenih, null);
                    KlijentKontrolerProjekat.getInstanca().dodajProjekat(projekat);
                    
                    JOptionPane.showMessageDialog(rootPane, "Sistem je zapamtio projekat.", "Uspešno izvršeno.", JOptionPane.INFORMATION_MESSAGE);

                    txtNazivProjekta.setText("");
                    txtPocetakRealizacijeProjekta.setText("");
                    cmbRukovodilacProjekta.setSelectedIndex(0);
                    cmbPrioritetProjekta.setSelectedIndex(0);
                    cmbZaposleni.setSelectedIndex(0);
                    mtz.setListaZaposlenih(new LinkedList<>());

                    return;
                } else {
                    return;
                }
            }
            Projekat projekat = new Projekat(0, naziv, datumPocetkaRealizacije, rukovodilac, prioritet, Stanje.Kreiran, listaZaposlenih, null);
            KlijentKontrolerProjekat.getInstanca().dodajProjekat(projekat);
            
            JOptionPane.showMessageDialog(rootPane, "Sistem je zapamtio projekat.", "Uspešno izvršeno.", JOptionPane.INFORMATION_MESSAGE);
            
            txtNazivProjekta.setText("");
            txtPocetakRealizacijeProjekta.setText("");
            cmbRukovodilacProjekta.setSelectedIndex(0);
            cmbPrioritetProjekta.setSelectedIndex(0);
            cmbZaposleni.setSelectedIndex(0);
            mtz.setListaZaposlenih(new LinkedList<>());

            return;

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Doslo je do greske!", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDodajProjekatActionPerformed

    private void btnDodajZaposlenogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajZaposlenogActionPerformed
        Zaposleni zaposleni = (Zaposleni) cmbZaposleni.getSelectedItem();
        ModelTabeleZaposleni mtz = (ModelTabeleZaposleni) tblZaposleni.getModel();
        LinkedList<Zaposleni> listaZaposlenihUTabeli = mtz.vratiListu();
        if(listaZaposlenihUTabeli.size() > 0){
            if(listaZaposlenihUTabeli.contains(zaposleni)){
                JOptionPane.showMessageDialog(rootPane, "Zaposleni je vec dodat.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mtz.dodajZaposlenog(zaposleni);
            }
        } else {
            mtz.dodajZaposlenog(zaposleni);
        }
        
    }//GEN-LAST:event_btnDodajZaposlenogActionPerformed

    private void btnObrisiZaposlenogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiZaposlenogActionPerformed
        int red = tblZaposleni.getSelectedRow();
        if(red != -1){
            ModelTabeleZaposleni mtz = (ModelTabeleZaposleni) tblZaposleni.getModel();
            mtz.obrisiZaposlenog(red);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Odaberite zaposlenog iz liste kod zelite da uklonite sa projekta.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnObrisiZaposlenogActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodajProjekat;
    private javax.swing.JButton btnDodajZaposlenog;
    private javax.swing.JButton btnObrisiZaposlenog;
    private javax.swing.JComboBox cmbPrioritetProjekta;
    private javax.swing.JComboBox<Object> cmbRukovodilacProjekta;
    private javax.swing.JComboBox<Object> cmbZaposleni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNazivProjekta;
    private javax.swing.JLabel lblNazivProjekta1;
    private javax.swing.JLabel lblPrioritetProjekta;
    private javax.swing.JLabel lblRukovodilacProjekta;
    private javax.swing.JTable tblZaposleni;
    private javax.swing.JTextField txtNazivProjekta;
    private javax.swing.JFormattedTextField txtPocetakRealizacijeProjekta;
    // End of variables declaration//GEN-END:variables

    private void popuniCmbRukovodilac() throws Exception {
        cmbRukovodilacProjekta.removeAllItems();
        LinkedList<Zaposleni> listaRukovodioca = KlijentKontrolerZaposleni.getInstanca().vratiZaposlene();
        for (Zaposleni zaposleni : listaRukovodioca) {
            cmbRukovodilacProjekta.addItem(zaposleni);
        }
    }

    private void popuniCmbPrioriter() {
        cmbPrioritetProjekta.removeAllItems();
        for (Prioritet prioritet : Prioritet.values()) {
            cmbPrioritetProjekta.addItem(prioritet);
        }
    }

    private void popuniCmbZaposleni() throws Exception {
        cmbZaposleni.removeAllItems();
        LinkedList<Zaposleni> listaZaposlenih = KlijentKontrolerZaposleni.getInstanca().vratiZaposlene();
        for (Zaposleni zaposleni : listaZaposlenih) {
            cmbZaposleni.addItem(zaposleni);
        }
    }
}
