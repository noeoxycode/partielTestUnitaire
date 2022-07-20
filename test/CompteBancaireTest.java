import exception.CreditException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CompteBancaireTest {

    // TODO
    @Test
    public void compteBanquaireCredite() throws CreditException {
        CompteBancaire newCb = new CompteBancaire();
        newCb.setMontant(50);
        CompteRenduOperation newCompteRendu;
        newCompteRendu = newCb.credit(50);
        assertEquals(100, newCompteRendu.getNouveauSolde());
    }

    @Test (expected = CreditException.class)
    public void mauvaisMontantAcrediter() throws CreditException {
        CompteBancaire newCb = new CompteBancaire();
        newCb.setMontant(0);
        CompteRenduOperation newCompteRendu;
        newCompteRendu = newCb.credit(-20);
    }

    @Test
    public void compteBanquaireDebite() throws CreditException {
        CompteBancaire newCb = new CompteBancaire();
        newCb.setMontant(100);
        CompteRenduOperation newCompteRendu;
        newCompteRendu = newCb.debit(50);
        assertEquals(50, newCompteRendu.getNouveauSolde());
    }

    @Test
    public void compteBanquaireDebiteNegatif() throws CreditException {
        CompteBancaire newCb = new CompteBancaire();
        newCb.setMontant(50);
        CompteRenduOperation newCompteRendu;
        newCompteRendu = newCb.debit(100);
    }

    @Test (expected = CreditException.class)
    public void mauvaisMontant() throws CreditException {
        CompteBancaire newCb = new CompteBancaire();
        newCb.setMontant(0);
        CompteRenduOperation newCompteRendu;
        newCompteRendu = newCb.credit(-20);
    }

    @Test
    public void comptePasValide(){
        CompteBancaire newCb = new CompteBancaire();
        newCb.estValide();
        newCb.setMontant(-20);
        assertEquals(false, newCb.estValide());
    }

    @Test
    public void compteValide(){
        CompteBancaire newCb = new CompteBancaire();
        newCb.estValide();
        newCb.setMontant(50);
        assertEquals(true, newCb.estValide());
    }

}
