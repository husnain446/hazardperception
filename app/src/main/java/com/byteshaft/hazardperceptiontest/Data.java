package com.byteshaft.hazardperceptiontest;


import com.byteshaft.hazardperceptiontest.utils.AppGlobals;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {

    private static ArrayList<String> sQuestionsArray;
    private static HashMap<String, String[]> sAnswerHashMap;


    public static void initDataForTest() {
        sQuestionsArray = new ArrayList<>();
        sAnswerHashMap = new HashMap<>();
        addQuestionsData();
        TestActivity.setUpQuestionsData(sQuestionsArray, sAnswerHashMap);
    }

    public static void addQuestionsData() {
        sQuestionsArray.add("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow down");
        sAnswerHashMap.put("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow down",
                new String[] {"4.55", "10.7", "question_1"});

        sQuestionsArray.add("You are stopped. " +
                "You wish to turn right at the intersection. " +
                "Click the 'RESPOND NOW' button when it would be safe to go.");
        sAnswerHashMap.put("You are stopped. " +
                        "You wish to turn right at the intersection. " +
                        "Click the 'RESPOND NOW' button when it would be safe to go.",
                new String[] {"12.85", "15.18", "question_2"});

        sQuestionsArray.add("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow down.");
        sAnswerHashMap.put("You are travelling along a two-way road in a 50km/hour speed zone. " +
                        "Click the 'RESPOND NOW' button when you would slow down.",
                new String[] {"9.7", "12.85", "question_3"});

        sQuestionsArray.add("You are stopped. " +
                "You wish to turn right at the intersection. " +
                "Click the 'RESPOND NOW' button when it would be safe to go.");
        sAnswerHashMap.put("You are stopped. " +
                        "You wish to turn right at the intersection. " +
                        "Click the 'RESPOND NOW' button when it would be safe to go.",
                new String[] {AppGlobals.NO_RESPONSE, "", "question_4"});

        sQuestionsArray.add("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow  down.");
        sAnswerHashMap.put("You are travelling along a two-way road in a 50km/hour speed zone. " +
                        "Click the 'RESPOND NOW' button when you would slow  down.",
                new String[] {"6.27", "11.47", "question_5"});

        sQuestionsArray.add("You are travelling along a two-way road in a 80km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow down.");
        sAnswerHashMap.put("You are travelling along a two-way road in a 80km/hour speed zone. " +
                        "Click the 'RESPOND NOW' button when you would slow down.",
                new String[] {"1", "20", "question_6"});

        sQuestionsArray.add("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "You want to keep driving straight ahead. " +
                "Click the 'RESPOND NOW' button when you would slow down.");
        sAnswerHashMap.put("You are travelling along a two-way road in a 50km/hour speed zone. " +
                        "You want to keep driving straight ahead. " +
                        "Click the 'RESPOND NOW' button when you would slow down.",
                new String[] {"3", "8.54", "question_7"});
    }
}
