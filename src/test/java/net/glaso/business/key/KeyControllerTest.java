package net.glaso.business.key;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KeyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createKeyStoreTestSuccess() throws Exception {
        // GIVEN
        String keyStoreName = "test-key-store-001";
        String json = objectMapper.writeValueAsString(Collections.singletonMap("keyStoreName", keyStoreName));

        log.info("json msg: " + json);

        // WHEN, THEN
        MvcResult mvcResult = mockMvc.perform(post("/key/key-store")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_ENCODING,  "UTF-8")
                .content(json)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

        log.info("HTTP Body: " + mvcResult.getResponse().getContentAsString());

    }

}
