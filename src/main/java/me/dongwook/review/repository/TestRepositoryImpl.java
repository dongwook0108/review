package me.dongwook.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.dongwook.review.model.QTestEntity;
import me.dongwook.review.model.TestEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TestRepositoryImpl implements TestRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TestEntity> findAllByNameByQuerydsl(String name) {
        return queryFactory.selectFrom(QTestEntity.testEntity)
                .fetch();
    }
}
