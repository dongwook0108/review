package me.dongwook.review.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLombokApi {

    @GetMapping("/test/lombok")
    public TestLombokResponseBody testLombok() {
        return new TestLombokResponseBody("fofofo®", 20);
    }

    @AllArgsConstructor
    @Getter
    public static class TestLombokResponseBody{
        String name;
        Integer age;
    }


}
