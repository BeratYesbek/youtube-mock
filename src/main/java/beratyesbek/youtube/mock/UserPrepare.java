package beratyesbek.youtube.mock;

import beratyesbek.youtube.mock.model.User;
import beratyesbek.youtube.mock.model.dto.user.UserCreateDTO;
import beratyesbek.youtube.mock.model.mapper.UserMapper;
import beratyesbek.youtube.mock.util.EncryptionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPrepare {

    private final UserMapper userMapper;
    private final EncryptionHelper encryptionHelper;

    public User prepareUserForCreation(UserCreateDTO userCreateDTO) {
        User user = userMapper.mapToEntity(userCreateDTO);
        prepareSensitiveData(user);
        return user;
    }

    private void prepareSensitiveData(User user) {
        user.setEmail(encryptionHelper.encrypt(user.getEmail()));
        user.setPhone(encryptionHelper.encrypt(user.getPhone()));
    }
}
