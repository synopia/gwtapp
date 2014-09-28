package org.synopia.gwtapp.transfer.client.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.putnami.pwt.core.mvp.client.MvpPlace;
import fr.putnami.pwt.core.mvp.client.ViewProxy;

/**
 * @author synopia
 */
@Singleton
public class TransferPlace extends MvpPlace {
//    @Inject
//    TransferPresenter presenter;

    @Inject
    public TransferPlace() {
        super(new ViewProxy(){
            @Override
            public void getView(Callback callback) {
                callback.showView(null);
            }

        }, null);
    }

    @Override
    public MvpPlace getPlace(String token) {
        return this;
    }
}
