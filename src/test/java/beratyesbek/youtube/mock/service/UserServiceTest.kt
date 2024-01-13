package beratyesbek.youtube.mock.service

import beratyesbek.youtube.mock.UserPrepare
import beratyesbek.youtube.mock.model.User
import beratyesbek.youtube.mock.model.dto.user.UserCreateDTO
import beratyesbek.youtube.mock.model.dto.user.UserReadDTO
import beratyesbek.youtube.mock.model.mapper.UserMapper
import beratyesbek.youtube.mock.repository.UserRepository
import beratyesbek.youtube.mock.service.email.EmailService
import beratyesbek.youtube.mock.service.email.user.UserEmailService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userMapper: UserMapper

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var userPrepare: UserPrepare

    @Mock
    private lateinit var userEmailService: UserEmailService

    @InjectMocks
    private lateinit var userService: UserServiceImpl


    @Test
    fun `create user success test`() {
        val userCreateDto = mock(UserCreateDTO::class.java)
        val user = mock(User::class.java)
        val readDTO = mock(UserReadDTO::class.java)

        `when`(userRepository.existsByEmail(userCreateDto.email)).thenReturn(false)
        `when`(userRepository.existsByPhone(userCreateDto.phone)).thenReturn(false)
        `when`(userPrepare.prepareUserForCreation(userCreateDto)).thenReturn(user)
        doNothing().`when`(userEmailService).sendVerificationEmail(any(), any(), any())
        `when`(userRepository.save(user)).thenReturn(user)
        `when`(userMapper.mapToReadDTO(user)).thenReturn(readDTO)

        val result = userService.create(userCreateDto)

        verify(userRepository, times(1)).existsByEmail(userCreateDto.email)
        verify(userRepository, times(1)).existsByPhone(userCreateDto.email)
        verify(userRepository, times(1)).save(user)

        Assertions.assertEquals(user.email, result.email)
        Assertions.assertEquals(user.phone, result.phone)
    }

    @Test
    fun `create use failure because of duplicate email`() {
        val userCreateDTO = mock(UserCreateDTO::class.java)
        val user = mock(User::class.java)
        val readDTO = mock(UserReadDTO::class.java)

        // Mocking, It returns true when the method is called
        // It is used for testing the failure case
        // It will throw an exception when the method is called
        `when`(userRepository.existsByEmail(userCreateDTO.email)).thenReturn(true)

        `when`(userRepository.existsByPhone(userCreateDTO.phone)).thenReturn(false)
        doNothing().`when`(userEmailService).sendVerificationEmail(any(), any(), any())
        `when`(userPrepare.prepareUserForCreation(userCreateDTO)).thenReturn(user)
        `when`(userRepository.save(user)).thenReturn(user)
        `when`(userMapper.mapToReadDTO(user)).thenReturn(readDTO)


        val exception = Assertions.assertThrows(RuntimeException::class.java) {
            userService.create(userCreateDTO)
        }

        // It will check the method is called or not
        verify(userRepository, never()).save(any())
        Assertions.assertTrue(exception.message?.contains("User already exists with this email") ?: false)

    }

    @Test
    fun `create user failure because of duplicate phone`() {
        val userCreateDTO = mock(UserCreateDTO::class.java)
        val user = mock(User::class.java)
        val readDTO = mock(UserReadDTO::class.java)

        // Mocking, It returns true when the method is called
        // It is used for testing the failure case
        // It will throw an exception when the method is called
        `when`(userRepository.existsByPhone(userCreateDTO.phone)).thenReturn(true)
        `when`(userRepository.existsByEmail(userCreateDTO.email)).thenReturn(false)
        doNothing().`when`(userEmailService).sendVerificationEmail(any(), any(), any())
        `when`(userPrepare.prepareUserForCreation(userCreateDTO)).thenReturn(user)
        `when`(userRepository.save(user)).thenReturn(user)
        `when`(userMapper.mapToReadDTO(user)).thenReturn(readDTO)

        val exception = Assertions.assertThrows(RuntimeException::class.java) {
            userService.create(userCreateDTO)
        }

        // It will check the method is called or not
        verify(userRepository, never()).save(any())
        Assertions.assertTrue(exception.message?.contains("User already exists with this phone") ?: false)
    }
}