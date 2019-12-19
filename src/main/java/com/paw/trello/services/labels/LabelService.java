package com.paw.trello.services.labels;

import com.paw.trello.dtos.ResponseMessage;
import com.paw.trello.dtos.labels.AddLabelToCardDto;
import com.paw.trello.dtos.labels.EditLabelDto;
import com.paw.trello.entities.Label;
import com.paw.trello.services.auth.AuthorizationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class LabelService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private AuthorizationService authorizationService;

    public ResponseMessage addLabelToCard(AddLabelToCardDto addLabelToCardDto) {
        String username = this.authorizationService.getUsername(addLabelToCardDto.getCreatorId());
        Label label = new Label();
        label.setAuditCd(new Date());
        label.setCardId((long) addLabelToCardDto.getCardId());
        label.setLabelColor(addLabelToCardDto.getColor());
        label.setLabelContent(addLabelToCardDto.getContent());
        label.setCreatorId((long) addLabelToCardDto.getCreatorId());
        label.setCreatorUsername(username);

        entityManager.persist(label);

        return ResponseMessage.builder()
                              .message("Pomyślnie dodano etykietę do karty.")
                              .build();
    }

    public List<Label> getLabelsForSpecificCard(int cardId) {
        List<Label> labels = entityManager.createQuery("SELECT l FROM Label l WHERE l.cardId = :cardId")
                .setParameter("cardId", cardId)
                .getResultList();

        return labels;
    }

    public ResponseMessage deleteLabelFromCard(int labelId) {
        Label label = (Label) entityManager.createQuery("SELECT l FROM Label l WHERE l.labelId = :labelId")
                .setParameter("labelId", labelId)
                .getSingleResult();

        entityManager.remove(label);

        return ResponseMessage.builder()
                              .message("Pomyślnie usunięto etykietę z karty!")
                              .build();
    }

    public ResponseMessage editLabel(EditLabelDto editLabelDto) {
        Label label = (Label) entityManager.createQuery("SELECT l FROM Label l WHERE l.labelId = :labelId")
                                           .setParameter("labelId", editLabelDto.getLabelId())
                                           .getSingleResult();

        label.setCreatorId((long) editLabelDto.getCreatorId());
        label.setAuditMd(new Date());
        label.setLabelColor(editLabelDto.getColor());
        label.setLabelContent(editLabelDto.getContent());

        entityManager.merge(label);

        return ResponseMessage.builder()
                              .message("Pomyślnie zmodyfikowano etykietę!")
                              .build();
    }

}
