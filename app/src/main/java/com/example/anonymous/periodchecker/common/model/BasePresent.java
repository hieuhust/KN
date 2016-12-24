package com.example.anonymous.periodchecker.common.model;

/**
 * Created by Huy Hieu on 12/24/2016.
 */

public abstract class BasePresent<T extends DataModel> {

    protected T dataModel;

    public T getData() {
        dataModel = fetchDataModel();
        dataModel.attach(this);
        return dataModel;
    }

    protected abstract T fetchDataModel();

    /**
     * This method is called when data model is changed
     * @param dataModel  a new dataModel after data model is changed
     */
    public abstract void signalNotifyDataChange(T dataModel);
}
