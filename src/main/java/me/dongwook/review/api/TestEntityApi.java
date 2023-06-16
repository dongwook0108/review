package me.dongwook.review.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.dongwook.review.service.TestService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestEntityApi {

    private final TestService testService;

    @PostMapping("/test/entity/create")
    public void createTestEntity(
            @RequestBody CreateTestEntityRequest request
    ) {
        testService.create(request.getName(), request.getAge());
    }

    @DeleteMapping("/test/entity/{id}")
    public void deleteTestEntity(@PathVariable Long id) {
        testService.delete(id);
    }

    @PutMapping("/test/entity/{id}")
    public void putTestEntity(
            @PathVariable Long id,
            @RequestBody CreateTestEntityRequest request
    ) {
        testService.update(id, request.getName(), request.getAge());
    }

    @Getter
    @AllArgsConstructor
    public static class CreateTestEntityRequest {

        private final String name;
        private final Integer age;
    }
}
