package ru.job4j.task;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateJson {

    public static void main(String[] args) {
        String testsPath = args[0];
        String valuesPath = args[1];
        try {
            Map<Long, String> testsResults = new HashMap<>();
            FileReader readVal = new FileReader(valuesPath);
            JSONParser jsonValues = new JSONParser();
            JSONObject jsObjVal = (JSONObject) jsonValues.parse(readVal);
            JSONArray val = (JSONArray) jsObjVal.get("values");
            for (int i = 0; i < val.size(); i++) {
                JSONObject result = (JSONObject) val.get(i);
                testsResults.put((Long) result.get("id"), (String) result.get("value"));
            }

            FileReader readTest = new FileReader(testsPath);
            JSONParser jsonTests = new JSONParser();
            JSONObject jsObjTest = (JSONObject) jsonTests.parse(readTest);

            addValue(jsObjTest, "tests", testsResults);
            FileWriter file = new FileWriter("report.json");
            file.write(jsObjTest.toJSONString());
            file.flush();
            file.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void addValue(JSONObject y, String name, Map<Long, String> testsResults) {
        JSONArray jsArr = (JSONArray) y.get(name);
        if (jsArr != null) {
            for (int i = 0; i < jsArr.size(); i++) {
                JSONObject newObj = (JSONObject) jsArr.get(i);
                if (testsResults.containsKey(newObj.get("id"))) {
                    newObj.put("value", testsResults.get(newObj.get("id")));
                }
                addValue(newObj, "values", testsResults);
            }
        }
    }
}
