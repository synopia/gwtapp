package org.synopia.gwtapp.transfer.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import fr.putnami.pwt.core.mvp.client.MvpPlace;
import fr.putnami.pwt.core.mvp.client.View;
import fr.putnami.pwt.core.widget.client.binder.UiBinderLocalized;

import java.util.logging.Logger;

/**
 * @author synopia
 */
public class TransferPresenter extends Composite implements View<MvpPlace>{
    Logger logger = Logger.getLogger("TransferPresenter");

    interface Binder extends UiBinderLocalized<HTMLPanel, TransferPresenter> {
        Binder BINDER = GWT.create(Binder.class);
    }

//    @Inject
//    MvpController mvpController;

    public TransferPresenter() {
        initWidget(Binder.BINDER.createAndBindUi(this));
    }

    @Override
    protected void initWidget(Widget widget) {
        super.initWidget(widget);
    }

    @Override
    public void present(MvpPlace transferPlace) {
//        logger.warning("TransferPresenter opened "+mvpController);
    }
}
