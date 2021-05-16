package com.github.grading.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.grading.payload.Envelope;
import com.github.grading.payload.Token;
import com.github.grading.payload.Topic;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

public class JsonHelperTest {

    public static final String tokenStr = "{\"id\":null,\"login\":\"Alex\",\"expire_in\":1900,\"createdAt\":180000}";

    private final Token token = new Token("Alex", 1900, 180000);


    @Test
    public void toJson() {
        Optional<String> result = JsonHelper.toJson(token);
        String act = result.orElseThrow();
        Assert.assertEquals(tokenStr, act);
    }

    @Test(expected = JsonProcessingException.class)
    public void fromJsonEmpty() {
        Optional<String> result = JsonHelper.toJson(null);
        result.orElseThrow();
    }


    @Test
    public void fromJson() {
        Optional<Token> result = JsonHelper.fromJson(tokenStr,Token.class);
        Token act = result.orElseThrow();
        Assert.assertEquals(token , act);
    }

    @Test
    public void fromJson2() {
        Token token = new Token(1L, "Aaaa", new Date().getTime(), new Date().getTime());
        String cipherToken = TokenProvider.encode(token);
        Envelope env = new Envelope(Topic.auth, token);
        String res = JsonHelper.toJson(env).orElseThrow();
        String payload = "{\"topic\":\"auth\",\"token\":\"" + cipherToken + "\"}";
        Assert.assertEquals(payload, res);
    }


}