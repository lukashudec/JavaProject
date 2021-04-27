package EXPERIMENTAL_API_OBJECT_MODEL;
import io.restassured.RestAssured;

import io.restassured.response.Response;


public class AbstractGet {
        public Response response;

        String ENDPOINT;
        String URL;

        public AbstractGet(String ENDPOINT, String URL) {
            this.ENDPOINT = ENDPOINT;
            this.URL = URL;
        }

        public Response get(String input) {
            response = RestAssured
                    .given().baseUri(ENDPOINT).basePath(URL)
                    .get(input);
            return response;
        }
}
