import exception.CreditException;
import org.junit.Assert;
import org.junit.Test;

public class CompteBancaireTest {
    private CompteBancaire compteBancaire = new CompteBancaire();

    @Test
    public void testCreditNominal() throws CreditException {
        this.compteBancaire.setMontant(800);
        CompteRenduOperation op = this.compteBancaire.credit(50);
        Assert.assertEquals(850, op.getNouveauSolde());
        Assert.assertEquals(50, op.getMontantCredite());
        Assert.assertEquals(0, op.getMontantNonCredite());
        Assert.assertEquals(0, op.getMontantDebite());
        Assert.assertEquals(0, op.getMontantNonDebite());
    }

    @Test
    public void testPartialCreditMaxReached() throws CreditException {
        this.compteBancaire.setMontant(800);
        CompteRenduOperation op = this.compteBancaire.credit(300);
        Assert.assertEquals(1000, op.getNouveauSolde());
        Assert.assertEquals(200, op.getMontantCredite());
        Assert.assertEquals(100, op.getMontantNonCredite());
        Assert.assertEquals(0, op.getMontantDebite());
        Assert.assertEquals(0, op.getMontantNonDebite());
    }

    @Test
    public void testDebitNominal() throws CreditException {
        this.compteBancaire.setMontant(800);
        CompteRenduOperation op = this.compteBancaire.debit(200);
        Assert.assertEquals(600, op.getNouveauSolde());
        Assert.assertEquals(200, op.getMontantDebite());
        Assert.assertEquals(0, op.getMontantNonDebite());
        Assert.assertEquals(0, op.getMontantCredite());
        Assert.assertEquals(0, op.getMontantNonCredite());
    }

    @Test
    public void testPartialDebitMinReached() throws CreditException {
        this.compteBancaire.setMontant(300);
        CompteRenduOperation op = this.compteBancaire.debit(500);
        Assert.assertEquals(0, op.getNouveauSolde());
        Assert.assertEquals(300, op.getMontantDebite());
        Assert.assertEquals(200, op.getMontantNonDebite());
        Assert.assertEquals(0, op.getMontantCredite());
        Assert.assertEquals(0, op.getMontantNonCredite());
    }
}
