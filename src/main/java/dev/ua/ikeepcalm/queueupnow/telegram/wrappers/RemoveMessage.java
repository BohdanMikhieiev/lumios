package dev.ua.ikeepcalm.queueupnow.telegram.wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemoveMessage {

    private int messageId;
    private long chatId;

}

