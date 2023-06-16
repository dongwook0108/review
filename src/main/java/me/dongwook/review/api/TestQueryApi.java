package me.dongwook.review.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.dongwook.review.model.TestEntity;
import me.dongwook.review.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestQueryApi {

    private final TestService testService;

    @GetMapping("/test/query/jpa")
    public List<TestEntity> queryJpa() {
        return testService.findAllByNameByJpa("dongwook");
    }

    @GetMapping("/test/query/querydsl")
    public List<TestEntity> querydsl() {
        return testService.findAllByNameByQuerydsl("dongwook");
    }
}
