package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.data.jpa.domain.Specification;

public final class HeroSpecification {
    private HeroSpecification(){
    }

    public static Specification<Hero> idEq(String id){
        return (root, cq, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Hero> nameEq(String name){
        return (root, query, cb) -> cb.equal(cb.upper(root.get("name")), name.toUpperCase());
    }

    public static Specification<Hero> nameFilter(String name){
        return (root, query, cb) -> cb.like(cb.upper(root.get("name")), name.toUpperCase());
    }
}
