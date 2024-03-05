package dev.ua.ikeepcalm.queueupnow.database.entities.history;

import dev.ua.ikeepcalm.queueupnow.database.entities.reverence.ReverenceUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@Entity(name = "message_records")
public class MessageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private Long messageId;

    @Column
    @NotNull
    private Long chatId;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ReverenceUser user;

}
