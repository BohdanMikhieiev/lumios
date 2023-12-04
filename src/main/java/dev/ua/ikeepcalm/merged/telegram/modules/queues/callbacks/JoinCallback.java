package dev.ua.ikeepcalm.merged.telegram.modules.queues.callbacks;

import dev.ua.ikeepcalm.merged.database.entities.queue.SimpleQueue;
import dev.ua.ikeepcalm.merged.database.entities.queue.QueueUser;
import dev.ua.ikeepcalm.merged.telegram.modules.CallbackParent;
import dev.ua.ikeepcalm.merged.telegram.wrappers.EditMessage;
import dev.ua.ikeepcalm.merged.telegram.modules.queues.utils.QueueUpdateUtil;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.UUID;

@Component("simple-join")
public class JoinCallback extends CallbackParent {

    @Override
    public void processUpdate(CallbackQuery message) {
        String receivedCallback = message.getData().replace("-simple-join", "");
        String callbackQueryId = message.getId(); instantiateUpdate(message);
        SimpleQueue simpleQueue = simpleQueueLifecycle.getSimpleQueue(UUID.fromString(receivedCallback));
        QueueUser queueUser = new QueueUser();
        queueUser.setName(message.getFrom().getFirstName());
        queueUser.setAccountId(message.getFrom().getId());
        queueUser.setUsername(message.getFrom().getUserName());
        if (!simpleQueue.getContents().contains(queueUser)) {
            simpleQueue.addUser(queueUser);
            simpleQueue.setMessageId(absSender.sendEditMessage
                    (QueueUpdateUtil.updateMessage(message.getMessage(), simpleQueue))
                    .getMessageId());
            simpleQueueLifecycle.saveSimpleQueue(simpleQueue);
            this.absSender.sendAnswerCallbackQuery("Успішно заброньовано місце у черзі!", callbackQueryId);
        } else {
            this.absSender.sendAnswerCallbackQuery("Ви вже знаходитесь у цій черзі!", callbackQueryId);
        }
    }



}

