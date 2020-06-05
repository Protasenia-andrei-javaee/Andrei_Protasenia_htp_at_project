package runners;

import utills.JsonParser;

import java.io.IOException;

public class JSONParser {
    public static void main(String[] args) throws IOException {
        JsonParser jsonParser = new JsonParser();
       jsonParser.parseGson();
    }
}
