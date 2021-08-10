package net.glaso.key;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.glaso.key.domain.EnumKeyType;
import net.glaso.key.domain.Key;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KeyControllerTest_createKey {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String keySecretUri = "/key/secret";

    @Test
    public void createKeyStoreTestFail_KeyType_null() throws Exception {
        // GIVEN
        Key key = new Key();
        key.setKeyName("test-key-store-001");
        key.setConvergentEncryption(true);
        key.setExportable(true);

        String json = objectMapper.writeValueAsString(key);

        log.info("HTTP Request Body(JSON): " + json);

        // WHEN, THEN
        mockMvc.perform(post(keySecretUri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_ENCODING,  "UTF-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createKeyStoreTestSuccess() throws Exception {
        // GIVEN
        Key key = new Key();
        key.setKeyName("test-key-store-001");
        key.setConvergentEncryption(true);
        key.setExportable(true);
        key.setKeyType(EnumKeyType.AES_256);

        String json = objectMapper.writeValueAsString(key);

        log.info("HTTP Request Body(JSON): " + json);

        // WHEN, THEN
        mockMvc.perform(post(keySecretUri)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_ENCODING,  "UTF-8")
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());


    }
}
