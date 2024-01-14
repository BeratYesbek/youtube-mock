package beratyesbek.youtube.mock.integrationtest

import beratyesbek.youtube.mock.controller.UserController
import beratyesbek.youtube.mock.model.dto.user.UserCreateDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ActiveProfiles("test-containers")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserControllerIntegrationTest {

    @Autowired
    private lateinit var userController: UserController

    @Test
    fun `create user successful`() {
        val userCreateDTO = UserCreateDTO.builder()
            .email("berat@gmail.com")
            .firstname("Berat")
            .lastname("Yesbek")
            .phone("1234563443")
            .password("123456")
            .build()
        val result = userController.create(userCreateDTO)

        Assertions.assertNotNull(result)
        Assertions.assertNotNull(result.body)
        Assertions.assertNotNull(result.body?.id)
        Assertions.assertEquals(result.statusCode, HttpStatus.OK)
        Assertions.assertEquals(result.body?.email, userCreateDTO.email)
        Assertions.assertEquals(result.body?.firstname, userCreateDTO.firstname)
        Assertions.assertEquals(result.body?.lastname, userCreateDTO.lastname)
        Assertions.assertEquals(result.body?.phone, userCreateDTO.phone)


    }


}