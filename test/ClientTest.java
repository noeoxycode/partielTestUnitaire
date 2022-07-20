import exception.GlobalException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ClientTest {

    // TODO
    @Test
    public void createClientTest() {
        List<CompteBancaire> myList = new ArrayList<CompteBancaire>();
        Client myTestClient = new Client("test@gmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), myList, true);
        assertEquals("test@gmail.com", myTestClient.getEmail());
        assertEquals("prenomDeTest", myTestClient.getPrenom());
        assertEquals("nomDeTest", myTestClient.getNom());
        assertEquals(LocalDate.of(1995, 4, 2), myTestClient.getDateDeNaissance());
        assertEquals(true, myTestClient.isInterditBancaire());
    }

    @Test
    public void controlBirthDate() {
        List <CompteBancaire> myList = new ArrayList<CompteBancaire>();
        Client myTestClient = new Client("test@gmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), myList, true);
        assertTrue(myTestClient.getDateDeNaissance().isBefore(LocalDate.now()));
    }

    @Test
    public void controlEmailFormat() {
        List <CompteBancaire> myList = new ArrayList<CompteBancaire>();
        Client myTestClient = new Client("test@gmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), myList, true);
        Pattern emailRegex = Pattern.compile("\\b[\\w.-]+@[\\w.-]+\\.\\w{2,4}\\b");
        Matcher emailIsValid = emailRegex.matcher(myTestClient.getEmail());
        assertTrue(emailIsValid.matches());
    }

    @Test
    public void controlEmailFormatBis() {
        List <CompteBancaire> myList = new ArrayList<CompteBancaire>();
        Client myTestClient = new Client("testgmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), myList, true);
        Pattern emailRegex = Pattern.compile("\\b[\\w.-]+@[\\w.-]+\\.\\w{2,4}\\b");
        Matcher emailIsValid = emailRegex.matcher(myTestClient.getEmail());
        assertFalse(emailIsValid.matches());
    }

    @Test(expected = GlobalException.class)
    public void controlCompteValide() throws GlobalException {
        List <CompteBancaire> myList = new ArrayList<CompteBancaire>();
        Client myTestClient = new Client("test@gmail.com", "prenomDeTest", "nomDeTest", LocalDate.of(1995, 4, 2), myList, false);
        myTestClient.verifierSiCompteValide(1234); ;
    }
}
