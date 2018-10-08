package magiclink;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class ApplicationTests {

  @Autowired
  MockMvc mvc;

  @MockBean
  Mailer mockedMailer;

  @Test
  public void sendTokenAndLogin() throws Exception {
    // Post Email Address
    mvc.perform(post("/login").param("email", "testuser@localhost"))
        .andExpect(status().isOk())
        .andExpect(content().string("Email with token has been sent"));

    // Capture token from mocked Mailer
    ArgumentCaptor<String> tokenCaptor = ArgumentCaptor.forClass(String.class);
    verify(mockedMailer).sendMagicLinkEmail(eq("testuser@localhost"), tokenCaptor.capture());
    String token = tokenCaptor.getValue();
    assertNotNull("Token should not be null", token);
    assertTrue("Token should be longer than 8 chars", token.length() > 8);

    // Verify login with captured token
    mvc.perform(get("/login/{token}", token))
        .andExpect(status().isOk())
        .andExpect(content().string("You are now logged in as testuser@localhost"));
  }

  @Test
  public void rejectWrongToken() throws Exception {
    String token = "WRONG-TOKEN";
    mvc.perform(get("/login/{token}", token))
        .andExpect(status().isUnauthorized())
        .andExpect(content().string("Invalid token"));
  }


}