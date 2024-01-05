package beratyesbek.youtube.mock.model.mapper;


import beratyesbek.youtube.mock.model.User;
import beratyesbek.youtube.mock.model.dto.PageDTO;
import beratyesbek.youtube.mock.model.dto.user.UserCreateDTO;
import beratyesbek.youtube.mock.model.dto.user.UserReadDTO;
import beratyesbek.youtube.mock.model.dto.user.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    @Named("mapToReadDTO")
    UserReadDTO mapToReadDTO(User user);

    @Named("mapToEntity")
    User mapToEntity(UserCreateDTO userCreateDTO);

    @Named("mapForUpdate")
    void mapForUpdate(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

    @Named("mapToReadDTOList")
    List<UserReadDTO> mapToReadDTOList(List<User> users);


    @Named("mapToPageDTO")
    @Mapping(target = "content", source = "content", qualifiedByName = "mapToReadDTOList")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "page", source = "pageable.pageNumber")
    @Mapping(target = "size", source = "pageable.pageSize")
    PageDTO<UserReadDTO> mapToPageDTO(Page<User> page);


}
