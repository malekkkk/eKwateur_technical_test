package org.ekwateur.cli.service.reader;

import org.beryx.textio.TextIO;
import org.ekwateur.core.model.Consumption;
import org.springframework.stereotype.Service;

@Service
public class ConsumptionReaderService {

    private final TextIO textIO;

    public ConsumptionReaderService(TextIO textIO) {
        this.textIO = textIO;
    }

    public Consumption readCustomerConsumption(){

       double electricityConsumptionInKwh = textIO.newDoubleInputReader()
               .withMinVal(0d)
               .read("Please enter the electricity consumption in Kwh");
       double gazConsumptionInKwh = textIO.newDoubleInputReader()
               .withMinVal(0d)
               .read("Please enter the gaz consumption in kwh");

       return new Consumption(electricityConsumptionInKwh, gazConsumptionInKwh);
    }
}
