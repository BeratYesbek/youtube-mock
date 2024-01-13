package beratyesbek.youtube.mock.service.email

import beratyesbek.youtube.mock.service.email.model.*
import beratyesbek.youtube.mock.service.email.user.UserEmailServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.Mockito.verify
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
class UserEmailServiceTest {
    @Mock
    private lateinit var emailService: EmailService

    @InjectMocks
    private lateinit var userEmailService: UserEmailServiceImpl

    @Captor
    private lateinit var emailRequestCaptor: ArgumentCaptor<EmailRequest>

    @Test
    fun `send verification email successfully`() {
        val email = "berat@gmail.com"
        val firstName = "Berat"
        val lastName = "Yesbek"

        userEmailService.sendVerificationEmail(email, firstName, lastName)

        // It is used to capture the arguments for all successive calls.
        verify(emailService, Mockito.times(1)).send(emailRequestCaptor.capture())

        // Returns the value of the captured argument.
        val capturedEmailRequest = emailRequestCaptor.value
        val capturedContext = capturedEmailRequest.context

        assertEquals(email, capturedEmailRequest.to)
        assertEquals(EmailTemplates.EMAIL_VERIFY, capturedEmailRequest.template)
        assertEquals(EmailSubjects.COMPLETE_YOUR_REGISTRATION, capturedEmailRequest.subject)

        assertEquals(firstName, capturedContext.getVariable("firstName"))
        assertEquals(lastName, capturedContext.getVariable("lastName"))
        assertEquals(EmailMessage.VERIFY_ACCOUNT.message, capturedContext.getVariable("message"))
    }
}