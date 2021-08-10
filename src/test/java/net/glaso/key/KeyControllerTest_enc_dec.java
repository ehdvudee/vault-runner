package net.glaso.key;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.glaso.key.domain.CryptoParam;
import net.glaso.key.domain.Key;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KeyControllerTest_enc_dec {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String keySecretUri = "/keys";
    private final String encryptUri = "/keys/%s/encrypt";

    @Test
    public void encryptDataSuccess() throws Exception {
        String keyIdentifier = this.createKey();
        String ciphertext = encrypt("d6e0bf4d-e121-401a-84aa-70c89c098186");
    }

    private String encrypt(String keyIdentifier) throws Exception {
        // GIVEN
        // $keyIdentifier
        String uri = String.format(encryptUri, keyIdentifier);
        CryptoParam cryptoParam = new CryptoParam();
        cryptoParam.setBase64Plaintext(DatatypeConverter.printBase64Binary("test".getBytes(StandardCharsets.UTF_8)));

        String reqJson = objectMapper.writeValueAsString(cryptoParam);
        // WHEN, THEN
        MvcResult mvcResult = mockMvc.perform(post(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_ENCODING,  "UTF-8")
                .content(reqJson)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

        String httpRespBody = mvcResult.getResponse().getContentAsString();
        JSONObject respJson = new JSONObject(httpRespBody);
        log.info("HTTP Response Body: " + httpRespBody);

        return respJson.getString("ciphertext");
    }

    private String createKey() throws Exception {
        // GIVEN
        Key key = new Key();
        key.setKeyName("test-key-store-001");
        key.setConvergentEncryption(true);
        String json = objectMapper.writeValueAsString(key);

        log.info("HTTP Request Body(JSON): " + json);

        // WHEN, THEN
        MvcResult mvcResult = mockMvc.perform(post(keySecretUri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_ENCODING,  "UTF-8")
                .content(json)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

        String httpRespBody = mvcResult.getResponse().getContentAsString();
        Key respKey = objectMapper.readValue(httpRespBody, Key.class);

        return respKey.getKeyTag();
    }

}
