import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TechnologyTest {
    static String existingTechnologyName = "Technical";
    static String existingTechnologySlug = "User";
    static String existingTechnologyDescription = "mindset";

    @BeforeEach
    public void setUp() throws Exception {
        Technology.data = new ArrayList<>();

        // We insert a user for testing delete and save
        Technology.data.add(new Technology(existingTechnologyName,existingTechnologySlug,existingTechnologyDescription));
    }

    @Test
    public void add() {
        Technology technology = new Technology("Software","User","Development");

        technology.addTechnology();

        int expectedId = Technology.data.size();
        assertEquals(expectedId, technology.id, "Technology ID should be the new List's size");
    }

    @Test
    public void save() {
        int originalListSize = Technology.data.size();
        String expectedSlug = "New";
        Technology existingTechnology = Technology.data.get(0);
        System.out.println(Technology.data.size());
        existingTechnology.saveTechnology("", expectedSlug, "");

        int newListSize = Technology.data.size();
        System.out.println(Technology.data.size());
        int lastTechnologyIndex = newListSize - 1;
        Technology latestTechnology = Technology.data.get(lastTechnologyIndex);

        assertNotEquals(originalListSize, newListSize, "List size shouldn't be the same");
        assertEquals(originalListSize, lastTechnologyIndex, "List size should be the same");
        assertEquals(expectedSlug,latestTechnology.slug,"Slug should have been updated");
        assertEquals(existingTechnology.name,latestTechnology.name,"Name should have not been updated");
    }

    @Test
    public void delete() {
        Technology existingTechnology = Technology.data.get(0);

        int expectedSize = Technology.data.size() - 1;

        try {
            existingTechnology.deleteTechnology();
        } catch (Exception e) {
            fail("Unexpected Exception received: " + e.getMessage());
        }

        int actualSize = Technology.data.size();

        assertEquals(expectedSize, actualSize, "List should be smaller");
    }
}
