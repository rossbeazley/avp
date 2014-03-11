package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.android.application.DependencyInjectors;

/**
* Created with IntelliJ IDEA.
* User: beazlr02
* Date: 11/03/2014
* Time: 17:06
* To change this template use File | Settings | File Templates.
*/
public class InjectableResultsFragmentInjector implements DependencyInjectors.Injector<InjectableResultsFragment> {
    @Override
    public void inject(InjectableResultsFragment object) {
        object.injectFragmentScreenFactory(new ResultsFragmentScreenFactory());
    }
}
