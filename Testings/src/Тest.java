import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;

import com.javarush.task.task31.task3110.*;
/**
 * Created by Данил on 26.02.2017.
 */
public class Тest {


    public static void main (String[] args) throws Exception {
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get("c:/tmp/test2.zip"));
List<Path> filesForDelete = new ArrayList<>();
filesForDelete.add(Paths.get("temp/3.txt"));
filesForDelete.add(Paths.get("temp/5.txt"));
zipFileManager.removeFiles(filesForDelete);

    }
}