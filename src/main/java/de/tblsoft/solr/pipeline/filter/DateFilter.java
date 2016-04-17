package de.tblsoft.solr.pipeline.filter;

import de.tblsoft.solr.pipeline.AbstractFilter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tblsoft 17.03.16.
 */
public class DateFilter extends AbstractFilter {

    private SimpleDateFormat inputDateFormat;
    private SimpleDateFormat outputDateFormat;

    private String dateField;


    @Override
    public void init() {
        String inputDateFormatString = getProperty("inputDateFormat", null);
        verify(inputDateFormatString, "For the DateFilter a inputDateFormat must be defined.");

        inputDateFormat = new SimpleDateFormat(inputDateFormatString);

        String outputDateFormatString = getProperty("outputDateFormat", null);
        verify(outputDateFormatString, "For the DateFilter a outputDateFormat must be defined.");

        outputDateFormat = new SimpleDateFormat(outputDateFormatString);

        this.dateField = getProperty("dateField", null);
        verify(this.dateField, "For the DateFilter a dateField must be defined.");

        super.init();
    }

    @Override
    public void field(String name, String value) {
        if(!name.matches(this.dateField)) {
            super.field(name,value);
            return;
        }
        try {
            Date inputDate = inputDateFormat.parse(value);
            String newValue = outputDateFormat.format(inputDate);
            super.field(name,newValue);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}