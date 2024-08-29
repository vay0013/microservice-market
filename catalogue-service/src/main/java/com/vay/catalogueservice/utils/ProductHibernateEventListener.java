package com.vay.catalogueservice.utils;

import com.vay.catalogueservice.dto.KafkaProductDto;
import com.vay.catalogueservice.mapper.ProductMapper;
import com.vay.catalogueservice.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductHibernateEventListener implements PostDeleteEventListener, PostUpdateEventListener {

    private final EntityManagerFactory entityManagerFactory;
    private final ProductMapper productMapper;

    private final KafkaTemplate<String, KafkaProductDto> kafkaTemplate;

    @Override
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
        Object entity = postDeleteEvent.getEntity();
        if (entity instanceof Product product) {
            kafkaTemplate.send("product", productMapper.toDto(product));
        }

    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        Object entity = postUpdateEvent.getEntity();
        if (entity instanceof Product product) {
            kafkaTemplate.send("product", productMapper.toDto(product));
        }

    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return true;
    }

    @PostConstruct
    private void postConstruct() {
        SessionFactoryImplementor sessionFactory = entityManagerFactory.unwrap(SessionFactoryImplementor.class);
        EventListenerRegistry registry = sessionFactory
                .getServiceRegistry()
                .getService(EventListenerRegistry.class);
        assert registry != null;
        registry.prependListeners(EventType.POST_COMMIT_DELETE, this);
        registry.prependListeners(EventType.POST_COMMIT_UPDATE, this);
    }
}