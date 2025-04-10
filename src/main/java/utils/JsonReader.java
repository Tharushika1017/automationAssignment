package utils;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    public static <T> T getTestData(String filePath, Class<T> clazz) throws IOException {
        FileReader reader = new FileReader(filePath);
        Gson gson = new Gson();
        T data = gson.fromJson(reader, clazz);
        reader.close();
        return data;
    }
}
