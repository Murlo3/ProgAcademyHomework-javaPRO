package gvv;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@SaveTo(path = "AAAAA.txt")
public class TextContainer {
    private String a = "strstr";

    @Saver
    public void saveTextInFile (String path) {
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
