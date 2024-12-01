/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import domen.Administrator;
import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import domen.OrganizacionaCelina;
import domen.Prioritet;
import domen.Projekat;
import domen.RadnoMesto;
import domen.Stanje;
import domen.Zaposleni;
import java.io.FileInputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;

/**
 *
 * @author Mihajlo
 */
public class DBBroker {

    Connection konekcija;
    String url;
    String user;
    String password;

    public DBBroker() {
        Properties parametriBaze = new Properties();
        try {
            FileInputStream fis = new FileInputStream(Konstante.LOKACIJA_PARAMETARA_BAZE);
            parametriBaze.load(fis);
            url = parametriBaze.getProperty(Konstante.LOKACIJA_BAZE_KEY);;
            user = parametriBaze.getProperty(Konstante.USERNAME_BAZA_KEY);
            password = parametriBaze.getProperty(Konstante.PASSWORD_BAZA_KEY);
        } catch (Exception ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void otvoriKonekciju() throws SQLException {
        if (konekcija == null || konekcija.isClosed()) {
            konekcija = DriverManager.getConnection(url, user, password);
            konekcija.setAutoCommit(false);
        }
        System.out.println("Konekcija je otvorena");
    }

    public void potvrdiTransakciju() throws SQLException {
        konekcija.commit();
        System.out.println("Commit");
    }

    public void odbaciTransakciju() throws SQLException {
        konekcija.rollback();
        System.out.println("Rollback");
    }

    public void zatvoriKonekciju() throws SQLException {
        konekcija.close();
        System.out.println("Konekcija je zatvorena");
    }
    
    public LinkedList<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws SQLException {
        //throw new Exception("greska.");
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " " + odo.alijas()
                + " " + odo.join() + " ORDER BY 1";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();
        return (LinkedList<OpstiDomenskiObjekat>) odo.vratiSve(rs);
    }
    
    public LinkedList<OpstiDomenskiObjekat> ucitaj(OpstiDomenskiObjekat odo) throws SQLException {
        //throw new Exception("greska.");
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " " + odo.alijas()
                + " " + odo.join() + " " + odo.uslov() + " ORDER BY 1";
        System.out.println(upit);
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();
        return (LinkedList<OpstiDomenskiObjekat>) odo.vratiSve(rs);
    }
    
    public boolean zapamti(OpstiDomenskiObjekat odo) throws SQLException {
        //throw new Exception("greska.");
        String upit = "INSERT INTO " + odo.vratiNazivTabele() + " "
                + odo.vratiNaziveKolonaTabele()+ " VALUES(" + odo.vratiVrednostiZaKreiranje() + ")";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        int dodatiRedovi = ps.executeUpdate();
        return dodatiRedovi > 0;
    }
    
    public boolean obrisi(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "DELETE FROM " + odo.vratiNazivTabele()
                + " WHERE " + odo.vratiPrimarniKljuc();
        PreparedStatement ps = konekcija.prepareStatement(upit);
        int brojObrisanihRedova = ps.executeUpdate();
        return brojObrisanihRedova > 0;
    }
    
    public LinkedList<OpstiDomenskiObjekat> vratiZaVrednost(OpstiDomenskiObjekat objekat) throws SQLException {
        String upit = "SELECT * FROM " + objekat.vratiNazivTabele() + " " + objekat.alijas()
                + " " + objekat.join() + " " + objekat.uslovZaPretragu() + " ORDER BY 1";
        System.out.println(upit);
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ResultSet rs = ps.executeQuery(upit);
        return (LinkedList<OpstiDomenskiObjekat>) objekat.vratiSve(rs);
    }
    
    public boolean izmeni(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "UPDATE " + odo.vratiNazivTabele() + " SET "
                + odo.vratiVrednostiZaIzmenu()+ " WHERE " + odo.vratiPrimarniKljuc();
        System.out.println(upit);
        PreparedStatement ps = konekcija.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        int brojIzmenjenihRedova = ps.executeUpdate();
        return brojIzmenjenihRedova > 0;
    }
    
    public int vratiPoslednjiPrimarniKljuc(OpstiDomenskiObjekat objekat) throws SQLException {
        int maxId = 0;
        String upit = "SELECT MAX(" + objekat.vratiNazivPrimarnogKljuca() + ") as maxId FROM " + objekat.vratiNazivTabele();
        System.out.println(upit);
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            maxId = rs.getInt("maxId");
        }
        return maxId;
    }
    
    public void azurirajStanjeProjektaNaAktivan(Projekat projekat) throws Exception {
        try {
            String upit = "UPDATE projekat SET stanje = 'Aktivan' WHERE projekatId = ?";

            PreparedStatement ps = konekcija.prepareStatement(upit);
            ps.setInt(1, projekat.getProjekatId());

            ps.execute();
            konekcija.commit();
        } catch (Exception ex) {
            konekcija.rollback();
            throw new Exception("Doslo je do greske prilikom azuriranja projekta: " + projekat);
        }

    }
    
    public LinkedList<Angazovanje> vratiAngazovanjaProjekta(Projekat ucitaniProjekat) throws SQLException {
        LinkedList<Angazovanje> listaAngazovanja = new LinkedList<>();
        String upit = "SELECT * FROM angazovanje a JOIN zaposleni z ON a.zaposleniId = z.zaposleniId "
                    + "JOIN organizacionacelina oc ON z.organizacionaCelinaId = oc.organizacionaCelinaId "
                    + "JOIN radnomesto rm ON z.radnoMestoId = rm.radnoMestoId "
                    + "JOIN projekat p ON a.projekatId = p.projekatId where a.projekatId = ? order by 1";

        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, ucitaniProjekat.getProjekatId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Angazovanje a = new Angazovanje();
            a.setAngazovanjeId(rs.getInt("angazovanjeId"));

            Projekat p = new Projekat();
            p.setProjekatId(rs.getInt("projekatId"));
            p.setNazivProjekta(rs.getString("nazivProjekta"));
            p.setPocetakRealizacije(rs.getDate("pocetakRealizacije"));
            p.setPrioritet(Prioritet.valueOf(rs.getString("prioritet")));
            p.setStanje(Stanje.valueOf(rs.getString("stanje")));

            Zaposleni z = new Zaposleni();
            z.setZaposleniId(rs.getInt("zaposleniId"));
            z.setIme(rs.getString("ime"));
            z.setPrezime(rs.getString("prezime"));
            z.setEmail(rs.getString("email"));
            z.setDatumZaposlenja(rs.getDate("datumZaposlenja"));
            
            OrganizacionaCelina oc = new OrganizacionaCelina();
            oc.setOrganizacionaCelinaId(rs.getInt("organizacionaCelinaId"));
            oc.setNazivOrganizacioneCeline(rs.getString("nazivOrganizacioneCeline"));
            z.setOrganizacionaCelina(oc);

            RadnoMesto rm = new RadnoMesto();
            rm.setRadnoMestoId(rs.getInt("radnoMestoId"));
            rm.setNazivRadnogMesta(rs.getString("nazivRadnogMesta"));
            z.setRadnoMesto(rm);

            p.setRukovodilac(z);

            a.setProjekat(p);

            a.setZaposleni(z);

            a.setPocetakAngazovanja(rs.getDate("pocetakAngazovanja"));
            a.setKrajAngazovanja(rs.getDate("krajAngazovanja"));

            listaAngazovanja.add(a);
        }

        return listaAngazovanja;
    }
    
//    public Angazovanje vratiAngazovanjeZaProjekat(Projekat izmenjeniProjekat) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
    
//    public Administrator login(Administrator ad) throws Exception {
//        LinkedList<Administrator> listaAdministratora = new LinkedList<>();
//        String upit = "SELECT * FROM administrator";
//
//        PreparedStatement ps = konekcija.prepareStatement(upit);
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            int administratorId = rs.getInt("administratorId");
//            String email = rs.getString("email");
//            String lozinka = rs.getString("lozinka");
//            Administrator administrator = new Administrator(administratorId, email, lozinka);
//            listaAdministratora.add(administrator);
//        }
//
//        for (Administrator administrator : listaAdministratora) {
//            if (administrator.getEmail().equals(ad.getEmail()) && administrator.getLozinka().equals(ad.getLozinka())) {
//                return ad;
//            }
//        }
//        throw new Exception("Pogresni kredencijali. Ponovite unos.");
//
//    }

//    public LinkedList<OrganizacionaCelina> vratiOrganizacioneCeline() throws Exception {
//        LinkedList<OrganizacionaCelina> listaOrganizacionihCelina = new LinkedList<>();
//        String upit = "SELECT * FROM organizacionacelina";
//
//        PreparedStatement ps = konekcija.prepareStatement(upit);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            int organizacionaCelinaId = rs.getInt("organizacionaCelinaId");
//            String nazivOrganizacioneCeline = rs.getString("nazivOrganizacioneCeline");
//
//            OrganizacionaCelina organizacionaCelina = new OrganizacionaCelina(organizacionaCelinaId, nazivOrganizacioneCeline);
//            listaOrganizacionihCelina.add(organizacionaCelina);
//        }
//
//        return listaOrganizacionihCelina;
//    }

//    public LinkedList<RadnoMesto> vratiRadnaMesta(int organizacionaCelinaId) throws Exception {
//        LinkedList<RadnoMesto> listaRadnihMesta = new LinkedList<>();
//        String upit = "SELECT * FROM radnomesto WHERE organizacionaCelinaId = ?";
//
//        PreparedStatement ps = konekcija.prepareStatement(upit);
//        ps.setInt(1, organizacionaCelinaId);
//
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//
//            OrganizacionaCelina organizacionaCelina = new OrganizacionaCelina();
//            int organizacionaCelinaRM = rs.getInt("organizacionaCelinaId");
//            organizacionaCelina.setOrganizacionaCelinaId(organizacionaCelinaRM);
//
//            int radnoMestoId = rs.getInt("radnoMestoId");
//            String nazivRadnogMesta = rs.getString("nazivRadnogMesta");
//            int brojZaposlenih = rs.getInt("brojZaposlenih");
//
//            RadnoMesto radnoMesto = new RadnoMesto(organizacionaCelina, radnoMestoId, nazivRadnogMesta, brojZaposlenih);
//            listaRadnihMesta.add(radnoMesto);
//        }
//
//        return listaRadnihMesta;
//    }

//    public void dodajZaposlenog(Zaposleni zaposleni) throws Exception {
//        try {
//            String upit = "INSERT INTO zaposleni(ime, prezime, email, datumZaposlenja, organizacionaCelinaId, radnoMestoId) values (?, ?, ?, ?, ?, ?)";
//            PreparedStatement ps = konekcija.prepareStatement(upit);
//            ps.setString(1, zaposleni.getIme());
//            ps.setString(2, zaposleni.getPrezime());
//            ps.setString(3, zaposleni.getEmail());
//            ps.setDate(4, new java.sql.Date(zaposleni.getDatumZaposlenja().getTime()));
//            ps.setInt(5, zaposleni.getOrganizacionaCelina().getOrganizacionaCelinaId());
//            ps.setInt(6, zaposleni.getRadnoMesto().getRadnoMestoId());
//
//            ps.execute();
//
//            konekcija.commit();
//        } catch (Exception ex) {
//            konekcija.rollback();
//            throw new Exception("Sistem ne moze da doda zaposlenog.");
//        }
//    }

//    public LinkedList<Zaposleni> vratiZaposlene() throws Exception {
//        LinkedList<Zaposleni> listaZaposlenih = new LinkedList<>();
//        String upit = "SELECT * FROM zaposleni z "
//                + "JOIN organizacionaCelina oc ON z.organizacionaCelinaId = oc.organizacionaCelinaId "
//                + "JOIN radnoMesto rm ON z.radnoMestoId = rm.radnoMestoId";
//        PreparedStatement ps = konekcija.prepareStatement(upit);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            Zaposleni z = new Zaposleni();
//            z.setZaposleniId(rs.getInt("zaposleniId"));
//            z.setIme(rs.getString("ime"));
//            z.setPrezime(rs.getString("prezime"));
//            z.setEmail(rs.getString("email"));
//            z.setDatumZaposlenja(rs.getDate("datumZaposlenja"));
//
//            OrganizacionaCelina oc = new OrganizacionaCelina();
//            oc.setOrganizacionaCelinaId(rs.getInt("organizacionaCelinaId"));
//            oc.setNazivOrganizacioneCeline(rs.getString("nazivOrganizacioneCeline"));
//            z.setOrganizacionaCelina(oc);
//
//            RadnoMesto rm = new RadnoMesto();
//            rm.setRadnoMestoId(rs.getInt("radnoMestoId"));
//            rm.setNazivRadnogMesta(rs.getString("nazivRadnogMesta"));
//            z.setRadnoMesto(rm);
//
//            listaZaposlenih.add(z);
//        }
//        return listaZaposlenih;
//    }

//    public void dodajProjekat(Projekat projekat) throws Exception {
//        try {
//            String upit = "INSERT INTO projekat(nazivProjekta, pocetakRealizacije, rukovodilacId, prioritet, stanje) values (?, ?, ?, ?, ?)";
//            PreparedStatement ps = konekcija.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, projekat.getNazivProjekta());
//            ps.setDate(2, new java.sql.Date(projekat.getPocetakRealizacije().getTime()));
//            ps.setInt(3, projekat.getRukovodilac().getZaposleniId());
//            ps.setString(4, projekat.getPrioritet().toString());
//            ps.setString(5, projekat.getStanje().toString());
//
//            ps.executeUpdate();
//            
//            ResultSet tableKeys = ps.getGeneratedKeys();
//            tableKeys.next();
//            int projekatId = tableKeys.getInt(1);
//            
//            projekat.setProjekatId(projekatId);
//            
//            dodajAngazovenje(projekat);
//            
//            konekcija.commit();
//        } catch (SQLException ex) {
//            konekcija.rollback();
//            throw ex;
//        }
//
//    }

//    public LinkedList<Zaposleni> vratiZaposlenePretraga(String pretragaZaposleni) throws Exception {
//        LinkedList<Zaposleni> vratiZaposlenePretraga = new LinkedList<>();
//        String upit = "SELECT * FROM zaposleni z "
//                + "JOIN organizacionaCelina oc ON z.organizacionaCelinaId = oc.organizacionaCelinaId "
//                + "JOIN radnoMesto rm ON z.radnoMestoId = rm.radnoMestoId "
//                + "WHERE z.ime LIKE ? OR z.prezime LIKE ? OR z.email LIKE ?";
//        PreparedStatement ps = konekcija.prepareStatement(upit);
//        String pojamZaPretragu = '%' + pretragaZaposleni + '%';
//        ps.setString(1, pojamZaPretragu);
//        ps.setString(2, pojamZaPretragu);
//        ps.setString(3, pojamZaPretragu);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            Zaposleni z = new Zaposleni();
//            z.setZaposleniId(rs.getInt("zaposleniId"));
//            z.setIme(rs.getString("ime"));
//            z.setPrezime(rs.getString("prezime"));
//            z.setEmail(rs.getString("email"));
//            z.setDatumZaposlenja(rs.getDate("datumZaposlenja"));
//
//            OrganizacionaCelina oc = new OrganizacionaCelina();
//            oc.setOrganizacionaCelinaId(rs.getInt("organizacionaCelinaId"));
//            oc.setNazivOrganizacioneCeline(rs.getString("nazivOrganizacioneCeline"));
//            z.setOrganizacionaCelina(oc);
//
//            RadnoMesto rm = new RadnoMesto();
//            rm.setRadnoMestoId(rs.getInt("radnoMestoId"));
//            rm.setNazivRadnogMesta(rs.getString("nazivRadnogMesta"));
//            z.setRadnoMesto(rm);
//
//            vratiZaposlenePretraga.add(z);
//        }
//
//        return vratiZaposlenePretraga;
//
//    }

//    public void obrisiZaposlenog(Zaposleni zaposleni1) throws Exception {
//        try {
//            String upit = "DELETE FROM zaposleni WHERE zaposleniId = ?";
//            PreparedStatement ps = konekcija.prepareStatement(upit);
//            ps.setInt(1, zaposleni1.getZaposleniId());
//
//            ps.execute();
//            konekcija.commit();
//        } catch (Exception ex) {
//            konekcija.rollback();
//            throw new Exception("Doslo je do greske prilikom brisanja zaposlenog: " + zaposleni1);
//        }
//
//    }

//    public LinkedList<Projekat> vratiProjekte() throws Exception {
//        LinkedList<Projekat> listaProjekata = new LinkedList<>();
//
//        String upit = "SELECT * FROM projekat p "
//                + "JOIN zaposleni z ON p.rukovodilacId = z.zaposleniID "
//                + "JOIN organizacionacelina oc ON z.organizacionaCelinaId = oc.organizacionaCelinaId "
//                + "JOIN radnomesto rm ON z.radnoMestoId = rm.radnoMestoId";
//        PreparedStatement ps = konekcija.prepareStatement(upit);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//
//            Projekat p = new Projekat();
//            p.setProjekatId(rs.getInt("projekatId"));
//            p.setNazivProjekta(rs.getString("nazivProjekta"));
//            p.setPocetakRealizacije(rs.getDate("pocetakRealizacije"));
//            p.setPrioritet(Prioritet.valueOf(rs.getString("prioritet")));
//            p.setStanje(Stanje.valueOf(rs.getString("stanje")));
//
//            Zaposleni z = new Zaposleni();
//            z.setZaposleniId(rs.getInt("zaposleniId"));
//            z.setIme(rs.getString("ime"));
//            z.setPrezime(rs.getString("prezime"));
//            z.setEmail(rs.getString("email"));
//            z.setDatumZaposlenja(rs.getDate("datumZaposlenja"));
//
//            OrganizacionaCelina oc = new OrganizacionaCelina();
//            oc.setOrganizacionaCelinaId(rs.getInt("organizacionaCelinaId"));
//            oc.setNazivOrganizacioneCeline(rs.getString("nazivOrganizacioneCeline"));
//            z.setOrganizacionaCelina(oc);
//
//            RadnoMesto rm = new RadnoMesto();
//            rm.setRadnoMestoId(rs.getInt("radnoMestoId"));
//            rm.setNazivRadnogMesta(rs.getString("nazivRadnogMesta"));
//            z.setRadnoMesto(rm);
//
//            p.setRukovodilac(z);
//
//            listaProjekata.add(p);
//
//        }
//
//        return listaProjekata;
//
//    }

//    public LinkedList<Projekat> vratiProjektePretraga(String pretragaProjekti) throws Exception {
//        LinkedList<Projekat> vratiProjektePretraga = new LinkedList<>();
//        String upit = "SELECT * FROM projekat p JOIN zaposleni z ON p.rukovodilacId = z.zaposleniID "
//                + "JOIN organizacionacelina oc ON z.organizacionaCelinaId = oc.organizacionaCelinaId "
//                + "JOIN radnomesto rm ON z.radnoMestoId = rm.radnoMestoId "
//                + "WHERE p.nazivProjekta LIKE ? OR p.prioritet LIKE ? OR p.stanje LIKE ?";
//        PreparedStatement ps = konekcija.prepareStatement(upit);
//        String pojamZaPretragu = '%' + pretragaProjekti + '%';
//        ps.setString(1, pojamZaPretragu);
//        ps.setString(2, pojamZaPretragu);
//        ps.setString(3, pojamZaPretragu);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            Projekat p = new Projekat();
//            p.setProjekatId(rs.getInt("projekatId"));
//            p.setNazivProjekta(rs.getString("nazivProjekta"));
//            p.setPocetakRealizacije(rs.getDate("pocetakRealizacije"));
//            p.setPrioritet(Prioritet.valueOf(rs.getString("prioritet")));
//            p.setStanje(Stanje.valueOf(rs.getString("stanje")));
//
//            Zaposleni z = new Zaposleni();
//            z.setZaposleniId(rs.getInt("zaposleniId"));
//            z.setIme(rs.getString("ime"));
//            z.setPrezime(rs.getString("prezime"));
//            z.setEmail(rs.getString("email"));
//            z.setDatumZaposlenja(rs.getDate("datumZaposlenja"));
//
//            OrganizacionaCelina oc = new OrganizacionaCelina();
//            oc.setOrganizacionaCelinaId(rs.getInt("organizacionaCelinaId"));
//            oc.setNazivOrganizacioneCeline(rs.getString("nazivOrganizacioneCeline"));
//            z.setOrganizacionaCelina(oc);
//
//            RadnoMesto rm = new RadnoMesto();
//            rm.setRadnoMestoId(rs.getInt("radnoMestoId"));
//            rm.setNazivRadnogMesta(rs.getString("nazivRadnogMesta"));
//            z.setRadnoMesto(rm);
//
//            p.setRukovodilac(z);
//
//            vratiProjektePretraga.add(p);
//        }
//
//        return vratiProjektePretraga;
//    }

