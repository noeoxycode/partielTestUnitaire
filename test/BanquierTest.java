import exception.GlobalException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BanquierTest {

    private Banquier banquier;
    private Client client1;
    private Client client2;
    private CompteBancaire compteBancaireNonValide;

    @Before
    public void setUp() {
        this.compteBancaireNonValide = new CompteBancaire();
        this.compteBancaireNonValide.setMontant(-1);

        this.client1 = new Client("test1@test.fr",
                "testPrenom1",
                "testNom1",
                LocalDate.now().minusYears(30),
                Arrays.asList(new CompteBancaire(), new CompteBancaire()),
                false);
        this.client2 = new Client("test2@test.fr",
                "testPrenom2",
                "testNom2",
                LocalDate.now().minusYears(40),
                Arrays.asList(new CompteBancaire(), this.compteBancaireNonValide),
                true);

        this.banquier = new Banquier("banquier@test.fr", "banquierPrenom", "banquierNom",
                LocalDate.now().minusYears(50), LocalDate.now().minusMonths(6), Arrays.asList(this.client1, this.client2));
    }

    @Test
    public void testAucunClientAContacter() throws GlobalException {
        this.banquier.setClients(Arrays.asList(this.client1));
        List<Client> clientAContacter = this.banquier.clientsAContacter();
        Assert.assertTrue(clientAContacter.isEmpty());
    }

    @Test
    public void testUnClientAContacter() throws GlobalException {
        this.banquier.setClients(Arrays.asList(this.client1, this.client2));
        List<Client> clientAContacter = this.banquier.clientsAContacter();
        Assert.assertFalse(clientAContacter.isEmpty());
        Assert.assertEquals("test2@test.fr", clientAContacter.get(0).getEmail());
    }
}
