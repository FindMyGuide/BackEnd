package com.find_my_guide.main_member.mail.repository;

import com.find_my_guide.main_member.mail.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
    Boolean existsMailByEmail(String email);
    Boolean existsMailByCodeAndEmail(String code, String email);
    Mail findMailByEmail(String email);

    Mail findMailByCodeAndEmail(String code, String email);
}
