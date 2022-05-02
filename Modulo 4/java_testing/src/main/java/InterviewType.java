import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class InterviewType implements Serializable {
    static ArrayList<InterviewType> data;
    int id;
    String name;
    String slug;
    String description;
    public InterviewType(String name, String slug, String description){
        this.id = data.size()+1;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }
    public InterviewType addInterviewType() {
        data.add(this);
        return this;
    }
    public void saveInterviewType(String name, String slug, String description) {
        if (!name.equals(""))
            this.name = name;
        if (!slug.equals(""))
            this.slug = slug;
        if (!description.equals(""))
            this.description = description;
        data.add(this);
    }

    public void deleteInterviewType() throws Exception{
        InterviewType interviewType = InterviewType.getInterviewType(this.name);
        if (interviewType != null) {
            data.remove(this);
            Interviewer.saveDataToFile();
        }
        else
            throw new Exception("InterviewType not found");
    }

    public static InterviewType getInterviewType(String name) {
        for (InterviewType interviewType : data) {
            if (interviewType.name.equals(name))
                return interviewType;
        }

        return null;
    }

    public static void saveDataToFileInterviewType() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./interviewType");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(Interviewer.data);
            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}