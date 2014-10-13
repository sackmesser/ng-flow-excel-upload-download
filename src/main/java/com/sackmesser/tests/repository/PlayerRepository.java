package com.sackmesser.tests.repository;

import com.sackmesser.tests.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 08/10/14
 * Time: 10:27
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
}
