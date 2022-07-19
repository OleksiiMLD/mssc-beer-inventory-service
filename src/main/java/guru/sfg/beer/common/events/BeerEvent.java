package guru.sfg.beer.common.events;

import guru.sfg.beer.common.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by okostetskyi on 19.07.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BeerEvent implements Serializable {

    private BeerDto beerDto;
}
