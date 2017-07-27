package feign.error;

import feign.Response;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static feign.Feign.configKey;

public abstract class AbstractAnnotationErrorDecoderTest<T> {



    public abstract Class<T> interfaceAtTest();

    String feignConfigKey(String methodName) throws NoSuchMethodException {
        return configKey(interfaceAtTest(), interfaceAtTest().getMethod(methodName));
    }

    Response testResponse(int status) {
        return testResponse(status, "default Response body");
    }

    Response testResponse(int status, String body) {
        return testResponse(status, body, new HashMap<String, Collection<String>>() );
    }

    Response testResponse(int status, String body, Map<String, Collection<String>> headers) {
        return Response.builder()
            .status(status)
            .body(body, Charset.forName("UTF-8"))
            .headers(headers)
            .build();
    }
}
