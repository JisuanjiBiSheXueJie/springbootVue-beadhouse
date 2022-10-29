package com.example.nursinghomeapplication;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.auth.AuthenticationToken;
import org.apache.pulsar.client.impl.conf.ClientConfigurationData;

import java.util.HashMap;

/**
 * \* Created with IntelliJ IDEA.
 * \*  @author 用户: 28159
 * \* 日期: 2022/4/10
 * \* 时间: 13:20
 * \* 乌漆嘛黑码神保佑没有bug:
 * \
 */
public class Pulsar {
    private PulsarClient client;
    public void Pulsar() throws PulsarClientException {
        client = PulsarClient.builder()
                .serviceUrl("pulsar://100.118.1.12:32230,100.118.1.13:30376")
                .build();
        ConsumerBuilder<byte[]> consumerBuilder = client.newConsumer();
        consumerBuilder.topic("my-topic");
        consumerBuilder.subscriptionName("my-subscription");
        consumerBuilder.subscriptionType(SubscriptionType.Exclusive);
        consumerBuilder.subscribe();
    }
    public void Close() throws PulsarClientException {
        client.close();
    }
    public PulsarClient getPulsarClient(){
        return client;
    }
}
class main {
    //来个主方法
    public static void main(String[] args) throws PulsarClientException { //主方法
        Pulsar pulsar = new Pulsar();
        pulsar.Pulsar();
    }
}
