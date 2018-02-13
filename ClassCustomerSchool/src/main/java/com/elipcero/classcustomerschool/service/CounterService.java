package com.elipcero.classcustomerschool.service;

import com.elipcero.classcustomerschool.domain.Counter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class CounterService {

    @NonNull private final MongoOperations mongo;

    private static long CONST_ID = 1;

    public long count() {
        Counter counter = this.mongo.findAndModify(
                query(where("_id").is(CONST_ID)),
                new Update().inc("count", 1),
                new FindAndModifyOptions().returnNew(true),
                Counter.class);


        // Production environments should be done before and not within code
        // above all when there are several instances running at the same time
        if (counter == null) {
            counter = Counter.builder().id(CONST_ID).count(0).build();
            this.mongo.insert(counter);
        }

        return counter.getCount();
    }
}
