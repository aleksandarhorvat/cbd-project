package org.example.libraryservice.proxy;

import org.example.libraryservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserProxy {

    @GetMapping("/users/{id}")
    UserDto getUser(@PathVariable("id") Integer userId);

}
