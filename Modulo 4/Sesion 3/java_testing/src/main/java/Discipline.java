import java.io.*;
import java.util.ArrayList;

public class Discipline implements Serializable {
    static ArrayList<Discipline> data;
    int id;
    String name;
    String slug;
    String description;

    public Discipline (String name, String slug, String description){
        this.id = data.size() + 1;
        this.name = name;
        this.description = description;
        this.slug = slug;
    }

    public Discipline addDiscipline() {
        data.add(this);
        Discipline.saveDataToFileDiscipline();
        return this;
    }

    public void save(String name,String slug,String description) {
        if (!name.equals(""))
            this.name = name;
        if (!slug.equals(""))
            this.slug = slug;
        if (!description.equals(""))
            this.description = description;
        data.add(this);
    }

    public void delete() throws Exception{
        Discipline discipline = Discipline.getDiscipline(this.name);
        if (discipline != null) {
            data.remove(this);
            Discipline.saveDataToFileDiscipline();
        }
        else
            throw new Exception("Discipline not found");
    }

    public static Discipline getDiscipline(String name) {
        for (Discipline discipline: data) {
            if (discipline.name.equals(name))
                return discipline;
        }

        return null;
    }
    public static void saveDataToFileDiscipline() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./disciplines");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(Discipline.data);
            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

