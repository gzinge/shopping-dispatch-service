package com.shopping.dispatch.api;

import com.shopping.dispatch.model.Dispatch;
import com.shopping.dispatch.service.DispatchBSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dispatch")
public class DispatchRestController {

    Logger logger = LoggerFactory.getLogger(DispatchRestController.class);

    @Autowired
    private DispatchBSI dispatchBS;

    @PostMapping("/create")
    public ResponseEntity<Object> createDispatch(@RequestBody Dispatch dispatch) throws Exception {
        try {
            int i = dispatchBS.createDispatch(dispatch);
        }catch (Exception ex) {
            logger.error("Exception occurred while creating dispatch record", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Dispatch record Created Successfully", HttpStatus.OK);
    }

    @GetMapping("/dispatchId/{dispatchId}")
    public ResponseEntity<Object> getDispatchById(@PathVariable("dispatchId") Long dispatchId) throws Exception {
        Dispatch dispatch = null;
        try {
            dispatch = dispatchBS.getDispatchById(dispatchId);
        }catch (Exception ex) {
            logger.error("Exception occurred while getting dispatch record by id : "+dispatchId, ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(dispatch, HttpStatus.OK);
    }

    @GetMapping("/orderId/{orderId}")
    public ResponseEntity<Object> getDispatchByOrderId(@PathVariable("orderId") Long orderId) throws Exception{
        List<Dispatch> dispatchList = new ArrayList<>();
        try {
            dispatchList = dispatchBS.getDispatchByOrderId(orderId);
        }catch (Exception ex) {
            logger.error("Exception occurred while getting dispatch record by orderId : "+orderId, ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(dispatchList, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateDispatchDelivery(@RequestBody Dispatch dispatch) throws Exception{
        try {
            int ret = dispatchBS.updateDispatchDelivery(dispatch);
        }catch (Exception ex) {
            logger.error("Exception occurred while updating dispatch record : ", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Dispatch record updated with delivery details", HttpStatus.OK);
    }

    @DeleteMapping("/delete/dispatchId/{dispatchId}")
    public ResponseEntity<Object> removeDispatchById(@PathVariable("dispatchId") Long dispatchId) throws Exception{
        try {
            int ret = dispatchBS.removeDispatchById(dispatchId);
        }catch (Exception ex) {
            logger.error("Exception occurred while removing dispatch record by id: "+dispatchId, ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Dispatch record removed with delivery details", HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<Object> removeAllDispatch() throws Exception{
        try {
            int ret = dispatchBS.removeAllDispatch();
        }catch (Exception ex) {
            logger.error("Exception occurred while removing all dispatch record: ", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(" All Dispatch record removed", HttpStatus.OK);
    }

}
