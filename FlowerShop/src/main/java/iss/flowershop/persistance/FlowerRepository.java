package iss.flowershop.persistance;

import iss.flowershop.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Integer> {

}
