package sosteam.throwapi.domain.mail.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sosteam.throwapi.global.entity.PrimaryKeyEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class AuthMail extends PrimaryKeyEntity {

    @Column(length = 6)
    public String authCode;

    @CreatedDate
    @Column(updatable = false)
    public LocalDateTime createdAt;

    public LocalDateTime endAt;

    public String email;

    public AuthMail(String authCode, LocalDateTime endAt, String email) {
        this.authCode = authCode;
        this.endAt = endAt;
        this.email = email;
    }
}