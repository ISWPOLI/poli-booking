package co.edu.poligran.serviciosalestudiante.configuration;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventosCache implements CacheEventListener<Object, Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventosCache.class);

    @Override
    public void onEvent(CacheEvent<?, ?> event) {
        LOGGER.info("Evento: " + event.getType() + " Llave: " + event.getKey() + " valor anterior: " + event.getOldValue
                () + " valor nuevo: " + event.getNewValue());
    }
}
