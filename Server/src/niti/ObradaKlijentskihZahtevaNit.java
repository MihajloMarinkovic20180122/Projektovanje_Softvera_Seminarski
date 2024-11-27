/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import com.sun.source.tree.TryTree;
import domen.Administrator;
import domen.Angazovanje;
import domen.OrganizacionaCelina;
import domen.Projekat;
import domen.RadnoMesto;
import domen.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.ServerKontroler;
import transfer.KlijentskiZahtev;
import transfer.Operacije;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Mihajlo
 */
public class ObradaKlijentskihZahtevaNit extends Thread{

    Socket s;
    ServerskaNit sn;
    Administrator administrator;
    LinkedList<Administrator> ulogovani;

//    public ObradaKlijentskihZahtevaNit(Socket s, ServerskaNit sn, LinkedList<Administrator> ulogovani) {
//        this.s = s;
//        this.sn = sn;
//        this.ulogovani = ulogovani;
//    }
    
    public ObradaKlijentskihZahtevaNit(Socket s, ServerskaNit sn) {
        this.s = s;
        this.sn = sn;
    }
    
    @Override
    public void run() {
        try {
            while (s != null && !s.isClosed()) {

                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    KlijentskiZahtev kz = (KlijentskiZahtev) ois.readObject();
                    ServerskiOdgovor so = obradiZahtev(kz);
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(so);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        administrator = null;
    }

    private ServerskiOdgovor obradiZahtev(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try{
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    Administrator administratorPokusaj = (Administrator) kz.getParametar();
                    sn.mozeDaSeUloguje(administratorPokusaj);
                    administrator = ServerKontroler.getInstanca().login(administratorPokusaj);
                    if(administrator != null){
                        so.setOdgovor(administrator);
                    }
                    else {
                        throw new SecurityException("Pogresni kredencijali. Ponovite unos.");
                    }
                    break;
                case Operacije.LOGOUT:
                    //zatvoriSoket();
                    administrator = null;
                    //s.close();
                    break;
                case Operacije.VRATI_ORGANIZACIONE_CELINE:
                    LinkedList<OrganizacionaCelina> listaOrganizacionihCelina = ServerKontroler.getInstanca().vratiOrganizacioneCeline();
                    if(listaOrganizacionihCelina == null){
                        throw new Exception("Doslo je do greske pri ucitavanju Organizacionih Celina.");
                    } else {
                        so.setOdgovor(listaOrganizacionihCelina);
                    }
                    break;
                case Operacije.VRATI_RADNA_MESTA:
                    OrganizacionaCelina organizacionaCelina = (OrganizacionaCelina) kz.getParametar();
                    LinkedList<RadnoMesto> listaRadnihMesta = ServerKontroler.getInstanca().vratiRadnaMesta(organizacionaCelina);
                    if(listaRadnihMesta == null){
                        throw new Exception("Doslo je do greske pri ucitavanju Radnih Mesta.");
                    } else {
                        so.setOdgovor(listaRadnihMesta);
                    }
                    break;
                case Operacije.DODAJ_ZAPOSLENOG:
                    Zaposleni zaposleni = (Zaposleni) kz.getParametar();
                    boolean uspesnoSacuvanZaposleni = ServerKontroler.getInstanca().dodajZaposlenog(zaposleni);
                    if(!uspesnoSacuvanZaposleni){
                        throw new Exception("Zaposleni sa tim Email vec postoji.");
                    }
                    break;
                case Operacije.VRATI_ZAPOSLENE:
                    LinkedList<Zaposleni> listaZaposlenih = ServerKontroler.getInstanca().vratiZaposlene();
                    if(listaZaposlenih == null){
                        throw new Exception("Doslo je do greske pri ucitavanju svih zaposlenih.");
                    } else {
                        so.setOdgovor(listaZaposlenih);
                    }
                    break;
                case Operacije.UCITAJ_ZAPOSLENOG:
                    Zaposleni zaposleniZaUcitavanje = (Zaposleni) kz.getParametar();
                    Zaposleni ucitaniZaposleni = ServerKontroler.getInstanca().ucitajZaposlenog(zaposleniZaUcitavanje);
                    if(ucitaniZaposleni == null){
                        throw new Exception("Doslo je do greske pri ucitavanju odabranog zaposlenog.");
                    } else {
                        so.setOdgovor(ucitaniZaposleni);
                    }
                    break;
                case Operacije.OBRISI_ZAPOSLENOG:
                    Zaposleni zaposleniZaObrisati = (Zaposleni) kz.getParametar();
                    boolean uspesnoObrisanZaposleni = ServerKontroler.getInstanca().obrisiZaposlenog(zaposleniZaObrisati);
                    if(!uspesnoObrisanZaposleni){
                        throw new Exception("Doslo je do greske prilikom brisanja zaposlenog!");
                    }
                    break;
                case Operacije.VRATI_ZAPOSLENE_PRETRAGA:
                    Zaposleni pretragaZaposleni = (Zaposleni) kz.getParametar();
                    LinkedList<Zaposleni> listaZaposlenihPretraga = ServerKontroler.getInstanca().vratiZaposlenePretraga(pretragaZaposleni);
                    if(listaZaposlenihPretraga == null){
                        throw new Exception("Doslo je do greske pri pretrazi zaposlenih.");
                    } else {
                        so.setOdgovor(listaZaposlenihPretraga);
                    }
                    break;
                case Operacije.IZMENI_PODATKE_ZAPOSLENOG:
                    Zaposleni izmenjeniZaposleni = (Zaposleni) kz.getParametar();
                    boolean uspesnoIzmenjenZaposleni = ServerKontroler.getInstanca().izmeniPodatkeZaposlenog(izmenjeniZaposleni);
                    if(!uspesnoIzmenjenZaposleni){
                        throw new Exception("Doslo je do greske prilikom izmene zaposlenog!");
                    }
                    break;
                case Operacije.DODAJ_PROJEKAT:
                    Projekat projekat = (Projekat) kz.getParametar();
                    boolean uspesnoSacuvanProjekat = ServerKontroler.getInstanca().dodajProjekat(projekat);
                    if(!uspesnoSacuvanProjekat){
                        throw new Exception("Doslo je do greske prilikom čuvanja projekta!");
                    }
                    break;
                case Operacije.VRATI_PROJEKTE:
                    LinkedList<Projekat> listaProjekata = ServerKontroler.getInstanca().vratiProjekte();
                    if(listaProjekata == null){
                        throw new Exception("Doslo je do greske pri ucitavanju svih projekata.");
                    } else {
                        so.setOdgovor(listaProjekata);
                    }
                    break;
//                case Operacije.VRATI_PROJEKTE_PRETRAGA:
//                    String pretragaProjekti = (String) kz.getParametar();
//                    LinkedList<Projekat> listaProjekataPretraga = ServerKontroler.getInstanca().vratiProjektePretraga(pretragaProjekti);
//                    so.setOdgovor(listaProjekataPretraga);
//                    break;
//                case Operacije.OBRISI_PROJEKAT:
//                    Projekat projekat1 = (Projekat) kz.getParametar();
//                    ServerKontroler.getInstanca().obrisiProjekat(projekat1);
//                    break;
//                case Operacije.DODAJ_ANGAZOVANJE:
//                    Angazovanje angazovanje = (Angazovanje) kz.getParametar();
//                    ServerKontroler.getInstanca().dodajAngazovanje(angazovanje);
//                    break;
//                case Operacije.VRATI_ANGAZOVANJA:
//                    LinkedList<Angazovanje> listaAngazovanja = ServerKontroler.getInstanca().vratiAngazovanja();
//                    so.setOdgovor(listaAngazovanja);
//                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception ex){
            so.setGreska(ex);
        }
        return so;
    }

    public void zatvoriSoket(){
        try {
            if (s != null && !s.isClosed()) {
                administrator = null;
                s.close();
            }
        } catch (Exception ex) {
        }
    }

    public Administrator getAdministrator() {
        return administrator;
    }
    
    
}
