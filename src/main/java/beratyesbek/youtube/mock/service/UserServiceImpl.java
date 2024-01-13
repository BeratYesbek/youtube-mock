package beratyesbek.youtube.mock.service;

import beratyesbek.youtube.mock.UserPrepare;
import beratyesbek.youtube.mock.model.User;
import beratyesbek.youtube.mock.model.dto.PageDTO;
import beratyesbek.youtube.mock.model.dto.user.UserCreateDTO;
import beratyesbek.youtube.mock.model.dto.user.UserReadDTO;
import beratyesbek.youtube.mock.model.dto.user.UserUpdateDTO;
import beratyesbek.youtube.mock.model.mapper.UserMapper;
import beratyesbek.youtube.mock.repository.UserRepository;
import beratyesbek.youtube.mock.service.email.user.UserEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPrepare userPrepare;
    private final UserEmailService userEmailService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserReadDTO create(UserCreateDTO userCreateDTO) {
        checkPhoneExists(userCreateDTO.getPhone());
        checkEmailExists(userCreateDTO.getEmail());
        final User user = userPrepare.prepareUserForCreation(userCreateDTO);
        userRepository.save(user);
        userEmailService.sendVerificationEmail(user.getEmail(), user.getFirstname(), user.getLastname());
        return userMapper.mapToReadDTO(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserReadDTO update(String id, UserUpdateDTO userUpdateDTO) {
        final User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("USER_NOT_FOUND")
        );
        userRepository.saveAndFlush(user);
        return userMapper.mapToReadDTO(user);
    }

    @Override
    public UserReadDTO getById(String id) {
        return userMapper.mapToReadDTO(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("USER_NOT_FOUND")
        ));
    }

    @Override
    public PageDTO<UserReadDTO> getAll(Integer page, Integer size, String firstname, String lastname) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size), firstname, lastname);
        return userMapper.mapToPageDTO(users);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    private void checkPhoneExists(String phone) {
        if (Boolean.TRUE.equals(userRepository.existsByPhone(phone))) {
            throw new RuntimeException("User already exists with this phone");
        }
    }

    private void checkEmailExists(String email) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(email))) {
            throw new RuntimeException("User already exists with this email");
        }
    }

}
