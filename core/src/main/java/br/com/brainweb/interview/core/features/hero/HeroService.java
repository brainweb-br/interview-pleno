package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.dtos.HeroDto.HeroOut;
import br.com.brainweb.interview.core.dtos.HeroDto.HeroStronger;
import br.com.brainweb.interview.core.exception.NotFoundException;
import br.com.brainweb.interview.model.Hero;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class HeroService {

    @Autowired
    private HeroRepository repository;

    private static final String NOT_FOUND = "Hero não encontrado";
    private static final String POSITIVO = "positivo";
    private static final String NEGATIVO = "negativo";

    @Transactional
    public HeroOut create(final Hero hero) {

        if (validateUnique(hero.getName())) {
            throw new ValidationException("Nome já cadastrado.");
        }

        UUID result = repository.create(hero);

        return findById(result);
    }

    public HeroOut findById(final UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    public List<HeroOut> findByName(final String name) {
        return repository.findByName(name);
    }

    private boolean validateUnique(final String name) {
        return repository.validateUnique(name);
    }

    @Transactional
    public void delete(final UUID id) {
        repository.delete(findById(id).getId());
    }

    @Transactional
    public HeroOut update(final UUID id, final Hero input) {

        Integer result = repository.update(id, input);
        if (result < 1) {
            throw new NotFoundException(NOT_FOUND);
        }
        return findById(id);
    }

    public List<HeroStronger> comparetorHero(final List<String> names) {
        List<HeroOut> heros = repository.listComparator(names);

        if (CollectionUtils.isEmpty(heros)) {
            throw new NotFoundException("Não encontrado heros");
        }

        List<HeroStronger> result = new ArrayList<>();
        HeroStronger one = new HeroStronger();
        HeroStronger two = new HeroStronger();

        one.setId(heros.get(0).getId());
        two.setId(heros.get(1).getId());

        getAgilityResult(heros, one, two);
        getIntelligenceResult(heros, one, two);
        getDexterityResult(heros, one, two);
        getStrength(heros, one, two);

        result.add(one);
        result.add(two);

        return result;
    }

    private void getAgilityResult(List<HeroOut> heros, HeroStronger one, HeroStronger two) {
        Comparator<HeroOut> herosComparator
                = Comparator.comparing(HeroOut::getAgility);

        heros.sort(herosComparator);

        if (heros.get(0).getId().equals(one.getId())) {
            one.setAgility(POSITIVO);
            two.setAgility(NEGATIVO);
        } else {
            one.setAgility(NEGATIVO);
            two.setAgility(POSITIVO);
        }
    }

    private void getIntelligenceResult(List<HeroOut> heros, HeroStronger one, HeroStronger two) {

        Comparator<HeroOut> herosComparator
                = Comparator.comparing(HeroOut::getIntelligence);

        heros.sort(herosComparator);

        if (heros.get(0).getId().equals(one.getId())) {
            one.setIntelligence(POSITIVO);
            two.setIntelligence(NEGATIVO);
        } else {
            one.setIntelligence(NEGATIVO);
            two.setIntelligence(POSITIVO);
        }
    }

    private void getDexterityResult(List<HeroOut> heros, HeroStronger one, HeroStronger two) {

        Comparator<HeroOut> herosComparator
                = Comparator.comparing(HeroOut::getDexterity);

        heros.sort(herosComparator);

        if (heros.get(0).getId().equals(one.getId())) {
            one.setDexterity(POSITIVO);
            two.setDexterity(NEGATIVO);
        } else {
            one.setDexterity(NEGATIVO);
            two.setDexterity(POSITIVO);
        }
    }

    private void getStrength(List<HeroOut> heros, HeroStronger one, HeroStronger two) {

        Comparator<HeroOut> herosComparator
                = Comparator.comparing(HeroOut::getStrength);
        heros.sort(herosComparator);

        if (heros.get(0).getId().equals(one.getId())) {
            one.setStrength(POSITIVO);
            two.setStrength(NEGATIVO);
        } else {
            one.setStrength(NEGATIVO);
            two.setStrength(POSITIVO);
        }
    }

}
