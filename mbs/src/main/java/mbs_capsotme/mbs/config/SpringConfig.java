package mbs_capsotme.mbs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@ComponentScan
public class SpringConfig {

    private final EntityManager em;
}