    //Razmoriti i opciju safe delite da mi samo postavlja u Otkazan a ne brisanje. Mada to moze i na izmenu da se racuna
//    public void obrisiProjekat(Projekat projekat1) throws Exception {
//        try {
//            String upit = "DELETE FROM projekat WHERE projekatId = ?";
//            PreparedStatement ps = konekcija.prepareStatement(upit);
//            ps.setInt(1, projekat1.getProjekatId());
//
//            ps.execute();
//            konekcija.commit();
//        } catch (Exception ex) {
//            konekcija.rollback();
//            throw new Exception("Doslo je do greske prilikom brisanja projekta: " + projekat1);
//        }
//    }

//    public void dodajAngazovenje(Projekat projekat) throws Exception {
//        try {
//            String upit = "INSERT INTO angazovanje(zaposleniId, projekatId, pocetakAngazovanja) values (?, ?, ?)";
//            PreparedStatement ps = konekcija.prepareStatement(upit);
//            for (Zaposleni zaposleni : projekat.getZaposleni()) {
//                ps.setInt(1, zaposleni.getZaposleniId());
//                ps.setInt(2, projekat.getProjekatId());
//                ps.setDate(3, new java.sql.Date(projekat.getPocetakRealizacije().getTime()));
//                ps.addBatch();
//            }
//            ps.executeBatch();
//            konekcija.commit();
//
//            azurirajStanjeProjektaNaAktivan(projekat);
//
//        } catch (Exception ex) {
//            konekcija.rollback();
//            throw new Exception("Doslo je do greske prilikom dodavanja angazovanja.");
//        }
//    }

    

//    public LinkedList<Angazovanje> vratiAngazovanja() throws Exception {
//        LinkedList<Angazovanje> listaAngazovanja = new LinkedList<>();
//        String upit = "SELECT * FROM angazovanje a JOIN zaposleni z ON a.zaposleniId = z.zaposleniId "
//                    + "JOIN projekat p ON a.projekatId = p.projekatId order by 1";
//
//        PreparedStatement ps = konekcija.prepareStatement(upit);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            Angazovanje a = new Angazovanje();
//            a.setAngazovanjeId(rs.getInt("angazovanjeId"));
//
//            Projekat p = new Projekat();
//            p.setProjekatId(rs.getInt("projekatId"));
//            p.setNazivProjekta(rs.getString("nazivProjekta"));
//            p.setPocetakRealizacije(rs.getDate("pocetakRealizacije"));
//            p.setPrioritet(Prioritet.valueOf(rs.getString("prioritet")));
//            p.setStanje(Stanje.valueOf(rs.getString("stanje")));
//
//            Zaposleni z = new Zaposleni();
//            z.setZaposleniId(rs.getInt("zaposleniId"));
//            z.setIme(rs.getString("ime"));
//            z.setPrezime(rs.getString("prezime"));
//            z.setEmail(rs.getString("email"));
//            z.setDatumZaposlenja(rs.getDate("datumZaposlenja"));
//
//            p.setRukovodilac(z);
//
//            a.setProjekat(p);
//
//            a.setZaposleni(z);
//
//            a.setPocetakAngazovanja(rs.getDate("pocetakAngazovanja"));
//            a.setKrajAngazovanja(rs.getDate("krajAngazovanja"));
//
//            listaAngazovanja.add(a);
//        }
//
//        return listaAngazovanja;
//
//    }

//    public void dodajAngazovenjeIzAngazovanja(Angazovanje angazovanje) throws Exception {
//        try {
//            String upit = "INSERT INTO angazovanje(zaposleniId, projekatId, pocetakAngazovanja) values (?, ?, ?)";
//            PreparedStatement ps = konekcija.prepareStatement(upit);
//            ps.setInt(1, angazovanje.getZaposleni().getZaposleniId());
//            ps.setInt(2, angazovanje.getProjekat().getProjekatId());
//            ps.setDate(3, new java.sql.Date(angazovanje.getPocetakAngazovanja().getTime()));
//            
//            ps.execute();
//            konekcija.commit();
//
//            azurirajStanjeProjektaNaAktivanZaAngazovanje(angazovanje);
//
//        } catch (Exception ex) {
//            konekcija.rollback();
//            throw new Exception("Doslo je do greske prilikom dodavanja angazovanja.");
//        }
//    }

//    private void azurirajStanjeProjektaNaAktivanZaAngazovanje(Angazovanje angazovanje) throws Exception {
//        try {
//            String upit = "UPDATE projekat SET stanje = 'Aktivan' WHERE projekatId = ?";
//
//            PreparedStatement ps = konekcija.prepareStatement(upit);
//            ps.setInt(1, angazovanje.getProjekat().getProjekatId());
//
//            ps.execute();
//            konekcija.commit();
//        } catch (Exception ex) {
//            konekcija.rollback();
//            throw new Exception("Doslo je do greske prilikom azuriranja projekta: " + angazovanje.getProjekat());
//        }
//    }

    

    

    

    

    

    

    

}
