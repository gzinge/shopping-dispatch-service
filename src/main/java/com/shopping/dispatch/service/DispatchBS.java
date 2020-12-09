package com.shopping.dispatch.service;

import com.shopping.dispatch.model.Dispatch;
import com.shopping.dispatch.repository.DispatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DispatchBS implements DispatchBSI{

    @Autowired
    private DispatchRepository dispatchRepository;

    @Override
    public int createDispatch(Dispatch dispatch) throws Exception {
       if(dispatch != null) {
           Dispatch newDispatch = new Dispatch();
           newDispatch.setOrder(dispatch.getOrder());
           newDispatch.setDispatchDate(new Date());
           newDispatch.setAddress(dispatch.getAddress());
           newDispatch.setCourierName(dispatch.getCourierName());
           newDispatch.setDeliveryDate(dispatch.getDeliveryDate());
           newDispatch.setCourierName(dispatch.getCourierName());
           newDispatch.setDeliveryStatus(dispatch.getDeliveryStatus());
           dispatchRepository.save(newDispatch);
           return 1;
       }
       return 0;
    }

    @Override
    public Dispatch getDispatchById(Long dispatchId) throws Exception {
        if(dispatchId != null) {
            Optional<Dispatch> opt = dispatchRepository.findById(dispatchId);
            if(opt.isPresent()) {
                return opt.get();
            }
        }
        return null;
    }

    @Override
    public List<Dispatch> getDispatchByOrderId(Long orderId) throws Exception {
        List<Dispatch> dispatchList = new ArrayList<>();
        if(orderId != null) {
           dispatchList =  dispatchRepository.findAll().stream()
                                .filter(dispatch -> orderId.equals(dispatch.getOrder().getId()))
                                .collect(Collectors.toList());
        }
        return dispatchList;
    }

    @Override
    public int updateDispatchDelivery(Dispatch dispatch) throws Exception {
        if(dispatch != null) {
            Dispatch orgDispatch = getDispatchById(dispatch.getId());
            orgDispatch.setDeliveryDate(dispatch.getDeliveryDate());
            orgDispatch.setDeliveryStatus(dispatch.getDeliveryStatus());
            dispatchRepository.save(orgDispatch);
            return 1;
        }
        return 0;
    }

    @Override
    public int removeDispatchById(Long dispatchId) throws Exception {
        if(dispatchId != null) {
            dispatchRepository.deleteById(dispatchId);
            return 1;
        }
        return 0;
    }

    @Override
    public int removeAllDispatch() throws Exception {
        dispatchRepository.deleteAll();
        return 1;
    }
}
