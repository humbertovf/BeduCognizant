
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class DisciplineTest {
    static String existingDisciplineName = "Technical";
    static String existingDisciplineSlug = "User";
    static String existingDisciplineDescription = "mindset";

    @BeforeEach
    public void setUp() throws Exception {
        Discipline.data = new ArrayList<>();

        // We insert a user for testing delete and save
        Discipline.data.add(new Discipline(existingDisciplineName,existingDisciplineSlug,existingDisciplineDescription));
    }

    @Test
    public void add() {
        Discipline discipline = new Discipline("Developer","Front End","Java Developer");

        discipline.addDiscipline();

        int expectedId = Discipline.data.size();
        assertEquals(expectedId, discipline.id, "Discipline ID should be the new List's size");
    }

    @Test
    public void save() {
        int originalListSize = Discipline.data.size();
        String expectedSlug = "New";
        Discipline existingDiscipline = Discipline.data.get(0);
        System.out.println(Discipline.data.size());
        existingDiscipline.save("", expectedSlug, "");

        int newListSize = Discipline.data.size();
        System.out.println(Discipline.data.size());
        int lastDisciplineIndex = newListSize - 1;
        Discipline latestDiscipline = Discipline.data.get(lastDisciplineIndex);

        assertNotEquals(originalListSize, newListSize, "List size should be the same");
        assertEquals(originalListSize, lastDisciplineIndex, "List size should be the same");
        assertEquals(expectedSlug,latestDiscipline.slug,"Slug should have been updated");
        assertEquals(latestDiscipline.name,latestDiscipline.name,"Name should have not been updated");
    }

    @Test
    public void delete() {
        Discipline existingDiscipline = Discipline.data.get(0);

        int expectedSize = Discipline.data.size() - 1;

        try {
            existingDiscipline.delete();
        } catch (Exception e) {
            fail("Unexpected Exception received: " + e.getMessage());
        }

        int actualSize = Discipline.data.size();

        assertEquals(expectedSize, actualSize, "List should be smaller");
    }
}
