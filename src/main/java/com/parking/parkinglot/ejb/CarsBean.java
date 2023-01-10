package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.CarPhotoDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.CarPhoto;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> copyCarsToDto(List<Car> cars){
        List<CarDto> copyList = new ArrayList<>();
        LOG.info("copyCarsToDto");
        try {
            for (Car car : cars) {
                copyList.add(new CarDto(car.getId(), car.getLicensePlate(), car.getParkingSpot(), car.getOwner().getUsername()));
            }
            return copyList;
        }catch (Exception ex){
            throw new EJBException(ex);
        }
    }

    public List<CarDto> findAllCars(){
        LOG.info("findAllCars");
        try{
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        }catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void createCar(String licensePlate, String parkingSpot, Long userId){
        LOG.info("createCar");

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        entityManager.persist(car);
    }

    public CarDto findById(Long id){
        LOG.info("findById");

        CarDto car = null;
        List<CarDto> allCars = findAllCars();

        for (int i = 0; i < allCars.size(); i++) {
            if (Objects.equals(allCars.get(i).getId(), id)) {
                car = allCars.get(i);
            }
        }

    return car;
    }

    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId){
        LOG.info("updateCar");

        Car car = entityManager.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User oldUser = car.getOwner();
        oldUser.getCars().remove(car);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);
    }

    public void deleteCarsById(List<Long> carIds){
        LOG.info("deleteCarsById");

        for(Long carId : carIds){
            Car car = entityManager.find(Car.class, carId);
            entityManager.remove(car);
        }
    }

    public void addPhotoToCar(Long carId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addPhotoToCar");
        CarPhoto photo = new CarPhoto();
        photo.setFileName(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        Car car = entityManager.find(Car.class, carId);
        if (car.getPhoto() != null) {
            entityManager.remove(car.getPhoto());
        }
        car.setPhoto(photo);
        photo.setCar(car);
        entityManager.persist(photo);
    }
    public CarPhotoDto findPhotoByCarId(Integer carId) {
        List<CarPhoto> photos = entityManager
                .createQuery("SELECT p FROM CarPhoto p WHERE p.car.id = :id", CarPhoto.class)
                .setParameter("id", carId)
                .getResultList();
        if (photos.isEmpty()) {
            return null;
        }
        CarPhoto photo = photos.get(0);
        return new CarPhotoDto(photo.getId(), photo.getFileName(), photo.getFileType(),
                photo.getFileContent());
    }
}
