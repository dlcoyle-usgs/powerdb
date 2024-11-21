package gov.usgs.vrm.retrievals;

import java.net.http.HttpResponse;

public class ResponseObject {
    public RequestObject getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(RequestObject requestObject) {
        this.requestObject = requestObject;
    }

    RequestObject requestObject = null;

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    String responseBody = null;

    public HttpResponse<String> getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HttpResponse<String> httpResponse) {
        this.httpResponse = httpResponse;
    }

    HttpResponse<String> httpResponse = null;
    public String toString() {
        return
                //String.format("requestObject=[%s], ", this.requestObject.toString()) +
                String.format("response-status=[%s], ", httpResponse.statusCode()) +
                String.format("response-size=[%s]", this.responseBody.length());
    }
}
