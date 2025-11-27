package service.dataaccess.restaurant.adapter;

import core.domain.entity.Restaurant;
import org.springframework.stereotype.Component;
import service.dataaccess.restaurant.entity.RestaurantEntity;
import service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import service.dataaccess.restaurant.repository.RestaurantJpaRepository;
import service.domain.ports.output.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    public RestaurantRepositoryImpl(RestaurantJpaRepository restaurantJpaRepository,
                                    RestaurantDataAccessMapper restaurantDataAccessMapper) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.restaurantDataAccessMapper = restaurantDataAccessMapper;
    }

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        List<UUID> restaurantProduct =
                restaurantDataAccessMapper.restaurantToRestaurantProducts(restaurant);
        Optional<List<RestaurantEntity>> restaurantEntities = restaurantJpaRepository
                .findByRestaurantAndProductIdIn(restaurant.getId().getValue(), restaurantProduct);

        return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }


}
