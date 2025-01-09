package mate.academy.rickandmorty.init;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.ExternalClientService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitialization {

    private final ExternalClientService externalClientService;

    @EventListener(ApplicationReadyEvent.class)
    public void initDataBase() {
        externalClientService.getAndSaveAll();
    }
}
