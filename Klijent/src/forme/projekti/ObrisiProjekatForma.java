/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme.projekti;

import domen.Prioritet;
import domen.Stanje;
import domen.Projekat;
import domen.Zaposleni;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kontroler.KlijentKontroler;
import kontroler.KlijentKontrolerProjekat;
import kontroler.KlijentKontrolerZaposleni;
import modeli.ModelTabeleProjekti;
import modeli.ModelTabeleZaposleni;

/**
 *
 * @author Mihajlo
 */
public class ObrisiProjekatForma extends javax.swing.JDialog {

    /**
     * Creates new form ObrisiProjekatForma
     */
    ModelTabeleProjekti mtp;
    ModelTabeleZaposleni mtz;
    Projekat odabraniProjekat;
    public ObrisiProjekatForma(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            setLocationRelativeTo(null);
            setTitle("Izmena i Brisanje projekta");
            //tblProjekti.setAutoCreateRowSorter(true);
            mtp = new ModelTabeleProjekti();
            tblProjekti.setModel(mtp);
            mtz = new ModelTabeleZaposleni();
            tblZaposleni.setModel(mtz);
            btnObrisiProjekat.setEnabled(false);
            btnIzmeniProjekat.setEnabled(false);
            popuniTabeluSvimProjektima();
            popuniCmbRukovodilac();
            popuniCmbPrioriter();
            popuniCmbStanje();
            popuniCmbZaposleni();
        } catch (Exception ex) {
            Logger.getLogger(ObrisiProjekatForma.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        txtPretraga = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProjekti = new javax.swing.JTable();
        btnPronadjiProjekat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnObrisiProjekat = new javax.swing.JButton();
        btnIzmeniProjekat = new javax.swing.JButton();
        txtPocetakRealizacijeProjekta = new javax.swing.JFormattedTextField();
        cmbRukovodilacProjekta = new javax.swing.JComboBox<>();
        cmbPrioritetProjekta = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmbZaposleni = new javax.swing.JComboBox<>();
        lblNazivProjekta = new javax.swing.JLabel();
        btnDodajZaposlenog = new javax.swing.JButton();
        lblNazivProjekta1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblZaposleni = new javax.swing.JTable();
        lblRukovodilacProjekta = new javax.swing.JLabel();
        lblPrioritetProjekta = new javax.swing.JLabel();
        btnObrisiZaposlenog = new javax.swing.JButton();
        txtNazivProjekta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIdProjekta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbStanjeProjekta = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pronađi projekat"));

        tblProjekti.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProjekti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProjektiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProjekti);

        btnPronadjiProjekat.setText("Pronađi");
        btnPronadjiProjekat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPronadjiProjekatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPretraga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPronadjiProjekat)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPronadjiProjekat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Učitani projekat"));

