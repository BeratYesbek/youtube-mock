package beratyesbek.youtube.mock.service;



import beratyesbek.youtube.mock.model.dto.PageDTO;
import beratyesbek.youtube.mock.model.dto.user.UserCreateDTO;
import beratyesbek.youtube.mock.model.dto.user.UserReadDTO;
import beratyesbek.youtube.mock.model.dto.user.UserUpdateDTO;

import java.util.List;

public interface UserService {
    UserReadDTO create(UserCreateDTO userCreateDTO);

    UserReadDTO update(String id, UserUpdateDTO userUpdateDTO);

    UserReadDTO getById(String id);

    PageDTO<UserReadDTO> getAll(Integer page, Integer size, String firstname, String lastname);

    void delete(String id);


}
