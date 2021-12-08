package mywebapp.controller;

import mywebapp.model.LCSData;
import mywebapp.model.LCSResult;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
public class LCSController {

    @PostMapping("/lcs")
    public @ResponseBody LCSResult processLCS(@RequestBody LCSData lcsData) {
        if(!isValidRequest(lcsData)) throw new RuntimeException("Empty String list");
        System.out.println(lcsData);
        String value = lcsData.findLCS();
        LCSResult lcsResult = new LCSResult();
        lcsResult.lcs.setValue(value);
        return lcsResult;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid JSON String")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleException() {

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Empty List")
    @ExceptionHandler(RuntimeException.class)
    public void runtimeException() {

    }

    private boolean isValidRequest(LCSData lcsData) {
        if(lcsData.getSetOfStrings() == null) return false;

        if(lcsData.getSetOfStrings().size() == 0) return false;

        if(lcsData.getSetOfStrings().size() == 1
                &&  lcsData.getSetOfStrings().get(0).getValue().trim().equalsIgnoreCase("")) return false;

        return true;


    }
}
