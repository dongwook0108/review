package me.dongwook.review.repository;

import java.util.List;
import me.dongwook.review.model.TestEntity;

public interface TestRepositoryCustom {

    public List<TestEntity> findAllByNameByQuerydsl(String name);
}
