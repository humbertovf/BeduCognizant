import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class Technology implements Serializable {
    static ArrayList<Technology> data;
    int id;
    String name;
    String slug;
    String description;
    public Technology(String name, String slug, String description){
        this.id = data.size()+1;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }
    public Technology addTechnology() {
        data.add(this);
        return this;
    }

    public void saveTechnology(String name, String slug, String description) {
        if (!name.equals(""))
            this.name = name;
        if (!slug.equals(""))
            this.slug = slug;
        if (!description.equals(""))
            this.description = description;
        data.add(this);
    }

    public void deleteTechnology() throws Exception{
        Technology technology = Technology.getTechnology(this.name);
        if (technology != null) {
            data.remove(this);
            Interviewer.saveDataToFile();
        }
        else
            throw new Exception("InterviewType not found");
    }

    public static Technology getTechnology(String name) {
        for (Technology technology : data) {
            if (technology.name.equals(name))
                return technology;
        }

        return null;
    }

    public static void saveDataToFileTechnology() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./technology");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(Interviewer.data);
            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}