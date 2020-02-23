package br.com.brainweb.interview.utils;

import br.com.brainweb.interview.model.enums.StatusAttribute;

public class CompareHeroUtil {
    public StatusAttribute compare(int attributeHero1, int attributehero2) {
        if (attributeHero1 < attributehero2) {
            return StatusAttribute.NEGATIVE;
        }else if(attributeHero1 == attributehero2){
            return StatusAttribute.EQUAL;
        }else{
            return StatusAttribute.POSITIVE;
        }
    }

}
