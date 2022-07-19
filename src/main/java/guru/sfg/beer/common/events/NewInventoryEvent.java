package guru.sfg.beer.common.events;

import guru.sfg.beer.common.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * Created by okostetskyi on 19.07.2022
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {

    static final long serialVersionUID = 2680699635249989723L;

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}