import exception.GlobalException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class ClientTest {

    private Client client;
    private CompteBancaire compteBancaire;

    @Before
    public void setUp() {
        this.compteBancaire = new CompteBancaire();
        this.compteBancaire.setId(1);
        this.client = new Client("test@test.fr",
                "testPrenom",
                "testNom",
                LocalDate.now().minusYears(21),
                Arrays.asList(this.compteBancaire),
                false);
    }

    @Test
    public void testClientCompteValide() throws GlobalException {
        Assert.assertTrue(this.client.verifierSiCompteValide(1));
    }

    @Test(expected = GlobalException.class)
    public void testClientCompteInexistant() throws GlobalException {
        Assert.assertTrue(this.client.verifierSiCompteValide(2));
    }

    @Test
    public void testClientMontantDuCompteInvalide() throws GlobalException {
        this.compteBancaire.setMontant(-1);
        Assert.assertFalse(this.client.verifierSiCompteValide(1));
    }
}
