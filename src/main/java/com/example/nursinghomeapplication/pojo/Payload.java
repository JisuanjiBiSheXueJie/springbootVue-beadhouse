package com.example.nursinghomeapplication.pojo;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/3/9
 * \* 时间: 14:35
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Payload {
    //发布者
    private String issuer;
    //主题
    private String subject;
    //签名的观众 也可以理解谁接受签名的
    private List<String> audience;
    //发布时间
    private Date issuedAt;
    //过期时间
    private Date expiresAt;
    //开始使用时间
    private Date notBefore;
    private Map<String, String> claims;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getAudience() {
        return audience;
    }

    public void setAudience(List<String> audience) {
        this.audience = audience;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Date getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
    }

    public Map<String, String> getClaims() {
        return claims;
    }

    public void setClaims(Map<String, String> claims) {
        this.claims = claims;
    }

    public void setAudience(String... audienceStr) {
        List<String> audiences = new ArrayList<String>();
        for (String string : audienceStr) {
            audiences.add(string);
        }
        this.audience = audiences;
    }
}
