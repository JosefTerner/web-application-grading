package com.github.grading.utils;

import com.github.grading.exceptions.BadRequest;
import com.github.grading.exceptions.TokenProviderException;
import com.github.grading.payload.Token;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class TokenProviderTest {

    long dateStart = new GregorianCalendar(2019, Calendar.FEBRUARY, 11).getTime().getTime();
    long dateFinish = new GregorianCalendar(2023, Calendar.APRIL, 23).getTime().getTime();
    Token t = new Token(1L, "Aaaa", new Date().getTime(), new Date().getTime());

    Token tAnother = new Token(203L, "First", dateStart, dateFinish);
    Token tNull = null;

    @Test
    public void encode (){
        String cipher = TokenProvider.encode(t);
        Token token = TokenProvider.decode(cipher);
        Assert.assertEquals(t,token);
    }

    @Test
    public void encodeAnother (){
        String cipher = TokenProvider.encode(tAnother);
        Token token = TokenProvider.decode(cipher);
        Assert.assertEquals(tAnother,token);
    }

    @Test (expected = BadRequest.class)
    public void encodeNull (){
        String cipher = TokenProvider.encode(tNull);
        Token token = TokenProvider.decode(cipher);
        Assert.assertEquals(1,token);
    }

}
