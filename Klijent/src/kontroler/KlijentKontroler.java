/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Administrator;
import domen.Angazovanje;
import domen.OrganizacionaCelina;
import domen.Projekat;
import domen.RadnoMesto;
import domen.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import transfer.KlijentskiZahtev;
import transfer.Operacije;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Mihajlo
 */
public class KlijentKontroler {
//    private static KlijentKontroler instanca;
//    Socket s;
//    Administrator ulogovani;
//    
//    private KlijentKontroler() throws IOException {
//        s = new Socket("localhost", 10001);
//    }
//
//    public static KlijentKontroler getInstanca() throws IOException {
//        if(instanca == null){
//            instanca = new KlijentKontroler();
//        }
//        return instanca;
//    }
//
//    public Administrator getUlogovani() {
//        return ulogovani;
//    }
//
//    public void setUlogovani(Administrator ulogovani) {
//        this.ulogovani = ulogovani;
//    }
//    
//    private Object posaljiZahtev(int operacija, Object parametar) throws Exception{
//        KlijentskiZahtev kz = new KlijentskiZahtev(operacija, parametar);
//        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//        oos.writeObject(kz);
//        
//        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//        ServerskiOdgovor so = (ServerskiOdgovor) ois.readObject();
//        if(so.getGreska() != null){
//            throw so.getGreska();
//        } else {
//          return so.getOdgovor();
//        }
//    }
    
//    public Administrator login(Administrator administrator) throws Exception {
//        return (Administrator) posaljiZahtev(Operacije.LOGIN, administrator);
//    }
//
//    public LinkedList<OrganizacionaCelina> vratiOrganizacioneCeline() throws Exception {
//        return (LinkedList<OrganizacionaCelina>) posaljiZahtev(Operacije.VRATI_ORGANIZACIONE_CELINE, null);
//    }
//
//    public LinkedList<RadnoMesto> vratiRadnaMesta(int organizacionaCelinaId) throws Exception {
//        return (LinkedList<RadnoMesto>) posaljiZahtev(Operacije.VRATI_RADNA_MESTA, organizacionaCelinaId);
//    }
//
//    public void dodajZaposlenog(Zaposleni zaposleni) throws Exception {
//        posaljiZahtev(Operacije.DODAJ_ZAPOSLENOG, zaposleni);
//    }
//
//    public LinkedList<Zaposleni> vratiZaposlene() throws Exception {
//        return (LinkedList<Zaposleni>) posaljiZahtev(Operacije.VRATI_ZAPOSLENE, null);
//    }

//    public void dodajProjekat(Projekat projekat) throws Exception {
//        posaljiZahtev(Operacije.DODAJ_PROJEKAT, projekat);
//    }

//    public LinkedList<Zaposleni> pronadjiPaVratiZaposlene(String pretraga) throws Exception {
//        return (LinkedList<Zaposleni>) posaljiZahtev(Operacije.VRATI_ZAPOSLENE_PRETRAGA, pretraga);
//    }
//
//    public void obrisiZaposlenog(Zaposleni zaposleni) throws Exception {
//        posaljiZahtev(Operacije.OBRISI_ZAPOSLENOG, zaposleni);
//    }

//    public LinkedList<Projekat> vratiProjekte() throws Exception {
//        return (LinkedList<Projekat>) posaljiZahtev(Operacije.VRATI_PROJEKTE, null);
//    }
//
//    public LinkedList<Projekat> pronadjiPaVratiProjekte(String pretraga) throws Exception {
//        return (LinkedList<Projekat>) posaljiZahtev(Operacije.VRATI_PROJEKTE_PRETRAGA, pretraga);
//    }
//
//    public void obrisiProjekat(Projekat projekat) throws Exception {
//        posaljiZahtev(Operacije.OBRISI_PROJEKAT, projekat);
//    }

//    public void dodajAngazovanje(Angazovanje angazovanje) throws Exception {
//        posaljiZahtev(Operacije.DODAJ_ANGAZOVANJE, angazovanje);
//    }
//
//    public LinkedList<Angazovanje> vratiAngazovanja() throws Exception {
//        return (LinkedList<Angazovanje>) posaljiZahtev(Operacije.VRATI_ANGAZOVANJA, null);
//    }
    
    
    
}
