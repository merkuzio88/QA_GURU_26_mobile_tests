package helpers;

import static io.restassured.RestAssured.given;


public class Browserstack {

    // curl -u "bsuser_mpduwc:8o2TCk438GnhCfmDBTPF" -X GET "https://api-cloud.browserstack.com/app-automate/builds/6730cde087f2d21f02774eed5235873ad564438d/sessions/274886f199e5d647f7984c446e90de43f39fb66b/video"
    // automation_session.video_url

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic("bsuser_mpduwc", "8o2TCk438GnhCfmDBTPF")
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}