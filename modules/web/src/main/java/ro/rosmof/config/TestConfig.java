package ro.rosmof.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.rosmof.model.Splitter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TestConfig implements WebMvcConfigurer {

    /**
     * This is an excelent example on how to implement your own message converters.
     * This can be used, for example, to send a base64 response to the client.js, or an encrypted
     * response.
     * <p>
     * Created at 27 december 2018, by Sorin
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new HttpMessageConverter<Object>() {
            @Override
            public boolean canRead(Class<?> clazz, MediaType mediaType) {
                return clazz.getName().contains("AResp");
            }

            @Override
            public boolean canWrite(Class<?> clazz, MediaType mediaType) {
                return clazz.getName().contains("AResp");
            }

            @Override
            public List<MediaType> getSupportedMediaTypes() {
                return Arrays.asList(MediaType.ALL);
            }

            @Override
            public Object read(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
                return null;
            }

            @Override
            public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                outputMessage.getBody().write("cucubau".getBytes());
            }
        });

        /**
         * This is not required as i have included jackson in the classpath.Still i keep it here as
         * another reference on how to implement a message converter.
         *
         **/
        converters.add(new HttpMessageConverter<List<Splitter>>() {
            @Override
            public boolean canRead(Class<?> clazz, MediaType mediaType) {
                return false;
            }

            @Override
            public boolean canWrite(Class<?> clazz, MediaType mediaType) {
                return clazz.equals(ArrayList.class) && mediaType.isCompatibleWith(MediaType.APPLICATION_JSON);
            }

            @Override
            public List<MediaType> getSupportedMediaTypes() {
                return Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8);
            }

            @Override
            public List<Splitter> read(Class<? extends List<Splitter>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
                InputStreamReader reader = new InputStreamReader(inputMessage.getBody());
                return null;
            }

            @Override
            public void write(List<Splitter> splitters, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                ObjectMapper mapper = new ObjectMapper();
                outputMessage.getBody().write(mapper.writeValueAsString(splitters).getBytes());
            }
        });
    }


}
