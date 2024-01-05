package beratyesbek.youtube.mock.controller;

import beratyesbek.youtube.mock.model.dto.PageDTO;
import beratyesbek.youtube.mock.model.dto.user.UserCreateDTO;
import beratyesbek.youtube.mock.model.dto.user.UserReadDTO;
import beratyesbek.youtube.mock.model.dto.user.UserUpdateDTO;
import beratyesbek.youtube.mock.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserReadDTO> create(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userService.create(userCreateDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserReadDTO> update(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable String id) {
        return ResponseEntity.ok(userService.update(id, userUpdateDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserReadDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<PageDTO<UserReadDTO>> getAll(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(value = "firstname", required = false) String firstname,
            @RequestParam(value = "lastname", required = false) String lastname

    ) {
        return ResponseEntity.ok(userService.getAll(page, size, firstname, lastname));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
