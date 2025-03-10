package main;

import java.io.File;

public class FileManager {
    public File getFileByEnv(String line) {
        try {
            String path = System.getenv(line);
            return new File(path);
        } catch (SecurityException e) {
            System.out.println("Недостаточно прав для чтения файла!");
        } catch (NullPointerException e) {
            System.out.println("Такой переменной не существует!");
        }
        return null;
    }
}
