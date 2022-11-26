package com.example.pizzamagic.repositories;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.example.pizzamagic.models.Ingredient;
import com.example.pizzamagic.models.Pizza;
import com.example.pizzamagic.models.PizzaOrder;
import com.example.pizzamagic.repositories.interfaces.IOrderRepository;

@Repository
public class JDBCOrderRepository implements IOrderRepository{

    // jdbc operations is the interface of JDBC template
    private JdbcOperations jdbcOperations;

    public JDBCOrderRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public PizzaOrder save(PizzaOrder pizzaOrder) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
            "insert into Pizza_Order (delivery_Name, delivery_Street, delivery_City, " +
            "delivery_State, delivery_Zip, " + 
            "cc_number, cc_expiration, cc_cvv,placed_at) VALUES (?,?,?,?,?,?,?,?,?)",
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
            Arrays.asList(
                pizzaOrder.getDeliverToName(),
                pizzaOrder.getDeliveryStreet(),
                pizzaOrder.getDeliveryCity(),
                pizzaOrder.getDeliveryState(),
                pizzaOrder.getDeliveryZip(),
                pizzaOrder.getCcNumber(),
                pizzaOrder.getCcExpiration(),
                pizzaOrder.getCcCVV(),
                new Date()
            )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc,keyHolder);
        long orderId = keyHolder.getKey().longValue();
        List<Pizza> pizzas = pizzaOrder.getPizzas();
        int sequenceNum = 0;
        for(Pizza pizza : pizzas){
            savePizza(pizza, orderId, sequenceNum++);
        }
        pizzaOrder.setId(orderId);
        return pizzaOrder;
    }

    private void savePizza(Pizza pizza, long orderId, int sequenceNum){
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
            "insert into Pizza (name, order_id, sequence_num, created_at) VALUES (?,?,?,?)", 
            Types.VARCHAR,Types.BIGINT, Types.BIGINT, Types.TIMESTAMP);
        
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc= pscf.newPreparedStatementCreator(
            Arrays.asList(
                pizza.getName(),
                orderId,
                sequenceNum,
                new Date()
            )
        );
        
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long pizzaId = keyHolder.getKey().longValue();

        setPizzaIngredientsMapping(pizzaId, pizza.getIngredients());

    }

    private void setPizzaIngredientsMapping(long pizzaId, List<Ingredient> ingredients){
        int sequenceNum = 0;
        for(Ingredient ingredient : ingredients){
            jdbcOperations.update(
                "insert into Pizza_Ingredient_Mapping(pizza_id, ingredient_id, sequence_num) " +
                "values(?,?,?)",
                pizzaId, ingredient.getId(),sequenceNum++
            );
        }
    }

}
