package app;
 
import org.springframework.stereotype.Component;

import ma.glasnost.orika.impl.ConfigurableMapper;
 
@Component
public class MyCustomMapper extends ConfigurableMapper {
 
//    protected void configure(MapperFactory factory) {
//        factory.classMap(Bet.class, BetBean.class)
//             .field("_1", "_2")
//             .byDefault()
//            .register();
//    }
} 