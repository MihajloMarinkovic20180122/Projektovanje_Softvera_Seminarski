/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemske.operacije.projekti;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import domen.Projekat;
import domen.Zaposleni;
import java.util.LinkedList;
import sistemske.operacije.SOOpsteIzvrsenje;

/**
 *
 * @author Mihajlo
 */
public class SOUcitajProjekat extends SOOpsteIzvrsenje{

    Projekat ucitaniProjekat;
    
    @Override
    public boolean proveriOgranicenja(OpstiDomenskiObjekat odo) throws Exception {
        //provera da li je prosledjeni objekat instanca klase Projekat
        if (odo instanceof Projekat projekat) {
            if (projekat.getNazivProjekta() == null 
                || projekat.getPocetakRealizacije() == null
                || projekat.getRukovodilac() == null
                || projekat.getPrioritet() == null 
                || projekat.getStanje() == null) {
                return false;   
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean izvrsiSO(OpstiDomenskiObjekat odo) throws Exception {
        ucitaniProjekat = (Projekat) dbb.ucitaj(odo).get(0);
        LinkedList<Angazovanje> listaAngazovanjaZaProjekat = dbb.vratiAngazovanjaProjekta(ucitaniProjekat);
        LinkedList<Zaposleni> listaZaposlenihNaProjektu = new LinkedList<>();
            for (Angazovanje angazovanje : listaAngazovanjaZaProjekat) {
                Zaposleni z = new Zaposleni();
                z.setZaposleniId(angazovanje.getZaposleni().getZaposleniId());
                z.setIme(angazovanje.getZaposleni().getIme());
                z.setPrezime(angazovanje.getZaposleni().getPrezime());
                z.setEmail(angazovanje.getZaposleni().getEmail());
                z.setDatumZaposlenja(angazovanje.getZaposleni().getDatumZaposlenja());
                z.setOrganizacionaCelina(angazovanje.getZaposleni().getOrganizacionaCelina());
                z.setRadnoMesto(angazovanje.getZaposleni().getRadnoMesto());
                listaZaposlenihNaProjektu.add(z);
        }
        ucitaniProjekat.setZaposleni(listaZaposlenihNaProjektu);
        return true;
    }

    public Projekat getUcitaniProjekat() {
        return ucitaniProjekat;
    }
    
    
}