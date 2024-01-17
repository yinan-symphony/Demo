package com.example.datahose;

import com.symphony.bdk.core.service.datafeed.EventException;
import com.symphony.bdk.core.service.datafeed.RealTimeEventListener;
import com.symphony.bdk.core.service.message.MessageService;
import com.symphony.bdk.gen.api.model.V4Initiator;
import com.symphony.bdk.gen.api.model.V4MessageSent;
import com.symphony.bdk.template.api.Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnMessageSentListener implements RealTimeEventListener {
  private final MessageService messageService;
  private final Template template;

  @Autowired
  public OnMessageSentListener(MessageService messageService) {
    this.messageService = messageService;
    this.template = messageService.templates().newTemplateFromClasspath("/templates/welcome.ftl");
  }

  @Override
  public void onMessageSent(V4Initiator initiator, V4MessageSent event) throws EventException {
    System.out.println(String.format("received message - %s from %s", event.getMessage().getMessage(),
        initiator.getUser().getDisplayName()));
  }
}
