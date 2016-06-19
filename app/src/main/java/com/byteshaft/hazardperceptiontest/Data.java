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
        VideoPlayActivity.setUpQuestionsData(sQuestionsArray, sAnswerHashMap);
    }

    public static void addQuestionsData() {
        sQuestionsArray.add("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow down");
        sAnswerHashMap.put("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow down",
                new String[] {"4550", "10700", "question_1"});

        sQuestionsArray.add("You are stopped." +
                "You wish to turn right at the intersection. " +
                "Click the 'RESPOND NOW' button when it would be safe to go.");
        sAnswerHashMap.put("You are stopped." +
                        "You wish to turn right at the intersection. " +
                        "Click the 'RESPOND NOW' button when it would be safe to go.",
                new String[] {"12850", "15180", "question_2"});

        sQuestionsArray.add("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow down.");
        sAnswerHashMap.put("You are travelling along a two-way road in a 50km/hour speed zone. " +
                        "Click the 'RESPOND NOW' button when you would slow down.",
                new String[] {"9700", "12850", "question_3"});

        sQuestionsArray.add("You are stopped. " +
                "You wish to turn right at the intersection. " +
                "Click the 'RESPOND NOW' button when it would be safe to go.");
        sAnswerHashMap.put("You are stopped. " +
                        "You wish to turn right at the intersection. " +
                        "Click the 'RESPOND NOW' button when it would be safe to go.",
                new String[] {AppGlobals.NO_RESPONSE, AppGlobals.NO_RESPONSE, "question_4"});

        sQuestionsArray.add("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow  down.");
        sAnswerHashMap.put("You are travelling along a two-way road in a 50km/hour speed zone. " +
                        "Click the 'RESPOND NOW' button when you would slow  down.",
                new String[] {"6270", "11470", "question_5"});

        sQuestionsArray.add("You are travelling along a two-way road in a 80km/hour speed zone. " +
                "Click the 'RESPOND NOW' button when you would slow down.");
        sAnswerHashMap.put("You are travelling along a two-way road in a 80km/hour speed zone. " +
                        "Click the 'RESPOND NOW' button when you would slow down.",
                new String[] {"1000", "20000", "question_6"});

        sQuestionsArray.add("You are travelling along a two-way road in a 50km/hour speed zone. " +
                "You want to keep driving straight ahead. " +
                "Click the 'RESPOND NOW' button when you would slow down.");
        sAnswerHashMap.put("You are travelling along a two-way road in a 50km/hour speed zone. " +
                        "You want to keep driving straight ahead. " +
                        "Click the 'RESPOND NOW' button when you would slow down.",
                new String[] {"3000", "8540", "question_7"});
    }
}
