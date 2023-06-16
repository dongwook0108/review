package me.dongwook.review.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRequestApi {

    // Request Parameter 방식

    @GetMapping("/test/param")
    public String requestParam(
            @RequestParam String name,
            @RequestParam Integer age
    ) {
        return "Hello, Request, I am " + name + ", " + age;
    }

    // Path Variable 방식
    @GetMapping("/test/path/{name}/{age}")
    public String requestPathVariable(
            @PathVariable String name,
            @PathVariable Integer age
    ) {
        return "Hello, Path Variable, I am " + name + ", " + age;
    }

    // RequestBody 방식
    @PostMapping("/test/body")
    public String requestBody(
            @RequestBody TestRequestBody request
    ) {
        return "Hello, Request Body, I am " + request.name + ", " + request.age;
    }

    public static class TestRequestBody {
        String name;
        Integer age;

        public TestRequestBody(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
