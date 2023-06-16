package me.dongwook.review.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.dongwook.review.model.TestEntity;
import me.dongwook.review.repository.TestRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public void create(String name, Integer age) {
        TestEntity testEntity = new TestEntity(name, age);
        testRepository.save(testEntity);
    }

    public void delete(Long id) {
        testRepository.delete(testRepository.findById(id).get());
    }

    public void update(Long id, String name, Integer age) {
        TestEntity testEntity = testRepository.findById(id).orElseThrow();
        testEntity.changeNameAndAge(name, age);
        testRepository.save(testEntity);
    }

    public List<TestEntity> findAllByNameByJpa(String name) {
        return testRepository.findAllByName(name);
    }

    public List<TestEntity> findAllByNameByQuerydsl(String name) {
        return testRepository.findAllByNameByQuerydsl(name);
    }

}
