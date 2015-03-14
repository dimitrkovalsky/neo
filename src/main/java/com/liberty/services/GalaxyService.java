package com.liberty.services;


import com.liberty.domain.World;
import com.liberty.repositories.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class GalaxyService {

    @Autowired
    private WorldRepository worldRepository;

    @Autowired
    private Neo4jTemplate template;

    public long getNumberOfWorlds() {
        return worldRepository.count();
    }

    public World createWorld(String name, int moons) {
        return new World(name, moons);
    }

    public void save(World world){
        worldRepository.save(world);
    }

    public Iterable<World> getAllWorlds() {
        System.out.println("Find all");
        return worldRepository.findAll();
    }


    public World findWorldById(Long id) {
        return worldRepository.findOne(id);
    }

    // This is using the schema based index
    public World findWorldByName(String name) {
        return worldRepository.findBySchemaPropertyValue("name", name);
    }

    // This is using the legacy index
    public Iterable<World> findAllByNumberOfMoons(int numberOfMoons) {
        return worldRepository.findAllByPropertyValue("moons", numberOfMoons);
    }

    public WorldRepository getWorldRepository() {
        return worldRepository;
    }

    public void setWorldRepository(WorldRepository worldRepository) {
        this.worldRepository = worldRepository;
    }

    public Collection<World> makeSomeWorlds() {
        Collection<World> worlds = new ArrayList<World>();

        // Solar worlds
        worlds.add(createWorld("Mercury", 0));
        worlds.add(createWorld("Venus", 0));

        World earth = createWorld("Earth", 1);
        World mars = createWorld("Mars", 2);
        mars = mars.addRocketRouteTo(earth);
     //   worldRepository.save(mars);
        worlds.add(earth);
        worlds.add(mars);

        World jupiter = createWorld("Jupiter", 63).addRocketRouteTo(mars);
        World saturn = createWorld("Saturn", 62).addRocketRouteTo(mars).addRocketRouteTo(jupiter);
        World uranus = createWorld("Uranus", 27).addRocketRouteTo(jupiter).addRocketRouteTo(earth);
        World neptune = createWorld("Neptune", 13).addRocketRouteTo(uranus);
        World alfheimr = createWorld("Alfheimr", 0);
        World midgard = createWorld("Midgard", 1).addRocketRouteTo(earth).addRocketRouteTo(alfheimr);
        World muspellheim = createWorld("Muspellheim", 2);
        World asgard = createWorld("Asgard", 63).addRocketRouteTo(midgard);


        worlds.add(jupiter);
        worlds.add(saturn);
        worlds.add(uranus);
        worlds.add(neptune);


        // Norse worlds
        worlds.add(alfheimr);
        worlds.add(midgard);
        worlds.add(muspellheim);
        worlds.add(asgard);
        World hel = createWorld("Hel", 62);
        worlds.add(hel);

        for(World w : worlds) {
            System.out.println("Created : " + w);
            save(w);
        }
        return worlds;
    }

    public Neo4jTemplate getTemplate() {
        return template;
    }

    public void setTemplate(Neo4jTemplate template) {
        this.template = template;
    }
}
