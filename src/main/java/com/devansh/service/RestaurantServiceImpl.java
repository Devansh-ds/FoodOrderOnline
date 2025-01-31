package com.devansh.service;

import com.devansh.Model.Address;
import com.devansh.Model.Restaurant;
import com.devansh.Model.User;
import com.devansh.dto.RestaurantDto;
import com.devansh.repo.AddressRepository;
import com.devansh.repo.RestaurantRepository;
import com.devansh.repo.UserRepository;
import com.devansh.request.CreateRestaurantRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
        System.out.println("Restaurant creation: " + request.getAddress());

        Address address = Address
                .builder()
                .streetAddress(request.getAddress().getStreetAddress())
                .city(request.getAddress().getCity())
                .state(request.getAddress().getState())
                .zipCode(request.getAddress().getZipCode())
                .country(request.getAddress().getCountry())
                .build();

        Address savedAddress = addressRepository.save(address);
        Restaurant restaurant = Restaurant
                .builder()
                .address(savedAddress)
                .contactInformation(request.getContactInformation())
                .name(request.getName())
                .description(request.getDescription())
                .cuisineType(request.getCuisineType())
                .openingHours(request.getOpeningHours())
                .registrationDate(LocalDateTime.now())
                .images(request.getImages())
                .openingHours(request.getOpeningHours())
                .owner(user)
                .build();
        System.out.println("Restaurant created: " + restaurant);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Integer restaurantId, CreateRestaurantRequest request) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if (request.getCuisineType() != null) {
            restaurant.setCuisineType(request.getCuisineType());
        }
        if (request.getOpeningHours() != null) {
            restaurant.setOpeningHours(request.getOpeningHours());
        }
        if (request.getImages() != null) {
            restaurant.setImages(request.getImages());
        }
        if (request.getContactInformation() != null) {
            restaurant.setContactInformation(request.getContactInformation());
        }
        if (request.getAddress() != null) {
            restaurant.setAddress(request.getAddress());
        }
        if (request.getName() != null) {
            restaurant.setName(request.getName());
        }
        if (request.getDescription() != null) {
            restaurant.setDescription(request.getDescription());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Integer restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Integer restaurantId) throws Exception {

        return restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Restaurant not found with Id: " + restaurantId));
    }

    @Override
    public Restaurant getRestaurantByUserId(Integer userId) throws Exception {
        return restaurantRepository
                .findByOwnerId(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("No restaurant found with user id: " + userId));
    }

    @Override
    public Restaurant addToFavourites(Integer restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (user.getFavoriteRestaurants().contains(restaurant)) {
            user.getFavoriteRestaurants().remove(restaurant);
        } else {
            user.getFavoriteRestaurants().add(restaurant);
        }

        userRepository.save(user);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurantStatus(Integer restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}

















