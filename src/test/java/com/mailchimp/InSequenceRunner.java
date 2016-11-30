package com.mailchimp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * Sort the tests with {@link InSequence} and sets the tests with no InSequence defined as first.
 *
 * @author stevensnoeijen
 */
public class InSequenceRunner extends BlockJUnit4ClassRunner {

    public InSequenceRunner(Class clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected List computeTestMethods() {
        //super returns an unmodified list and need to be copied to able be sorted
        List<FrameworkMethod> tests = new ArrayList<>(super.computeTestMethods());
        Collections.sort(tests, (FrameworkMethod o1, FrameworkMethod o2) -> {
            //if both are InSequence sort
            if (o1.getAnnotation(InSequence.class) != null
                    && o2.getAnnotation(InSequence.class) != null) {
                int o1Order = o1.getAnnotation(InSequence.class).value();
                int o2Order = o2.getAnnotation(InSequence.class).value();

                return Integer.compare(o1Order, o2Order);
            } else if (o1.getAnnotation(InSequence.class) != null) {//check if one InSequence
                return 1;
            } else if (o2.getAnnotation(InSequence.class) != null) {
                return -1;
            } else {
                //none
                return 0;//do this as last
            }
        });
        return tests;
    }
}
