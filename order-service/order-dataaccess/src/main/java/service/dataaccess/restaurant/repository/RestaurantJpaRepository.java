package service.dataaccess.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.dataaccess.restaurant.entity.RestaurantEntity;
import service.dataaccess.restaurant.entity.RestaurantEntityId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, RestaurantEntityId> {

    Optional<List<RestaurantEntity>> findByRestaurantAndProductIdIn(UUID restaurantId, List<UUID> productIds);

}
