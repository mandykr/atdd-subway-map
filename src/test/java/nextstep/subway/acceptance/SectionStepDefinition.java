package nextstep.subway.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SectionStepDefinition {
    public static Map<String, String> 지하철_구간_파라미터_생성(
            ExtractableResponse<Response> upStation,
            ExtractableResponse<Response> downStation,
            int distance) {
        HashMap<String, String> params = new HashMap<>();
        params.put("upStationId", upStation.body().jsonPath().getString("id"));
        params.put("downStationId", downStation.body().jsonPath().getString("id"));
        params.put("distance", String.valueOf(distance));

        return params;
    }

    public static ExtractableResponse<Response> 지하철_구간_생성_요청(
            ExtractableResponse<Response> lineResponse,
            Map<String, String> params) {
        return RestAssured
                .given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/lines/{id}/sections", lineResponse.body().jsonPath().getLong("id"))
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 지하철_구간_삭제_요청(
            ExtractableResponse<Response> lineResponse,
            ExtractableResponse<Response> station) {
        return RestAssured
                .given().log().all()
                .param("stationId", station.body().jsonPath().getString("downStationId"))
                .when()
                .delete("/lines/{id}/sections", lineResponse.body().jsonPath().getLong("id"))
                .then().log().all()
                .extract();
    }

    public static void 지하철_구간_응답_상태_검증(ExtractableResponse<Response> response, HttpStatus status) {
        assertThat(response.statusCode()).isEqualTo(status.value());
    }

    public static void 지하철_구간_생성_완료(ExtractableResponse<Response> response) {
        지하철_구간_응답_상태_검증(response, HttpStatus.CREATED);
    }

    public static void 지하철_구간_생성_실패(ExtractableResponse<Response> response) {
        지하철_구간_응답_상태_검증(response, HttpStatus.BAD_REQUEST);
    }

    public static void 지하철_구간_삭제_완료(ExtractableResponse<Response> response) {
        지하철_구간_응답_상태_검증(response, HttpStatus.NO_CONTENT);
    }

    public static void 지하철_구간_삭제_실패(ExtractableResponse<Response> response) {
        지하철_구간_응답_상태_검증(response, HttpStatus.BAD_REQUEST);
    }
}
