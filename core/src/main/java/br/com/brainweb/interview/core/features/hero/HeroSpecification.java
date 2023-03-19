package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.data.jpa.domain.Specification;
import java.util.UUID;

public final class HeroSpecification {
    private HeroSpecification(){
    }

    public static Specification<Hero> idEq(UUID id){
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }


    public static Specification<Hero> nameEq(String name){
        return (root, query, cb) -> cb.equal(cb.upper(root.get("name")), name.toUpperCase());
    }

    public static Specification<Hero> nameFilter(String name){
        return (root, query, cb) -> cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%");
    }
}
