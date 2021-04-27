package EXPERIMENTAL_API_OBJECT_MODEL;

import java.util.HashMap;
import java.util.Map;

public class targetAPI {
    String ENDPOINT = "https://jsonplaceholder.typicode.com";
    String URL = "/posts/";

    private AbstractGet get = new AbstractGet(ENDPOINT, URL);
    private AbstractPost post = new AbstractPost(ENDPOINT, URL);


    public targetAPI get(String input) {
        get.get(input);
        return this;
    }

    public targetAPI post(String title, String body, String userId) {
        HashMap<String, String> reqBody = new HashMap<>();
        reqBody.put("title", title);
        reqBody.put("body", body);
        reqBody.put("userID", userId);
        post.post(reqBody);
        return this;
    }
}
