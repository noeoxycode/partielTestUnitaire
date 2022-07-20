import exception.GlobalException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class BanquierTest {

    // TODO
    @Test
    public void createBanquierTest() {
        List <Client> myList = new ArrayList<Client>();
        Banquier myTestBanquier = new Banquier("test@gmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), LocalDate.of(2020, 4, 2), myList);
        assertEquals("test@gmail.com", myTestBanquier.getEmail());
        assertEquals("prenomDeTest", myTestBanquier.getPrenom());
        assertEquals(LocalDate.of(2020, 4, 2), myTestBanquier.getDateArrivee());
        assertEquals(LocalDate.of(1995, 4, 2), myTestBanquier.getDateDeNaissance());
        assertEquals(myList, myTestBanquier.getClients());
    }

    @Test
    public void compareBornAndArrived() {
        List <Client> myList = new ArrayList<Client>();
        Banquier myTestBanquier = new Banquier("test@gmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), LocalDate.of(2020, 4, 2), myList);
        assertTrue(myTestBanquier.getDateArrivee().isAfter(myTestBanquier.getDateDeNaissance()));
    }

     @Test
    public void controlBirthDate() {
        List <Client> myList = new ArrayList<Client>();
        Banquier myTestBanquier = new Banquier("test@gmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(2000, 4, 2), LocalDate.of(2020, 4, 2), myList);
        assertTrue(myTestBanquier.getDateDeNaissance().isBefore(LocalDate.now()));
    }

    @Test
    public void cannotContatClient() {
        List <Client> myList = new ArrayList<Client>();
        Banquier myTestBanquier = new Banquier("test@gmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), LocalDate.of(2022, 4, 2), myList);
        assertTrue(myTestBanquier.getDateArrivee().isAfter(myTestBanquier.getDateDeNaissance()));
    }

    @Test
    public void controlEmailFormat() {
        List <Client> myList = new ArrayList<Client>();
        Banquier myTestBanquier = new Banquier("test@gmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), LocalDate.of(2022, 4, 2), myList);
        Pattern emailRegex = Pattern.compile("\\b[\\w.-]+@[\\w.-]+\\.\\w{2,4}\\b");
        Matcher emailIsValid = emailRegex.matcher(myTestBanquier.getEmail());
        assertTrue(emailIsValid.matches());
    }

    @Test
    public void controlEmailFormatBis() {
        List <Client> myList = new ArrayList<Client>();
        Banquier myTestBanquier = new Banquier("testgmailcom", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), LocalDate.of(2022, 4, 2), myList);
        Pattern emailRegex = Pattern.compile("\\b[\\w.-]+@[\\w.-]+\\.\\w{2,4}\\b");
        Matcher emailIsValid = emailRegex.matcher(myTestBanquier.getEmail());
        assertFalse(emailIsValid.matches());
    }

    @Test(expected = GlobalException.class)
    public void controleNouveauBanquier() throws GlobalException {
        List <Client> myList = new ArrayList<Client>();
        Banquier myTestBanquier = new Banquier("testgmailcom", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), LocalDate.now(), myList);
        myTestBanquier.setClients(myTestBanquier.clientsAContacter());;
    }
}
