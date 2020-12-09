package com.shopping.dispatch.service;

import com.shopping.dispatch.model.Dispatch;

import java.util.List;

public interface DispatchBSI {

    public int createDispatch(Dispatch dispatch) throws Exception;

    public Dispatch getDispatchById(Long dispatchId) throws Exception;

    public List<Dispatch> getDispatchByOrderId(Long orderId) throws Exception;

    public int updateDispatchDelivery(Dispatch dispatch) throws Exception;

    public int removeDispatchById(Long dispatchId) throws Exception;

    public int removeAllDispatch() throws Exception;

}
