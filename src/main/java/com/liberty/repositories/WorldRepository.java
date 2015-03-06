package com.liberty.repositories;

import com.liberty.domain.World;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface WorldRepository extends GraphRepository<World> {

}
