package prv.jws.tests;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Jerzy Szymanski on 24.09.2020 at 11:58
 */
@Slf4j
public class SimpleTest {
    @Test
    void customTest(){
        List<Boolean> boolean1 = Lists.list(TRUE,TRUE,FALSE,TRUE,FALSE,TRUE);
        List<Boolean> boolean2 = Lists.list(TRUE,TRUE,FALSE,TRUE);
        List<Boolean> boolean3 = Lists.list(TRUE,TRUE,FALSE,FALSE);
        List<Boolean> boolean4 = Lists.list(FALSE,FALSE,FALSE,FALSE);
        List<Boolean> boolean5 = Lists.list(TRUE,TRUE,TRUE,TRUE);

        log.debug("STEP1: 2 false, last item true");
        checkBooleanList(boolean1, FALSE);
        log.debug("STEP2: 1 false, last item true");
        checkBooleanList(boolean2, FALSE);
        log.debug("STEP3: 2 true and 2 false at the end");
        checkBooleanList(boolean3, FALSE);
        log.debug("STEP4: 4 false");
        checkBooleanList(boolean4, FALSE);
        log.debug("STEP5: 4 true");
        checkBooleanList(boolean5, TRUE);
    }

    private void checkBooleanList(final List<Boolean> booleans, Boolean expected) {
        Boolean result = booleans.stream()
                .map(value -> {
                    log.debug("... 1    mapping value >{}<", value);
                    return value;
                })
                .filter(value -> {
                    log.debug("... 2    ... filtering against value >{}<",value);
                    return !value;
                })
                .findAny()
                .map(value -> {
                    log.debug("... 3    ... ... after find any we have >{}<", value);
                    return value;
                })
                .orElse(TRUE);
        log.info("   >>>  Checking booleans, result is {} expected {}",result, expected);
    }
}
