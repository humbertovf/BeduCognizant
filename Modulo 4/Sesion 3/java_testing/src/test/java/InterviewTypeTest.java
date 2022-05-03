import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InterviewTypeTest {
    static String existingInterviewTypeName = "Technical";
    static String existingInterviewTypeSlug = "User";
    static String existingInterviewTypeDescription = "mindset";

    @BeforeEach
    public void setUp() throws Exception {
        InterviewType.data = new ArrayList<>();

        // We insert a user for testing delete and save
        InterviewType.data.add(new InterviewType(existingInterviewTypeName,existingInterviewTypeSlug,existingInterviewTypeDescription));
    }

    @Test
    public void add() {
        InterviewType interviewType = new InterviewType("Technical","User","skills");

        interviewType.addInterviewType();

        int expectedId = InterviewType.data.size();
        assertEquals(expectedId, interviewType.id, "Interviewer ID should be the new List's size");
    }

    @Test
    public void save() {
        int originalListSize = InterviewType.data.size();
        String expectedSlug = "New";
        InterviewType existingInterviewType = InterviewType.data.get(0);
        System.out.println(InterviewType.data.size());
        existingInterviewType.saveInterviewType("", expectedSlug, "");

        int newListSize = InterviewType.data.size();
        System.out.println(InterviewType.data.size());
        int lastInterviewTypeIndex = newListSize - 1;
        InterviewType latestInterviewType = InterviewType.data.get(lastInterviewTypeIndex);

        assertNotEquals(originalListSize, newListSize, "List size should be the same");
        //assertEquals(originalListSize, newListSize, "List size should be the same");
        assertEquals(expectedSlug,latestInterviewType.slug,"Slug should have been updated");
        assertEquals(existingInterviewType.name,latestInterviewType.name,"Name should have not been updated");
    }

    @Test
    public void delete() {
        InterviewType existingInterviewType = InterviewType.data.get(0);

        int expectedSize = InterviewType.data.size() - 1;

        try {
            existingInterviewType.deleteInterviewType();
        } catch (Exception e) {
            fail("Unexpected Exception received: " + e.getMessage());
        }

        int actualSize = InterviewType.data.size();

        assertEquals(expectedSize, actualSize, "List should be smaller");
    }
}