        btnObrisiProjekat.setText("Obriši projekat");
        btnObrisiProjekat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiProjekatActionPerformed(evt);
            }
        });

        btnIzmeniProjekat.setText("Izmeni projekat");
        btnIzmeniProjekat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniProjekatActionPerformed(evt);
            }
        });

        try {
            txtPocetakRealizacijeProjekta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.##.####.")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel1.setText("Zaposleni:");

        lblNazivProjekta.setText("Naziv:");

        btnDodajZaposlenog.setText("Dodaj");
        btnDodajZaposlenog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajZaposlenogActionPerformed(evt);
            }
        });

        lblNazivProjekta1.setText("Pocetak realizacije:");

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
        jScrollPane3.setViewportView(tblZaposleni);

        lblRukovodilacProjekta.setText("Rukovodilac:");

        lblPrioritetProjekta.setText("Prioritet:");

        btnObrisiZaposlenog.setText("Obriši");
        btnObrisiZaposlenog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiZaposlenogActionPerformed(evt);
            }
        });

        jLabel2.setText("ID:");

        txtIdProjekta.setEditable(false);

        jLabel3.setText("Stanje:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnObrisiProjekat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIzmeniProjekat))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNazivProjekta1)
                            .addComponent(lblRukovodilacProjekta)
                            .addComponent(lblPrioritetProjekta)
                            .addComponent(lblNazivProjekta)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNazivProjekta)
                            .addComponent(txtPocetakRealizacijeProjekta)
                            .addComponent(cmbRukovodilacProjekta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPrioritetProjekta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbZaposleni, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDodajZaposlenog)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnObrisiZaposlenog, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIdProjekta)
                            .addComponent(cmbStanjeProjekta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNazivProjekta)
                    .addComponent(txtNazivProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNazivProjekta1)
                    .addComponent(txtPocetakRealizacijeProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRukovodilacProjekta)
                    .addComponent(cmbRukovodilacProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrioritetProjekta)
                    .addComponent(cmbPrioritetProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStanjeProjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDodajZaposlenog)
                    .addComponent(btnObrisiZaposlenog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnObrisiProjekat)
                    .addComponent(btnIzmeniProjekat))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPronadjiProjekatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPronadjiProjekatActionPerformed
        try {
            String pretraga = txtPretraga.getText();
            LinkedList<Projekat> listaProjekataIzPretrage = KlijentKontrolerProjekat.getInstanca().pronadjiPaVratiProjekte(pretraga);
            ModelTabeleProjekti mtp =  (ModelTabeleProjekti) tblProjekti.getModel();
            mtp.setListaProjekata(listaProjekataIzPretrage);
            LinkedList<Projekat> vracenaLista = mtp.vratiListu();
            if(vracenaLista.size() == 0){
                JOptionPane.showMessageDialog(rootPane, "Sistem ne može da nađe projekat po zadatoj vrednosti.", "Neuspešna pretraga.", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Sistem je našao projekat po zadatoj vrednosti.", "Uspešno izvršeno.", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_btnPronadjiProjekatActionPerformed

    private void btnObrisiProjekatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiProjekatActionPerformed
        try {
            int red = tblProjekti.getSelectedRow();
            Projekat projekat = mtp.vratiOdabraniProjekat(red);
            
            KlijentKontrolerProjekat.getInstanca().obrisiProjekat(projekat);
            
            //JOptionPane.showMessageDialog(rootPane, "Uspesno ste obrisali projekat: " + projekat);
            JOptionPane.showMessageDialog(rootPane, "Sistem je obrisao projekat.", "Uspešno izvršeno.", JOptionPane.INFORMATION_MESSAGE);
            
            mtp.setListaProjekata(KlijentKontrolerProjekat.getInstanca().vratiProjekte());
            
            txtIdProjekta.setText("");
            txtNazivProjekta.setText("");
            txtPocetakRealizacijeProjekta.setText("");
            cmbRukovodilacProjekta.setSelectedIndex(0);
            cmbPrioritetProjekta.setSelectedIndex(0);
            cmbStanjeProjekta.setSelectedIndex(0);
            cmbZaposleni.setSelectedIndex(0);
            ModelTabeleZaposleni mtz =  (ModelTabeleZaposleni) tblZaposleni.getModel();
            mtz.setListaZaposlenih(new LinkedList<>());
            btnObrisiProjekat.setEnabled(false);
            btnIzmeniProjekat.setEnabled(false);
            

        } catch (Exception ex) {
            ex.printStackTrace();
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Greska pri brisanju projekta!", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Doslo je do greske!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnObrisiProjekatActionPerformed

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

    private void tblProjektiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProjektiMouseClicked
        try{
            int red = tblProjekti.getSelectedRow();
            if(red == -1){

            } else {
                ModelTabeleProjekti mtp = (ModelTabeleProjekti) tblProjekti.getModel();
                odabraniProjekat = mtp.vratiOdabraniProjekat(red);
                Projekat ucitaniProjekat = KlijentKontrolerProjekat.getInstanca().ucitajProjekat(odabraniProjekat);
                popuniPodatkeUcitanogProjekta(ucitaniProjekat);
                //ModelTabeleZaposleni mtz = (ModelTabeleZaposleni) tblZaposleni.getModel();
                //odabraniZaposleni = mtz.vratiOdabranogZaposlenog(red);
                //Zaposleni ucitaniZaposleni = KlijentKontrolerZaposleni.getInstanca().ucitajZaposlenog(odabraniZaposleni);
    //            popuniPodatkeUcitanogZaposlenog(ucitaniZaposleni);
                btnIzmeniProjekat.setEnabled(true);
                btnObrisiProjekat.setEnabled(true);
                JOptionPane.showMessageDialog(rootPane, "Sistem je učitao projekat.", "Uspešno izvršeno.", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex){
            try {
                ex.printStackTrace();
                //JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Doslo je do greske!", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(rootPane, "Projekat " + odabraniProjekat + " je obrisan.", "Doslo je do greske.", JOptionPane.INFORMATION_MESSAGE);
                LinkedList<Projekat> listaSvihProjekata = KlijentKontrolerProjekat.getInstanca().vratiProjekte();
                if (listaSvihProjekata.size() > 0) {
                    ModelTabeleProjekti mtp =  (ModelTabeleProjekti) tblProjekti.getModel();
                    mtp.setListaProjekata(listaSvihProjekata);
                } else if(listaSvihProjekata.size() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Obrisali ste sve projekte. Ne postoji nijedan projekat za prikaz.");
                    this.dispose();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }//GEN-LAST:event_tblProjektiMouseClicked

    private void btnIzmeniProjekatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniProjekatActionPerformed
        try {
            Projekat izmenjeniProjekat = new Projekat();
            if(proveriNovePodatke(izmenjeniProjekat)){
                KlijentKontrolerProjekat.getInstanca().izmeniProjekat(izmenjeniProjekat);
                JOptionPane.showMessageDialog(rootPane, "Sistem je zapamtio projekat.", "Uspešno izvršeno.", JOptionPane.INFORMATION_MESSAGE);
                LinkedList<Projekat> listaSvihProjekata = KlijentKontrolerProjekat.getInstanca().vratiProjekte();
                ModelTabeleProjekti mtp = (ModelTabeleProjekti) tblProjekti.getModel();
                mtp.setListaProjekata(listaSvihProjekata);
                btnObrisiProjekat.setEnabled(false);
            }
        } catch (Exception ex) {
            Logger.getLogger(ObrisiProjekatForma.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Sistem ne može da zapamti projekta.", "Doslo je do greske!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIzmeniProjekatActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodajZaposlenog;
    private javax.swing.JButton btnIzmeniProjekat;
    private javax.swing.JButton btnObrisiProjekat;
    private javax.swing.JButton btnObrisiZaposlenog;
    private javax.swing.JButton btnPronadjiProjekat;
    private javax.swing.JComboBox cmbPrioritetProjekta;
    private javax.swing.JComboBox<Object> cmbRukovodilacProjekta;
    private javax.swing.JComboBox cmbStanjeProjekta;
    private javax.swing.JComboBox<Object> cmbZaposleni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNazivProjekta;
    private javax.swing.JLabel lblNazivProjekta1;
    private javax.swing.JLabel lblPrioritetProjekta;
    private javax.swing.JLabel lblRukovodilacProjekta;
    private javax.swing.JTable tblProjekti;
    private javax.swing.JTable tblZaposleni;
    private javax.swing.JTextField txtIdProjekta;
    private javax.swing.JTextField txtNazivProjekta;
    private javax.swing.JFormattedTextField txtPocetakRealizacijeProjekta;
    private javax.swing.JTextField txtPretraga;
    // End of variables declaration//GEN-END:variables

    private void popuniTabeluSvimProjektima() throws Exception {
        LinkedList<Projekat> listaSvihProjekata = KlijentKontrolerProjekat.getInstanca().vratiProjekte();
        if (listaSvihProjekata.size() > 0) {
            ModelTabeleProjekti mtp = (ModelTabeleProjekti) tblProjekti.getModel();
            mtp.setListaProjekata(listaSvihProjekata);
        } else if(listaSvihProjekata.size() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Ne postoji nijedan projekat.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Doslo je do greske prilikom pretrage projekata.");
            this.dispose();
        }
    }

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

    private void popuniPodatkeUcitanogProjekta(Projekat ucitaniProjekat) {
        txtIdProjekta.setText(String.valueOf(ucitaniProjekat.getProjekatId()));
        txtNazivProjekta.setText(ucitaniProjekat.getNazivProjekta());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        txtPocetakRealizacijeProjekta.setText(sdf.format(ucitaniProjekat.getPocetakRealizacije()));
        cmbRukovodilacProjekta.setSelectedItem(ucitaniProjekat.getRukovodilac());
        cmbPrioritetProjekta.setSelectedItem(ucitaniProjekat.getPrioritet());
        cmbStanjeProjekta.setSelectedItem(ucitaniProjekat.getStanje());
        ModelTabeleZaposleni mtz =  (ModelTabeleZaposleni) tblZaposleni.getModel();
        mtz.setListaZaposlenih(ucitaniProjekat.getZaposleni());
    
    }

    private boolean proveriNovePodatke(Projekat izmenjeniProjekat) throws Exception {
        izmenjeniProjekat.setProjekatId(Integer.parseInt(txtIdProjekta.getText()));
        
        String naziv = txtNazivProjekta.getText().trim();
            if (naziv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Polje Naziv ne sme biti prazno.", "Greska pri izmeni projekta!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        izmenjeniProjekat.setNazivProjekta(naziv);
        
        if(txtPocetakRealizacijeProjekta.getText().contains(" ")){
                JOptionPane.showMessageDialog(null, "Polje Početak realizacije ne sme biti prazno.", "Greska pri izmeni projekta!", JOptionPane.ERROR_MESSAGE);
                return false;
        }
            
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        izmenjeniProjekat.setPocetakRealizacije(sdf.parse(txtPocetakRealizacijeProjekta.getText()));

        izmenjeniProjekat.setRukovodilac((Zaposleni) cmbRukovodilacProjekta.getSelectedItem());
        izmenjeniProjekat.setPrioritet((Prioritet) cmbPrioritetProjekta.getSelectedItem());
        izmenjeniProjekat.setStanje((Stanje) cmbStanjeProjekta.getSelectedItem());
        ModelTabeleZaposleni mtz =  (ModelTabeleZaposleni) tblZaposleni.getModel();
        izmenjeniProjekat.setZaposleni(mtz.vratiListu());
        
        LinkedList<Zaposleni> listaZaposlenihProvera = izmenjeniProjekat.getZaposleni();
        
        if(listaZaposlenihProvera.size() == 0){
            int izbor = JOptionPane.showConfirmDialog(this, "Niste dodali nijednog zaposlenog. Da li zelite da sačuvate projekat bez zaposlenih?", "Čuvanje projekta", JOptionPane.YES_NO_OPTION);

            if (izbor == JOptionPane.YES_OPTION) {
                return true;
            }
            return false;
        }
        return true;
    }

    private void popuniCmbStanje() {
        cmbStanjeProjekta.removeAllItems();
        for (Stanje stanje : Stanje.values()) {
            cmbStanjeProjekta.addItem(stanje);
        }
    }
}
